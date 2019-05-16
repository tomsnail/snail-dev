package cn.tomsnail.snail.core.config.client.plugin;

import cn.tomsnail.snail.core.config.client.ConfigClientFactory;

/**
 *        对注解上的远程配置（${url}）进行转换
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月6日 上午10:27:55
 * @see 
 */
public class AnnotationConverter {

	/**
	 *        获取值
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:31:27
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static <T,K> K getValue(T value,Class<K> clazz,K dk){
		try {
			if(value instanceof String){
				String _key = (String) value;
				if(_key.startsWith("${")){
					_key = _key.replace("${", "").replace("}", "");
					if(_key.contains("_")){
						_key = _key.replace("_", ".");
					}
					String _value = ConfigClientFactory.getInstance().getConfigClient().getConfig(_key, null);
					if(_value==null){
						return dk;
					}
					if(clazz.getName().contains("Integer")){
						return (K) Integer.valueOf(_value);
					}
					if(clazz.getName().contains("String")){
						return (K) String.valueOf(_value);
					}
					if(clazz.getName().contains("Boolean")){
						return (K) Boolean.valueOf(_value);
					}
					if(clazz.getName().contains("Long")){
						return (K) Long.valueOf(_value);
					}
					return null;
				}else{
					return (K) value;
				}
			}else{
				return (K) value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:33:46
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static <T,K> K getValue(T value,Class<K> clazz){
		return getValue(value,clazz,null);
	}
	
	/**
	 *        获取String类型值
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:33:49
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static String getValue(String value){
		return getValue(value,String.class,value);
	}
	
	/**
	 *        获取Long类型值
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:33:52
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static Long getValue(Long value){
		return getValue(value,Long.class,value);
	}
	
	/**
	 *        获取Integer类型值
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:33:55
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static Integer getValue(Integer value){
		return getValue(value,Integer.class,value);
	}
	
	/**
	 *        获取boolean类型值
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:33:59
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public static Boolean getValue(Boolean value){
		return getValue(value,Boolean.class,value);
	}
}
