package cn.tomsnail.config.client.plugin;

import org.springframework.core.convert.converter.Converter;

import cn.tomsnail.config.client.ConfigClientFactory;

/**
 *        spring配置中boolean类型获取配置值
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午3:34:42
 * @see 
 */
public class BooleanSpringConverter implements Converter<String, Boolean> {

	@Override
	public Boolean convert(String source) {
		
		if(source.startsWith("${")){
			String key = source.replace("${", "").replace("}", "");
			String value = ConfigClientFactory.getInstance().getConfigClient().getConfig(key, null);
			if(value!=null){
				return  Boolean.valueOf(value);
			}
		}
		return Boolean.valueOf(source);
	}

}
