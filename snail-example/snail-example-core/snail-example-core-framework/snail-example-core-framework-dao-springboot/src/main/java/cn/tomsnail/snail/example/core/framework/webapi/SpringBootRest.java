package cn.tomsnail.snail.example.core.framework.webapi;

import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/springboot")
public class SpringBootRest {
	

		@Autowired
		private DemoService demoService;
	    
	     @LogPoint
	     @RequestMapping(value = "/{name}", method = RequestMethod.GET)
	     public String sayHello(@PathVariable("name") String name) {
	         return demoService.hello(name);
	     }
	
}
