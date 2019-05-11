package cn.tomsnail.snail.ext.pilot.core;

import cn.tomsnail.snail.ext.pilot.core.client.SubReportClientProxy;
import cn.tomsnail.snail.ext.pilot.core.client.ZkClient;
import cn.tomsnail.snail.ext.pilot.core.client.ZkSubProxy;
import cn.tomsnail.snail.ext.pilot.core.client.content.CpuReportContent;
import cn.tomsnail.snail.ext.pilot.core.client.content.MemReportContent;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月8日 下午3:47:41
 * @see 
 */
public class ClientInit {

	public ClientInit(){
		MemReportContent memReportContent = new MemReportContent(new CpuReportContent(null));
		new SubReportClientProxy(new ZkClient(),memReportContent,new ZkSubProxy());
	}
	
	public static void init(){
		new ClientInit();
	}
	
}
