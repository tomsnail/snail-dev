package cn.tomsnail.jms;

/**
 *        消息接收者回调接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月17日 下午1:07:40
 * @see 
 */
public interface IJmsReceiveCall{
	
	/**
	 *        回调
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:13:43
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void call(Message msg);

}
