package cn.tomsnail.snail.ext.security.authority.filter.dubbox;

import cn.tomsnail.snail.core.BaseContextManager;
import cn.tomsnail.snail.core.starter.spring.SpringBeanUtil;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilter;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilterException;
import cn.tomsnail.snail.ext.security.authority.filter.m.RestfulFilterManager;
import com.alibaba.dubbo.rpc.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class DubboxFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {


        boolean isFilter = ConfigHelp.getInstance("config").getLocalConfig("system.security.filter", true);
        HttpServletRequest request = (HttpServletRequest) RpcContext.getContext().getRequest();
        if(isFilter){
            String url = request.getRequestURI();
            String pathPatterns = ConfigHelp.getInstance("config").getLocalConfig("system.security.path","");
            if(StringUtils.isNotBlank(pathPatterns)){
                isFilter = false;
            }
            List pathPatternList = Arrays.asList(pathPatterns.split(","));
            if(StringUtils.isNotBlank(pathPatterns)&&pathPatternList.contains(url)){
                isFilter = true;
            }
            String excludePathPatterns = ConfigHelp.getInstance("config").getLocalConfig("system.security.path.excludes","");
            List excludePathPatternList = Arrays.asList(excludePathPatterns.split(","));

            if(StringUtils.isNotBlank(excludePathPatterns)&&excludePathPatternList.contains(url)){
                isFilter = false;
            }
        }

        if(isFilter){
            HttpServletResponse response = (HttpServletResponse) RpcContext.getContext().getResponse();
            Object[] objs = RpcContext.getContext().getArguments();
            RestfulFilterManager filterManager = SpringBeanUtil.getClassBean(RestfulFilterManager.class);
            List<RestfulFilter> filters = filterManager.getFilters();
            if (filters != null) {
                for (RestfulFilter filter : filters) {
                    boolean ok = true;
                    try {
                        ok = filter.filter(request, response, objs);
                    } catch (RestfulFilterException e) {
                        throw new RpcException(500, "FILTER ERROR", e);
                    }
                    if (!ok) {
                        throw new RpcException(304, "FILTER FAILTER");
                    }
                }
            }
        }

        Result r = invoker.invoke(invocation);
        BaseContextManager.LOCAL_CONTEXT.remove();
        return r;
    }

}
