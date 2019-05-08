package cn.tomsnail.snail.core.ds.router;

import org.aspectj.lang.JoinPoint;


public interface DataSourceRoute {
	
	public DataSourceName route(JoinPoint point,String routeType,DataSourceName dataSource);
	
}
