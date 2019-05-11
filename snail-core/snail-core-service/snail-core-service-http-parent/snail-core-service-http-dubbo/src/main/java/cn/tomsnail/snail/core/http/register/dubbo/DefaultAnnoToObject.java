package cn.tomsnail.snail.core.http.register.dubbo;

import java.lang.reflect.Method;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.URL;

import cn.tomsnail.snail.core.config.client.plugin.AnnotationConverter;
import cn.tomsnail.snail.core.http.register.annotation.HttpRestGet;
import cn.tomsnail.snail.core.http.register.annotation.HttpRestPost;
import cn.tomsnail.snail.core.http.register.annotation.HttpRestService;
import cn.tomsnail.snail.core.http.register.annotation.HttpRestServiceDubbo;
import cn.tomsnail.snail.core.http.register.core.IRegister;
import cn.tomsnail.snail.core.http.register.core.JsonServerRegisterObject;
import cn.tomsnail.snail.core.util.host.AppName;
import cn.tomsnail.snail.core.util.host.MapIP;



/**
 * 
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年11月17日 下午3:46:52
 * @see
 */

public class DefaultAnnoToObject implements IAnnoToObject {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DefaultAnnoToObject.class);

	@Override
	public void getObjectFromAnno(Method m, Path rootpath, URL url,
			HttpRestServiceDubbo httpRestServiceDubbo, IRegister register) {

		String serviceName = null;
		String group = "";
		int weight = 100;
		String version = "0.0.1";
		if (m.isAnnotationPresent(HttpRestService.class)) {
			HttpRestService httpRestService = m
					.getAnnotation(HttpRestService.class);
			serviceName = httpRestService.serviceName();
			serviceName = AnnotationConverter.getValue(serviceName);
			group = httpRestService.group().equals("default") ? httpRestServiceDubbo
					.group() : httpRestService.group();
			weight = httpRestService.weight();
			version = httpRestService.version();
		}else
		if (m.isAnnotationPresent(HttpRestPost.class)) {
			HttpRestPost httpRestService = m.getAnnotation(HttpRestPost.class);
			serviceName = httpRestService.serviceName();
			serviceName = AnnotationConverter.getValue(serviceName);
			group = httpRestService.group().equals("default") ? httpRestServiceDubbo
					.group() : httpRestService.group();
			weight = httpRestService.weight();
			version = httpRestService.version();
		}else
		if (m.isAnnotationPresent(HttpRestGet.class)) {
			HttpRestGet httpRestService = m.getAnnotation(HttpRestGet.class);
			serviceName = httpRestService.serviceName();
			serviceName = AnnotationConverter.getValue(serviceName);
			group = httpRestService.group().equals("default") ? httpRestServiceDubbo
					.group() : httpRestService.group();
			weight = httpRestService.weight();
			version = httpRestService.version();
		}
		if(serviceName!=null){
			Path p = m.getAnnotation(Path.class);
			String urlStr = null;
			if (MapIP.ip == null) {
				urlStr = "http://" + url.getAddress() + "/" + rootpath.value()
						+ "/" + p.value();
			} else {
				urlStr = "http://" + MapIP.ip + ":" + MapIP.port + "/"
						+ rootpath.value() + "/" + p.value();
			}
			JsonServerRegisterObject registerObject = new JsonServerRegisterObject();
			registerObject.setService(serviceName);
			registerObject.setAddUrl(urlStr);
			registerObject.setGroup(group);
			registerObject.setWeight(weight);
			registerObject.setAppName(AppName.AppName);
			registerObject.setVersion(version);
			register.register(registerObject);
			LOGGER.info("RegisterFactory " + serviceName + ":" + urlStr + ":"
					+ group);
		}
		

	}

}
