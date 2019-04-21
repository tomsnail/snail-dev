package cn.tomsnail.snail.core.obj.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.core.util.validator.annotation.FieldValidator;


public class BaseVo<K> extends BaseModel {
	
	public Map<String,Object> getMap(K k){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			Field[] fs = this.getClass().getDeclaredFields();
			Field[] k_fs = k.getClass().getDeclaredFields();
			Map<String,Field> fMap = new HashMap<String,Field>();
			for(Field this_f:k_fs){
				fMap.put(this_f.getName(), this_f);
			}
			for(Field f:fs){
				if(f.isAnnotationPresent(FieldValidator.class)){
					FieldValidator fieldValidator = f.getAnnotation(FieldValidator.class);
					String fName = f.getName();
					if(!StringUtils.isBlank(fieldValidator.mapperName())){
						fName = fieldValidator.mapperName();
					}
					try {
						if(fMap.containsKey(f.getName())){
							Field tf = fMap.get(f.getName());
							tf.setAccessible(true);
							Object v = tf.get(k);
							f.setAccessible(true);
							f.set(this, v);
						}
					} catch (Exception e) {
					}
					try {
						String mName = f.getName();
						mName = mName.substring(0, 1).toUpperCase() + mName.substring(1);
						Method m = this.getClass().getDeclaredMethod("get"+mName, null);
						Object v = m.invoke(this, null);
						map.put(fName, v==null?"":v);
					} catch (Exception e) {
					}
				}
			}
			if(k.getClass().getSuperclass()!=null){
				Class baseModelClass = k.getClass().getSuperclass();
				if(baseModelClass.getName().equals("cn.tomsnail.obj.base.BaseModel")){
					Field f = baseModelClass.getDeclaredField("resultMap");
					f.setAccessible(true);
					Map<String,Object> resultMap = (Map<String, Object>) f.get(k);
					if(resultMap!=null&&resultMap.size()>0){
						map.putAll(resultMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public K getModel(Class<K> clazz){
		try {
			K obj = clazz.newInstance();
			Field[] fs = clazz.getDeclaredFields();
			Field[] this_fs = this.getClass().getDeclaredFields();
			Map<String,Field> fMap = new HashMap<String,Field>();
			for(Field this_f:this_fs){
				fMap.put(this_f.getName(), this_f);
			}
			for(Field f:fs){
				try {
					String fname = f.getName();
					if(fMap.containsKey(fname)){
						Field this_f = fMap.get(fname);
						this_f.setAccessible(true);
						Object v = this_f.get(this);
						if(v==null) continue;
						f.setAccessible(true);
						f.set(obj, v);
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
			try {
				page(clazz, obj,"start");
				page(clazz, obj,"limit");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return obj;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}

	private void page(Class<K> clazz, K obj,String name) throws NoSuchFieldException,
			IllegalAccessException {
		Field tf1 = this.getClass().getSuperclass().getSuperclass().getDeclaredField(name);
		Field f1 = clazz.getSuperclass().getField(name);
		tf1.setAccessible(true);
		f1.setAccessible(true);
		f1.set(obj, f1.get(this));
	}
	
	
	
}
