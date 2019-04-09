package cn.tomsnail.test.dubbo.rest.webapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.tomsnail.log.annotation.LogLevel;
import cn.tomsnail.log.annotation.LogPoint;
import cn.tomsnail.log.annotation.LogTarget;
import cn.tomsnail.test.dubbo.rest.service.DubboDemo0Service;

@RestController
@RequestMapping("/springboot")
public class SpringBootRest {
	
		@Autowired
		private DubboDemo0Service dubboDemoService;

	    
	     @LogPoint(desc = "sayWorld", level = LogLevel.INFO, target = LogTarget.LOG)
	     @RequestMapping(value = "/{name}", method = RequestMethod.GET)
	     public String sayWorld(@PathVariable("name") String name) {
	         return "Hello " + name +" "+dubboDemoService.getNowTime();
	     }
	
}
