package cn.tomsnail.snail.ext.pilot.core.server;

import java.util.List;

import cn.tomsnail.snail.ext.pilot.core.model.ServiceProcess;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月7日 上午9:36:54
 * @see 
 */
public interface IServices {

	public List<ServiceProcess> getService(String node,String service);
}
