package cn.tomsnail.snail.core.config.client;

import java.lang.reflect.Field;

/**
 *        默认配置变化监听
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月21日 下午12:15:18
 * @see 
 */
public class DefaultConfigChangeListener implements IConfigChangeListener{
		
	@Override
	public void changed() {
		try {
			Field[] fs = this.getClass().getDeclaredFields();
			for(Field f:fs){
				if(!f.getType().getName().equals(String.class.getName())){
					continue;
				}
				String key = f.getName();
				String ckey = key.replace("_", ".");
				String oldValue = (String) f.get(this);
				String value = ConfigClientFactory.getInstance().getConfigClient().getConfig(ckey,oldValue);
				f.set(this,value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
