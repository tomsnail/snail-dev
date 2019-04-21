package cn.tomsnail.snail.core.http.register.springmvc;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import cn.tomsnail.snail.core.config.client.plugin.AnnotationConverter;
import cn.tomsnail.snail.core.http.register.annotation.HttpRestGet;
import cn.tomsnail.snail.core.http.register.annotation.HttpRestPost;
import cn.tomsnail.snail.core.http.register.annotation.HttpRestService;
import cn.tomsnail.snail.core.http.register.core.JsonServerRegisterObject;
import cn.tomsnail.snail.core.util.host.AppName;
import cn.tomsnail.snail.core.util.host.MapIP;
import cn.tomsnail.snail.core.http.register.core.IRegister;



/**
 * 
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年11月17日 下午3:46:52
 * @see
 */

public class DefaultAnnoToObject implements IAnnoToObject {

	@Override
	public void getObjectFromAnno(Map.Entry<RequestMappingInfo, HandlerMethod> m, IRegister register,Address address) {
		RequestMappingInfo info = m.getKey();
		HandlerMethod method = m.getValue();
		PatternsRequestCondition p = info.getPatternsCondition();

		String serviceName = null;
		String group = "";
		int weight = 100;
		String version = "0.0.1";
		if (method.getMethod().isAnnotationPresent(HttpRestService.class)) {
			HttpRestService httpRestService = method.getMethod().getAnnotation(
					HttpRestService.class);
			serviceName = httpRestService.serviceName();
			serviceName = AnnotationConverter.getValue(serviceName);
			weight = httpRestService.weight();
			version = httpRestService.version();
		} else if (method.getMethod().isAnnotationPresent(HttpRestPost.class)) {
			HttpRestPost httpRestService = method.getMethod().getAnnotation(
					HttpRestPost.class);
			serviceName = httpRestService.serviceName();
			serviceName = AnnotationConverter.getValue(serviceName);
			weight = httpRestService.weight();
			version = httpRestService.version();
		} else if (method.getMethod().isAnnotationPresent(HttpRestGet.class)) {
			HttpRestGet httpRestService = method.getMethod().getAnnotation(
					HttpRestGet.class);
			serviceName = httpRestService.serviceName();
			serviceName = AnnotationConverter.getValue(serviceName);
			weight = httpRestService.weight();
			version = httpRestService.version();
		}

		if (serviceName != null) {
			for (String url : p.getPatterns()) {
				String urlStr = null;
				if (MapIP.ip == null) {
					urlStr = "http://" + address.getIp() + ":"
							+ address.getPort() + "/" + address.getContext()
							+ url;
				} else {
					urlStr = "http://" + MapIP.ip + ":" + MapIP.port + "/"
							+ address.getContext() + url;
				}
				JsonServerRegisterObject registerObject = new JsonServerRegisterObject();
				registerObject.setService(serviceName);
				registerObject.setAddUrl(urlStr);
				registerObject.setGroup(group);
				registerObject.setWeight(weight);
				registerObject.setVersion(version);
				registerObject.setAppName(AppName.AppName);
				register.register(registerObject);
			}
		}

	}


}
