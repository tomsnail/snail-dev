package com.tomsnail.framework.example.dao;

import cn.tomsnail.snail.core.ds.router.DataSource;
import org.springframework.stereotype.Repository;

@Repository
@DataSource("mysql2")
public interface TestDao {

    public Integer test();
}
