package cn.tomsnail.snail.core.ds.mybatis.autoconfig.mybaits;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * The type My batis config.
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig  implements TransactionManagementConfigurer{

    /**
     * The Data source.
     */
    @Autowired
    DataSource dataSource;

    /**
     * Sql session factory bean sql session factory.
     *
     * @return the sql session factory
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(ConfigHelp.getInstance("config").getLocalConfig("mybatis.type-aliases-package", ""));
        
        

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources(ConfigHelp.getInstance("config").getLocalConfig("mybatis.mapper-locations", "")));
            bean.setConfigLocation(resolver.getResource(ConfigHelp.getInstance("config").getLocalConfig("mybatis.config-location", "classpath:mybatis-config.xml")));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Sql session template sql session template.
     *
     * @param sqlSessionFactory the sql session factory
     * @return the sql session template
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
