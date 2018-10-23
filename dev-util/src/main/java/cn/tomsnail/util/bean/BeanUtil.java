package cn.tomsnail.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class BeanUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);
	
	
	public static <T> void changeListToBean(List<Map<String,Object>> datalist,List<T> beanList,Class<T> beanClass){
		
		if(beanList==null){
			throw new NullPointerException("beanList is null");
		}
		
		if(datalist==null){
			throw new NullPointerException("datalist is null");
		}
		
		for (Map<String,Object> map : datalist) {
			try {
				T bean = (T) getBeanFromMap(map,beanClass );
				beanList.add(bean);
			} catch (Exception e) {
				LOGGER.error("",e);
			}
		}
	}

	/**
	 * 将对象装换为map
	 * 
	 * @param bean
	 * @return
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (bean != null) {
			net.sf.cglib.beans.BeanMap beanMap = net.sf.cglib.beans.BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				Object value = beanMap.get(key);
				map.put(key + "", value == null ? "" : value + "");
			}
		}
		return map;
	}
	
	public static <T> Map<?,?> getMapFromBean(T bean){
		
		if(bean!=null){
			try {
				return new BeanMap(bean);
			} catch (Exception e) {
			} 
		}
		return null;
		
	}
	
	public static <T> T getBeanFromMap(Map<String,?> map,Class<T> beanClass){
		
		if(map!=null){
			try {
				T obj = beanClass.newInstance();  
				BeanUtils.populate(obj, map);
				return obj;
			} catch (IllegalAccessException | InvocationTargetException  | InstantiationException e) {
				throw new RuntimeException(e.getMessage());
			} 
			
		}
		
		return null;
	}
	
	
	public static <T,E> void mergeBean(T src,E dest){
		
		if(src==null||dest==null){
			return;
		}
		try {
			BeanUtils.copyProperties(dest, src);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error("", e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T cloneBean(T bean){
		
		if(bean!=null){
			try {
				return (T) BeanUtils.cloneBean(bean);
			} catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
				LOGGER.error("", e);
			}
		}
		
		return null;
	}
	
	public static <T> String getBeanInfo(T bean){
		
		if(bean!=null){
			try {
				return JSON.toJSONString(BeanUtils.describe(bean),SerializerFeature.WriteMapNullValue);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				LOGGER.error("", e);
			}
		}
		
		return null;
	}
	
	public static boolean isAllFieldNull(Object obj) {
		if(obj==null){
			return false;
		}
		Class stuCla = (Class)obj.getClass();
		Field[] fs = stuCla.getDeclaredFields();
		boolean flag = true;
		for (Field f : fs) {
		    f.setAccessible(true);
			Object val;
			try {
				val = f.get(obj);
				if(val!=null) {
					 flag = false;
					 break;
					 }
			} catch (IllegalArgumentException | IllegalAccessException e) {
				
			}
		}
		return flag;
	}
}
