package cn.tomsnail.snail.core.config.client.plugin;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Configuration
public class CustomerConversionService{


	@Bean(name="conversionService")
	public ConversionServiceFactoryBean init(){
		ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		BooleanSpringConverter booleanSpringConverter = new BooleanSpringConverter();
		IntegerSpringConverter integerSpringConverter = new IntegerSpringConverter();
		LongSpringConverter longSpringConverter = new LongSpringConverter();
		StringSpringConverter stringSpringConverter = new StringSpringConverter();
		Set<Converter> converters = new LinkedHashSet<Converter>();
		converters.add(stringSpringConverter);
		converters.add(longSpringConverter);
		converters.add(integerSpringConverter);
		converters.add(booleanSpringConverter);
		conversionServiceFactoryBean.setConverters(converters);
		return conversionServiceFactoryBean;
	}




	
}
