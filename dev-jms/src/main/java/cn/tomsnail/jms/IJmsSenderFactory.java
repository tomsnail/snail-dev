package cn.tomsnail.jms;

/**
 *        jms发送者接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 上午10:55:46
 * @see 
 */
public interface IJmsSenderFactory {

	/**
	 *        获取jms发送者
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:08:50
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public IJmsSender getJmsSender(MQConfig mqConfig);
	
}
