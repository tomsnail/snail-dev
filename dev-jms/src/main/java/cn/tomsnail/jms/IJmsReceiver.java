package cn.tomsnail.jms;

/**
 *        jms接收者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月17日 下午1:30:49
 * @see 
 */
public interface IJmsReceiver {

	/**
	 *        消息处理，阻塞方法
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:11:58
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void onMessage(Message message);

}
