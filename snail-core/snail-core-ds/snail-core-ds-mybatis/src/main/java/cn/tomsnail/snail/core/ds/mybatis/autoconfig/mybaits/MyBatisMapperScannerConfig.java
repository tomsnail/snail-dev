package cn.tomsnail.snail.core.ds.mybatis.autoconfig.mybaits;


import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisMapperScannerConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage(ConfigHelp.getInstance("config").getLocalConfig("mybatis.mapper-scanners", ""));// 每张表对应的XXMapper.java interface类型的Java文件
		return mapperScannerConfigurer;
	}

}
