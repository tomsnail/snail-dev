package cn.tomsnail.jms;

import java.io.Serializable;

/**
 *        jms发送者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月17日 下午1:05:36
 * @see 
 */
public interface IJmsSender {
	
	/**
	 *        发送
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:17:45
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void send(String topic,Serializable obj);
	
	/**
	 *        发送
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:17:54
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void send(String topic,String msg);
	
}
