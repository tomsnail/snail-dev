package cn.tomsnail.snail.ext.security.authority.filter.springboot;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.ext.security.authority.filter.FilterContants;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilter;
import cn.tomsnail.snail.ext.security.authority.filter.plugins.AdditionalFilter;
import cn.tomsnail.snail.ext.security.authority.filter.plugins.JWTFilter;
import cn.tomsnail.snail.ext.security.authority.filter.plugins.SimpleFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan(basePackages = "cn.tomsnail.snail.ext.security.authority.filter.m")
public class AutoWebMvcConfigurer implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new SpringBootFilter());
        String pathPatterns = ConfigHelp.getInstance("config").getLocalConfig("system.security.path","");
        if(StringUtils.isNotBlank(pathPatterns)){
            ir.addPathPatterns(pathPatterns.split(","));
        }
        String excludePathPatterns = ConfigHelp.getInstance("config").getLocalConfig("system.security.path.excludes","");
        if(StringUtils.isNotBlank(excludePathPatterns)){
            ir.excludePathPatterns(excludePathPatterns.split(","));
        }
    }

    @Bean
    @ConditionalOnProperty(value = "system.security.type",havingValue = FilterContants.JWT)
    public RestfulFilter JWT(){
        return new JWTFilter();
    }

    @Bean
    @ConditionalOnProperty(value = "system.security.type",havingValue = FilterContants.SIMPLE)
    public RestfulFilter SIMPLE(){
        return new SimpleFilter();
    }

    @Bean
    @ConditionalOnProperty(value = "system.security.type",havingValue = FilterContants.SNAIL)
    public RestfulFilter SNAIL(){
        return null;
    }

    @Bean
    @ConditionalOnProperty(value = "system.security.additional",havingValue = "true")
    public RestfulFilter Additional(){
        return new AdditionalFilter();
    }
}
