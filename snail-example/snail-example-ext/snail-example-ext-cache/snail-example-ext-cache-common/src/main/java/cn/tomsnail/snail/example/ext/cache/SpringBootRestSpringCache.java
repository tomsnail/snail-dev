package cn.tomsnail.snail.example.ext.cache;

import cn.tomsnail.snail.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.ext.cache.annotation.SnailCache;
import net.oschina.j2cache.CacheChannel;

@RestController
@RequestMapping("/springcache")
@SnailCache
public class SpringBootRestSpringCache {

	@Autowired
	CacheService cacheService;

	@Autowired
	private CacheChannel cacheChannel;

	@LogPoint
	@RequestMapping(value = "put/{name}/{value}", method = RequestMethod.GET)
	public String put(@PathVariable("name") String name, @PathVariable("value") String value) {
		// ehcache.set(name, value);
		cacheChannel.set("default", name, value);
		return "OK";
	}

	@LogPoint
	@RequestMapping(value = "get/{name}", method = RequestMethod.GET)
	public String get(@PathVariable("name") String name) {
		return String.valueOf(cacheChannel.get("default", name));
	}

	@LogPoint
	@RequestMapping(value = "testbean", method = RequestMethod.GET)
	public String testbean() {
		// ehcache.set(name, value);
		return JsonUtil.toJson(cacheService.testBean());
	}
	
	@RequestMapping(value = "putbean", method = RequestMethod.GET)
	public String putbean() {
		// ehcache.set(name, value);
		return JsonUtil.toJson(cacheService.putBean());
	}

	@LogPoint
	@RequestMapping(value = "evictbean", method = RequestMethod.GET)
	public String get() {
		cacheService.evict();
		return "OK";
	}

}
