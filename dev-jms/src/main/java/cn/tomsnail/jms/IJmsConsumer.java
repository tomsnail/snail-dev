package cn.tomsnail.jms;

/**
 *        jms消费者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月17日 下午2:08:51
 * @see 
 */
public interface IJmsConsumer {

	/**
	 *        初始化
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:12:29
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void init();
	
	/**
	 *        开始
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:12:40
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void start();
	
	/**
	 *        关闭
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:12:47
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void close();
	
}
