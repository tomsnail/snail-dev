package cn.tomsnail.snail.core.config.server;

/**
 *        配置变化监听者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月4日 下午1:48:07
 * @see 
 */
public interface IConfigChangeListener {

	/**
	 *        变化通知
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:54:45
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void changed(String path,String value,int type);
	
}
