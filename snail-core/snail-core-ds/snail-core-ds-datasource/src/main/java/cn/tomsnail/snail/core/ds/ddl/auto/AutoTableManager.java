package cn.tomsnail.snail.core.ds.ddl.auto;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.configfile.ProgramPathHelper;
import cn.tomsnail.snail.core.util.string.StringUtils;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class AutoTableManager {

    private final Logger logger = LoggerFactory.getLogger(AutoTableManager.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @PostConstruct
    public void runSql() throws Exception {
        logger.info("★★★★ initTable start!!");
        boolean ddl = ConfigHelp.getInstance("config").getLocalConfig("system.db.auto.ddl",false);
        logger.info("AutoTableManager ddl [{}]",ddl);
        if(!ddl) return;
        String sqlPathStr = ConfigHelp.getInstance("config").getLocalConfig("system.db.auto.sql-paths","./sql/init.sql");
        logger.info("AutoTableManager sqlPathStr [{}]",sqlPathStr);
        if(StringUtils.isBlank(sqlPathStr)) return;
        String[] sqlPaths = sqlPathStr.split(",");

        // 正式库刷表
        logger.info("JdbcTemplate = {}", jdbcTemplate);
        for (String sqlPath : sqlPaths)
        {
            File f = new File(sqlPath);
            if(!f.exists()){
                f = new File(ProgramPathHelper.getProjectPath()+sqlPath);
                if(!f.exists()){
                    continue;
                }
            }
            try (InputStream inputStream = new FileInputStream(f))
            {
                if(inputStream==null){
                    logger.info("inputStream is null ");
                    return;
                }
                String sqlText = IOUtils.toString(inputStream, "utf-8");
                logger.info("SQL = {}", sqlText);
                DataSource ds = jdbcTemplate.getDataSource();
                if(ds instanceof DruidDataSource){
                    DruidDataSource druidDataSource = (DruidDataSource) ds;
                    if (druidDataSource.getUrl().contains("allowMultiQueries=true"))
                    {
                        logger.info("开始执行当前的初始化语句块");
                        jdbcTemplate.execute(sqlText);
                        logger.info("★★★★ initTable success!!");
                        return;
                    }
                }
                logger.info("开始分割执行当前的初始化语句块");
                for (String sql : sqlText.split(";"))
                {
                    if (StringUtils.isNotBlank(sql))
                    {
                        jdbcTemplate.execute(sql);
                    }
                }
            }
            logger.info("★★★★ initTable success!!");
        }

    }
}
