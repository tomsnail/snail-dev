package cn.tomsnail.snail.example.ext.cache;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.ext.cache.annotation.SnailCache;
import cn.tomsnail.snail.ext.cache.annotation.CacheConfig;
import cn.tomsnail.snail.ext.cache.core.ICache;

//@RestController
//@RequestMapping("/ehcache3")
//@SnailCache
public class SpringBootRestEhcache3 {


	
		
		 @CacheConfig(cachedType="EHCACHE3",name="test")
		 ICache ehcache;

		
	    
	     @LogPoint
	     @RequestMapping(value = "put/{name}/{value}", method = RequestMethod.GET)
	     public String put(@PathVariable("name") String name,@PathVariable("value") String value) {
	    	 ehcache.set(name, value);
	    	 return "OK";
	     }
	     
	     @LogPoint
	     @RequestMapping(value = "pute/{name}/{value}", method = RequestMethod.GET)
	     public String putExpire(@PathVariable("name") String name,@PathVariable("value") String value) {
	    	 ehcache.set(name, value, 3);
	    	 return "OK";
	     }
	     
	     
	     @LogPoint
	     @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
	     public String get(@PathVariable("name") String name) {
	        return  String.valueOf(ehcache.get(name));
	     }
	
}
