package cn.tomsnail.starter.domain;



/**
 *        spring服务管理
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月30日 上午11:00:59
 * @see 
 */
public interface ISpringService {

	public void initService();

	public void startService();
	
	public void stopService();
	
}
