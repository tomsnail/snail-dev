package cn.tomsnail.snail.ext.mq.core.mq.activemq;

/**
 *        ActiveMQ客户端工厂
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年1月19日 下午1:22:28
 * @see 
 */
public class JmsAMQClientFactory {
	 
	
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年1月19日 下午4:49:07
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static JmsSender getJmsSender(JmsSenderConfig config){
		return new JmsSender(config);
	}	
	
	
	
}
