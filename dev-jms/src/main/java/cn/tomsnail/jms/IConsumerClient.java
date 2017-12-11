package cn.tomsnail.jms;

/**
 *        消费者客户端
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月17日 下午2:15:25
 * @see 
 */
public interface IConsumerClient {

	/**
	 *        注册回调
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:15:20
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void register(IJmsReceiveCall jmsReceiveCall);
	
	/**
	 *        注册回调
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:15:30
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void register(MQConfig mqConfig,IJmsReceiveCall jmsReceiveCall);
	
	/**
	 *        关闭
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:15:36
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void close();
	
	/**
	 *        初始化
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:15:43
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void init();
	
}
