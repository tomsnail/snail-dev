package cn.tomsnail.snail.core.config.client;

import java.lang.reflect.Field;

import cn.tomsnail.snail.core.config.client.annotation.ConfigListener;
import cn.tomsnail.snail.core.config.client.annotation.FieldListener;
import cn.tomsnail.snail.core.config.client.annotation.Scope;





/**
 *        注解类的配置监听
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月22日 下午5:28:39
 * @see 
 */
public class AnnotationConfigListener implements IConfigChangeListener{

	/**
	 * 类名
	 */
	private static String className;
	
	public AnnotationConfigListener(){
		
	}

	public String getClassName() {
		return className;
	}

	public static void className(String className){
		AnnotationConfigListener.className = className;
	}

	public void setClassName(String className) {
		className(className);
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *        初始化配置
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午3:43:25
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void init() throws Exception{
		Class clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(clazz!=null){
			if(clazz.isAnnotationPresent(ConfigListener.class)){
				ConfigListener configListener = (ConfigListener) clazz.getAnnotation(ConfigListener.class);
				Object o = clazz.newInstance();
				Field[] fs = clazz.getDeclaredFields();
				for(Field f:fs){
					if(!f.getType().getName().equals(String.class.getName())){
						continue;
					}
					if(configListener.scope().equals(Scope.SPEC)){
						if(!f.isAnnotationPresent(FieldListener.class)){
							continue;
						}
					}
					String key = f.getName();
					String ckey = key.replace("_", ".");
					String oldValue = (String) f.get(o);
					String value = ConfigClientFactory.getInstance().getConfigClient(this.getClass()).getConfig(ckey,oldValue);
					f.set(o,value);
				}
			}
		}
	}
	
	

	@Override
	public void changed() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
