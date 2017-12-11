package cn.tomsnail.hot.load.m;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.stereotype.Component;

import cn.tomsnail.hot.load.annotation.HotLoadObject;
import cn.tomsnail.hot.load.annotation.HotLoadType;
import cn.tomsnail.hot.load.file.RtFileCallBack;
import cn.tomsnail.hot.load.file.RtFileListener;
import cn.tomsnail.hot.load.spring.SpringClassLoadBean;
import cn.tomsnail.hot.load.spring.SpringHotLoadInit;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月22日 上午10:38:26
	* @see 
	*/     
 
@Component
public class HotLoadManager {
	
	private static final Map<String,Object> ObjectMap = new ConcurrentHashMap<String, Object>();
	
	public static final Map<String,List<String>> ClassMapList = new ConcurrentHashMap<String, List<String>>();

	public void start(String path,long interval,RtFileCallBack fileCallBack){
		try {
			new RtFileListener(path,interval,fileCallBack).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setValue(HotLoadObject hotLoadObject,Field f,Object obj){
		String name = hotLoadObject.name().equals("")?f.getName():hotLoadObject.name();
		if(ClassMapList.containsKey(name)){
			if(hotLoadObject.type().equals(HotLoadType.singleton)){
				List<String> classList = ClassMapList.get(name);
				if(classList!=null&&classList.size()>0){
					try {
						if(hotLoadObject.isBean()){
							SpringClassLoadBean.load(SpringHotLoadInit.getInstance().getApplicationContext(), Class.forName(classList.get(0)), name);
						}else{
							f.set(obj, Class.forName(classList.get(0)).newInstance());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if(hotLoadObject.type().equals(HotLoadType.list)){
				List<String> classList = ClassMapList.get(name);
				if(classList!=null&&classList.size()>0){
					try {
						List list = new CopyOnWriteArrayList();
						List l = (List) f.get(obj);
						if(l==null||l.size()==0){
							l = new CopyOnWriteArrayList();
							for(String clazz:classList){
								l.add(Class.forName(clazz).newInstance());
							}
						}else{
							for(String clazz:classList){
								list.add(Class.forName(clazz).newInstance());
							}
							f.set(obj, list);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			ClassMapList.put(name, new CopyOnWriteArrayList());
		}
	}
	
	
	
	public void setValue(Object obj){
		Field[] fs = obj.getClass().getDeclaredFields();
		for(Field f:fs){
			if(f.isAnnotationPresent(HotLoadObject.class)){
				f.setAccessible(true);
				try {
					HotLoadObject hotLoadObject = f.getAnnotation(HotLoadObject.class);
					String name = hotLoadObject.name().equals("")?f.getName():hotLoadObject.name();
					this.setValue(hotLoadObject, f,obj);
					ObjectMap.put(name,obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void update(String name){
		Object obj = ObjectMap.get(name);
		if(obj!=null){
			setValue(obj);
		}
	}
	
}
