/**
 * Copyright (c) 2015-2017, Winter Lau (javayou@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.oschina.j2cache.cluster;

import net.oschina.j2cache.CacheException;
import net.oschina.j2cache.CacheProviderHolder;
import net.oschina.j2cache.Command;
import org.jgroups.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Properties;

/**
 * 使用 JGroups 组播进行集群内节点通讯
 * @author Winter Lau(javayou@gmail.com)
 */
public class JGroupsClusterPolicy extends ReceiverAdapter implements ClusterPolicy {

    private final static Logger log = LoggerFactory.getLogger(JGroupsClusterPolicy.class);

    private int LOCAL_COMMAND_ID = Command.genRandomSrc(); //命令源标识，随机生成，每个节点都有唯一标识

    private String configXml;
    private JChannel channel;
    private String name;
    private CacheProviderHolder holder;

    static {
        System.setProperty("java.net.preferIPv4Stack", "true"); //Disable IPv6 in JVM
    }

    /**
     * 构造函数
     * @param name 组播频道名称
     * @param props 配置文件路径
     */
    public JGroupsClusterPolicy(String name, Properties props) {
        this.name = name;
        if(this.name == null || this.name.trim().equalsIgnoreCase(""))
            this.name = "j2cache";
        this.configXml = props.getProperty("configXml");
        if(configXml == null || configXml.trim().length() == 0)
            this.configXml = "/network.xml";
    }

    @Override
    public boolean isLocalCommand(Command cmd) {
        return cmd.getSrc() == LOCAL_COMMAND_ID;
    }

    /**
     * 删除本地某个缓存条目
     * @param region 区域名称
     * @param keys   缓存键值
     */
    public void evict(String region, String... keys) {
        holder.getLevel1Cache(region).evict(keys);
    }

    /**
     * 清除本地整个缓存区域
     * @param region 区域名称
     */
    public void clear(String region) {
        holder.getLevel1Cache(region).clear();
    }

    @Override
    public void connect(Properties props, CacheProviderHolder holder) {
        this.holder = holder;
        try{
            long ct = System.currentTimeMillis();

            URL xml = getClass().getResource(configXml);
            if(xml == null)
                xml = getClass().getClassLoader().getParent().getResource(configXml);
            channel = new JChannel(xml);
            channel.setReceiver(this);
            channel.connect(name);

            this.publish(Command.join());
            log.info("Connected to jgroups channel:{}, time {}ms.", name, (System.currentTimeMillis()-ct));

        } catch (Exception e){
            throw new CacheException(e);
        }
    }

    @Override
    public void disconnect() {
        this.publish(Command.quit());
        channel.close();
    }

    @Override
    public void receive(Message msg) {
        //不处理发送给自己的消息
        if(msg.getSrc().equals(channel.getAddress()))
            return ;
        handleCommand(Command.parse((String)msg.getObject()));
    }

    @Override
    public void viewAccepted(View view) {
        log.info("Group Members Changed, LIST: {}",
                String.join(",", view.getMembers().stream().map(a -> a.toString()).toArray(String[]::new))
        );
    }

    @Override
    public void publish(Command cmd) {
        try {
        	cmd.setSrc(LOCAL_COMMAND_ID);
            Message msg = new Message(null, cmd.json());
            channel.send(msg);
        } catch (Exception e) {
            log.error("Failed to send message to jgroups -> {}", cmd, e);
        }
    }
}
