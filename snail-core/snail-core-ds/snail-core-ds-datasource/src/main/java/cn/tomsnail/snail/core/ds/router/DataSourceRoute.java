package cn.tomsnail.snail.core.ds.router;

import org.aspectj.lang.JoinPoint;

import cn.tomsnail.snail.core.ds.DataSourceName;


public interface DataSourceRoute {
	
	public DataSourceName route(JoinPoint point,String routeType,DataSourceName dataSource);
	
}
