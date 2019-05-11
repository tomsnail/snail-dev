package cn.tomsnail.snail.ext.security.authority.filter.dubbox;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.ext.security.authority.filter.FilterContants;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilter;
import cn.tomsnail.snail.ext.security.authority.filter.plugins.AdditionalFilter;
import cn.tomsnail.snail.ext.security.authority.filter.plugins.JWTFilter;
import cn.tomsnail.snail.ext.security.authority.filter.plugins.SimpleFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "cn.tomsnail.snail.ext.security.authority.filter.m")
public class AutoDubboxConfigurer {

    @Bean
    public RestfulFilter JWT() {
        String type = ConfigHelp.getInstance("config").getLocalConfig("system.security.type", "");
        if (!type.equals("JWT")) {
            return null;
        }
        return new JWTFilter();
    }

    @Bean
    public RestfulFilter SIMPLE() {
        String type = ConfigHelp.getInstance("config").getLocalConfig("system.security.type", "");
        if (!type.equals("SIMPLE")) {
            return null;
        }
        return new SimpleFilter();
    }

    @Bean
    public RestfulFilter SNAIL() {
        String type = ConfigHelp.getInstance("config").getLocalConfig("system.security.type", "");
        if (!type.equals("SNAIL")) {
            return null;
        }
        return null;
    }

    @Bean
    public RestfulFilter Additional() {
        if (ConfigHelp.getInstance("config").getLocalConfig("system.security.additional", true)) {
            return new AdditionalFilter();
        }
        return null;
    }

}
