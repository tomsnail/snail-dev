package cn.tomsnail.snail.ext.security.authority.filter.springboot;


import cn.tomsnail.snail.core.starter.spring.SpringBeanUtil;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilter;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilterException;
import cn.tomsnail.snail.ext.security.authority.filter.m.RestfulFilterManager;
import com.alibaba.dubbo.rpc.RpcException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class SpringBootFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isFilter = ConfigHelp.getInstance("config").getLocalConfig("system.security.filter",true);
        if(!isFilter){
            return true;
        }

        boolean filterType = ConfigHelp.getInstance("config").getLocalConfig("system.security.filter",true);

        RestfulFilterManager filterManager = SpringBeanUtil.getClassBean(RestfulFilterManager.class);
        List<RestfulFilter> filters = filterManager.getFilters();
        if(filters!=null){
            for(RestfulFilter filter:filters){
                boolean ok = true;
                try {
                    ok = filter.filter(request,response,handler);
                } catch (RestfulFilterException e) {
                    throw new RpcException(500, "FILTER ERROR",e);
                }
                if(!ok){
                    return false;
                }
            }
        }
        return true;
    }
}
