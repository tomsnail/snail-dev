package cn.tomsnail.snail.core.ds.router;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.ds.DataSourceName;
import cn.tomsnail.snail.core.ds.RountingDataSource;



@Component
public class AutoWriteReadRoute implements DataSourceRoute{
	
	
	public static final String read = "read";
	
	public static final String write = "write";
	
	

	@Override
	public DataSourceName route(JoinPoint point,String routeType,DataSourceName dataSource) {
		
		if(dataSource==null||StringUtils.isBlank(dataSource.getName())){
			return null;
		}
		
		String method = point.getSignature().getName();
		
		if(RountingDataSource.ROUTE_AUTO_WR.equals(routeType)){
       	 if(method.startsWith("find")||method.startsWith("get")||method.startsWith("query")||method.startsWith("count")||method.startsWith("page")){
       		return new DataSourceName("read",20);
       	 }
       	 if(method.startsWith("save")||method.startsWith("insert")||method.startsWith("delete")||method.startsWith("del")||method.startsWith("update")){
       		 return new DataSourceName("write",20);
       	 }
        }
		return null;
	}





	
	
	
	

}
