package cn.tomsnail.snail.core;


/**
 *  SPI核心内容      
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月5日 下午3:05:57
 * @see 
 */
public interface SpiCoreContextHolder {

	/**
	 *        获取扩展类
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:37:15
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public  String getSpiClass(String spi);
	
	  
	   /**
		* 获取扩展类，默认类对象class name
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2016年11月17日 下午1:44:11
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public  String getSpiClass(String spi,String defaultObj);
	
	/**
	 *        是否开启扩展
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:37:29
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public  boolean isDefaultSPI();
	
	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2016年11月17日 下午2:52:39
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
		
	public Object getSpiObject(String clazz,Object[] params,Class[] paramTypes);
	
	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2016年11月17日 下午2:52:41
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
		
	public Object getSpiObject(String clazz);
	
	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2016年11月17日 下午4:03:43
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
		
	public Object getSpiObjectFromSpiObject(String spi,String defaultObj);
	
}
