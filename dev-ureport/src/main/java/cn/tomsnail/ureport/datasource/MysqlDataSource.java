package cn.tomsnail.ureport.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.bstek.ureport.definition.datasource.BuildinDatasource;

public class MysqlDataSource extends DruidDataSource implements BuildinDatasource {
	
	private static final long serialVersionUID = -7074551103876212400L;

	public DruidPooledConnection getConnection(){
		try {
			return super.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String name() {
		return "MysqlDruidDataSource";
	}

}
