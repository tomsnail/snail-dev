package cn.tomsnail.pilot;

import cn.tomsnail.pilot.client.SubReportClientProxy;
import cn.tomsnail.pilot.client.ZkClient;
import cn.tomsnail.pilot.client.ZkSubProxy;
import cn.tomsnail.pilot.client.content.CpuReportContent;
import cn.tomsnail.pilot.client.content.MemReportContent;

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
