package cn.tomsnail.snail.ext.mq.core;

/**
 *        jms消费者工厂接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月18日 上午10:55:41
 * @see 
 */
public interface IJmsConsumerFactory {

	/**
	 *        获取消费者客户端
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:07:54
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public IConsumerClient getConsumerClient(MQConfig mqConfig);
	
}
