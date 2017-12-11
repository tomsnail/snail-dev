package cn.tomsnail.util.bean;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

public class BeanUtil {

	/**
	 * 将对象装换为map
	 * 
	 * @param bean
	 * @return
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				Object value = beanMap.get(key);
				map.put(key + "", value == null ? "" : value + "");
			}
		}
		return map;
	}
}
