package cn.tomsnail.snail.ext.security.authority.filter.plugins;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilter;
import cn.tomsnail.snail.ext.security.authority.filter.RestfulFilterException;


import cn.tomsnail.snail.core.BaseContext;
import cn.tomsnail.snail.core.BaseContextManager;



public class AdditionalFilter implements RestfulFilter {

	private String urlParamters = ConfigHelp.getInstance("config").getLocalConfig("system.core.http.add.flags","");

	private String valueKeys = ConfigHelp.getInstance("config").getLocalConfig("system.core.http.add.keys","");

	public boolean filter( HttpServletRequest request,HttpServletResponse response,Object[] args) throws RestfulFilterException {
		
		BaseContext context = new BaseContext()
				.addMapValue(BaseContext.USER_UUID, request.getParameter(BaseContext.USER_UUID))
				.addMapValue(BaseContext.SYSTEM_CODE, request.getParameter(BaseContext.SYSTEM_CODE));
		if(!StringUtils.isAnyBlank(urlParamters,valueKeys)){
			String[] paramters = urlParamters.split(",");
			String[] keys = valueKeys.split(",");
			if(paramters.length==keys.length){
				for(int i=0;i<paramters.length;i++){
					context.addMapValue(keys[i],request.getParameter(paramters[i]));
				}
			}
		}
		BaseContextManager.LOCAL_CONTEXT.set(context);
		return true;
	}

	public String getUrlParamters() {
		return urlParamters;
	}

	public void setUrlParamters(String urlParamters) {
		this.urlParamters = urlParamters;
	}

	public String getValueKeys() {
		return valueKeys;
	}

	public void setValueKeys(String valueKeys) {
		this.valueKeys = valueKeys;
	}
}
