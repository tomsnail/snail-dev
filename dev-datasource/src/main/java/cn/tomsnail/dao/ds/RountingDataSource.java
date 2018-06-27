package cn.tomsnail.dao.ds;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RountingDataSource extends AbstractRoutingDataSource{
	
	public static final String ROUTE_WR = "WR";
	
	public static final String ROUTE_AUTO_WR = "AUTO_WR";
	
	public static final String ROUTE_MS = "MS";
	
	public static final String ROUTE_MULTI_TYPE_DB = "MULTI_TYPE_DB";
	
	public static final String ROUTE_GROUP_MS = "GROUP_MS";	
	
	static String routeType = null;

	@Override
	protected Object determineCurrentLookupKey() {
		return HandleDataSource.getDataSource();  
	}
	
	@Override
	protected DataSource determineTargetDataSource() {
		return super.determineTargetDataSource();
	}
	
	@Override
	public void afterPropertiesSet(){
		if(routeType==null||routeType.equals("")){
			routeType = "ROUTE_WR";
		}
		super.afterPropertiesSet();
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	
	

}
