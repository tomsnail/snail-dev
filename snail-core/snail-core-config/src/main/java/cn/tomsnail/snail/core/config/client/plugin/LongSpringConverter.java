package cn.tomsnail.snail.core.config.client.plugin;

import org.springframework.core.convert.converter.Converter;

import cn.tomsnail.snail.core.config.client.ConfigClientFactory;



/**
 *        spring配置中Long类型获取配置值
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午3:36:20
 * @see 
 */
public class LongSpringConverter implements Converter<String, Long> {

	@Override
	public Long convert(String source) {
		if(source.startsWith("${")){
			String key = source.replace("${", "").replace("}", "");
			String value = ConfigClientFactory.getInstance().getConfigClient().getConfig(key, null);
			if(value!=null){
				return  Long.valueOf(value);
			}
		}
		return Long.valueOf(source);
	}

}
