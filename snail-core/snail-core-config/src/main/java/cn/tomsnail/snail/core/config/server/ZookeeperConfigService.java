package cn.tomsnail.snail.core.config.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;












import cn.tomsnail.snail.core.config.ConfigInfo;
import cn.tomsnail.snail.core.config.server.s.AConfigSource;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.core.util.zkclient.ZkHelper;
import cn.tomsnail.snail.core.util.zkclient.address.ZooKeeperAddress;





/**
 *        zookeeper配置服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月20日 下午1:50:35
 * @see 
 */
public class ZookeeperConfigService extends AConfigService  {
	
	private IConfigChangeListener configChangeListener = new DefaultConfigChangeListener();
	
	
	private static  ZkHelper ZK_HELPER = null;
	
	/**
	 *本地缓存
	 */
	private Map<String,String> cacheMap = null;
	
	public ZookeeperConfigService(){
		super();
		
	}
	
	public ZookeeperConfigService(AConfigSource configSource){
		super(configSource);
	}
	

	@Override
	public void delete(String key) {
		String path = ConfigInfo.rootNode+"/"+key.replace(".", "/");
		System.out.println("delete "+path);
		ZK_HELPER.deletePath(ConfigInfo.rootNode+"/"+key.replace(".", "/"));
		if(configChangeListener!=null ) configChangeListener.changed(path, "", ChangeType.delete);

	}

	@Override
	public void add(String key, String value) {
		String path = ConfigInfo.rootNode+"/"+key.replace(".", "/");
		System.out.println("add "+path);
		String paths[] = path.split("\\/");
		StringBuffer sb = new StringBuffer();
		for(String p:paths){
			if(StringUtils.isBlank(p)){
				continue;
			}
			sb.append("/").append(p);
			if(!ZK_HELPER.isExtisPath(sb.toString())){
				ZK_HELPER.createPath(sb.toString());
			}
		}
		ZK_HELPER.writeNode(path, value);
		if(configChangeListener!=null ) configChangeListener.changed(path, value, ChangeType.add);

	}

	@Override
	public void refresh(String key, String value) {
		String path = ConfigInfo.rootNode+"/"+key.replace(".", "/");
		System.out.println("refresh "+path);
		if(ZK_HELPER.isExtisPath(path)){
			ZK_HELPER.writeNode(path, value);
			if(configChangeListener!=null )configChangeListener.changed(path, value, ChangeType.refresh);
		}else{
			add(key,value);
		}
	}


	@Override
	public void initConfigMap() {
		ZK_HELPER = ZkHelper.getInstance(ZooKeeperAddress.ZK_ADDRESS);
		if(cacheMap==null)cacheMap = new HashMap<String, String>();
		if(!ZK_HELPER.isExtisPath(ConfigInfo.rootNode)){
			ZK_HELPER.createPathWithParent(ConfigInfo.rootNode, true);
		}
		List<String> nodes = ZK_HELPER.getPath(ConfigInfo.rootNode);
		if(nodes!=null){
			for(String node:nodes){
				setSubNode(ConfigInfo.rootNode+"/"+node);
			}
			Iterator<String> it = cacheMap.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				String value = cacheMap.get(key);
				this.configMap.put(key.replace(ConfigInfo.rootNode+"/", "").replace("/", "."), value);
			}
		}
	}
	
	/**
	 *        组织子节点
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:55:59
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	private void setSubNode(String parentNode){
		List<String> nodes = ZK_HELPER.getPath(parentNode);
		if(nodes!=null&&nodes.size()>0){
			for(String node:nodes){
				setSubNode(parentNode+"/"+node);
			}
		}else{
			try {
				cacheMap.put(parentNode, ZK_HELPER.readNode(parentNode)+"");
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(parentNode);
			}
		}
	}

	public IConfigChangeListener getConfigChangeListener() {
		return configChangeListener;
	}

	public void setConfigChangeListener(IConfigChangeListener configChangeListener) {
		this.configChangeListener = configChangeListener;
	}

	
	
}
