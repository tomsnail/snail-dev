package cn.tomsnail.config.client;

/**
 *        配置变更监听
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月21日 上午9:33:35
 * @see 
 */
public interface IConfigChangeListener {

	
	/**
	 *        变更通知
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:44:24
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void changed();
	
}
