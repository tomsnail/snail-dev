package cn.tomsnail.dubbo.restful.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tomsnail.framwork.core.BaseContextManager;
import cn.tomsnail.starter.domain.spring.SpringBeanUtil;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;

public class CommonFilter implements Filter{

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		 HttpServletRequest request = (HttpServletRequest)RpcContext.getContext().getRequest();  
	     HttpServletResponse response = (HttpServletResponse)RpcContext.getContext().getResponse();  
	     Object[] objs = RpcContext.getContext().getArguments();
	     
	     RestfulFilterManager filterManager = SpringBeanUtil.getClassBean(RestfulFilterManager.class	);
	     List<RestfulFilter> filters = filterManager.getFilters();
	     if(filters!=null){
	    	 for(RestfulFilter filter:filters){
	    		 boolean ok = true;
	    		 try {
	    			ok = filter.filter(request, response,objs);
				} catch (RestfulFilterException e) {
					 throw new RpcException(500, "FILTER ERROR",e);
				}
	    		 if(!ok){
					 throw new RpcException(304, "FILTER FAILTER");
				 }
	    	 }
	     }
	     
	     Result r = invoker.invoke(invocation); 	     
	     BaseContextManager.LOCAL_CONTEXT.remove();
	     return  r;
	}

}
