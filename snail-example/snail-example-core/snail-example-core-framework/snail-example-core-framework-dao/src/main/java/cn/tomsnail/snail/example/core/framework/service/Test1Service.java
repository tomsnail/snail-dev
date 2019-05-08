package cn.tomsnail.snail.example.core.framework.service;

import cn.tomsnail.snail.core.obj.base.BaseComponent;
import com.tomsnail.framework.example.dao.DispatchStrategyDao;
import com.tomsnail.framework.example.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Test1Service extends BaseComponent {


    private DispatchStrategyDao dispatchStrategyDao;


    private TestDao testDao;




    public void test(){

        logger.info(" mysql1 {}", dispatchStrategyDao.get("1"));
        logger.info(" mysql2 {}", testDao.test());
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelayString="20000")
    @Async
    public void jdbc(){
        logger.info(" jdbcTemplate execute");
        jdbcTemplate.execute("select 'x'");

    }
}
