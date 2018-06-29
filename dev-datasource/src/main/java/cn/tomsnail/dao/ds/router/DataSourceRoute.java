package cn.tomsnail.dao.ds.router;

import org.aspectj.lang.JoinPoint;

import cn.tomsnail.dao.ds.DataSourceName;

public interface DataSourceRoute {
	
	public DataSourceName route(JoinPoint point,String routeType,DataSourceName dataSource);
	
}
