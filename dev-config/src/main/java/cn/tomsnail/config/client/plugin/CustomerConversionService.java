package cn.tomsnail.config.client.plugin;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("conversionService")
public class CustomerConversionService extends ConversionServiceFactoryBean{

	public CustomerConversionService(){
		BooleanSpringConverter booleanSpringConverter = new BooleanSpringConverter();
		IntegerSpringConverter integerSpringConverter = new IntegerSpringConverter();
		LongSpringConverter longSpringConverter = new LongSpringConverter();
		StringSpringConverter stringSpringConverter = new StringSpringConverter();
		Set<Converter> converters = new LinkedHashSet<Converter>();
		converters.add(stringSpringConverter);
		converters.add(longSpringConverter);
		converters.add(integerSpringConverter);
		converters.add(booleanSpringConverter);
		setConverters(converters);
	}
	
}
