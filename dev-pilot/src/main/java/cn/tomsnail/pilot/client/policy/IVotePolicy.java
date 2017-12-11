package cn.tomsnail.pilot.client.policy;

import cn.tomsnail.pilot.model.ServiceProcess;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月11日 上午9:25:39
 * @see 
 */
public interface IVotePolicy {

	public String getNodePath(ServiceProcess serviceProcess);
	
	public boolean canStart(ServiceProcess serviceProcess);
	
}
