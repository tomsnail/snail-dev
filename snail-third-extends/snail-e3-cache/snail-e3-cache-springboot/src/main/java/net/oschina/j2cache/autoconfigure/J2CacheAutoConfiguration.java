package net.oschina.j2cache.autoconfigure;

import java.io.IOException;

import cn.tomsnail.snail.core.starter.spring.MixPropertySourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import net.oschina.j2cache.J2CacheBuilder;
import net.oschina.j2cache.cache.support.util.SpringJ2CacheConfigUtil;
import net.oschina.j2cache.cache.support.util.SpringUtil;
/**
 * 启动入口
 * @author zhangsaizz
 *
 */
@ConditionalOnClass(J2Cache.class)
@EnableConfigurationProperties({J2CacheConfig.class})
@Configuration
@PropertySource(value = {"classpath:config.properties","classpath:config.yml"}, encoding = "UTF-8", ignoreResourceNotFound = true,factory = MixPropertySourceFactory.class)
public class J2CacheAutoConfiguration {
	
    @Autowired
    private StandardEnvironment standardEnvironment;

    @Bean
    public net.oschina.j2cache.J2CacheConfig j2CacheConfig() throws IOException{
    	net.oschina.j2cache.J2CacheConfig cacheConfig = new net.oschina.j2cache.J2CacheConfig();
    	cacheConfig = SpringJ2CacheConfigUtil.initFromConfig(standardEnvironment);
    	return cacheConfig;
    }
    
    @Bean
    @DependsOn({"springUtil","j2CacheConfig"})
    public CacheChannel cacheChannel(net.oschina.j2cache.J2CacheConfig j2CacheConfig) throws IOException {
    	J2CacheBuilder builder = J2CacheBuilder.init(j2CacheConfig);
        return builder.getChannel();
    }

    @Bean
    public SpringUtil springUtil() {
    	return new SpringUtil();
    }

}
