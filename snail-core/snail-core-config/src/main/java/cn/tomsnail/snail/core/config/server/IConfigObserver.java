package cn.tomsnail.snail.core.config.server;

/**
 *       配置观察者 
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月20日 下午1:24:20
 * @see 
 */
public interface IConfigObserver {

	/**
	 *        刷新
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:50:07
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void refresh();
	
	/**
	 *        初始化
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:50:16
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void init();
	
}
