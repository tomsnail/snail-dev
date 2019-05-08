package cn.tomsnail.snail.example.core.framework.springboot;

import cn.tomsnail.snail.core.framework.validator.SnailValidator;
import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.core.obj.base.BaseWebApi;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/springboot")
public class SpringBootRest extends BaseWebApi {
	

		@Reference
		private DemoService demoService;
	    
	     @LogPoint
	     @RequestMapping(value = "/{name}", method = RequestMethod.GET)
	     public String sayHello(@PathVariable("name") String name) {
	         return demoService.hello(name);
	     }


	@LogPoint
	@RequestMapping(value = "/post/object", method = RequestMethod.POST)
	public String postObject(@PathVariable("name") String name) {
		return demoService.hello(name);
	}

	@LogPoint
	@RequestMapping(value = "/post/map", method = RequestMethod.POST)
	public String postMap(@PathVariable("name") String name) {
		return demoService.hello(name);
	}


	@LogPoint
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	@SnailValidator
	@ResponseBody
	public ResultData<ModelObject> post(@RequestBody RequestData<ModelObject> requestData) {
		ResultData<ModelObject> modelObjectResultData = new ResultData<>();

		modelObjectResultData.setBody(requestData.getBody());
		return modelObjectResultData;
	}

	@LogPoint
	@RequestMapping(value = "/post1", method = RequestMethod.POST)
	@SnailValidator(mapClass = Vo.class)
	@ResponseBody
	public ResultData<Map<String, Object>> post2(@RequestBody RequestData<Map<String, Object>> requestData) {
		Vo modelObject = (Vo) requestData.getTargetObj();
		logger.info("{}",modelObject);
		ResultData<Map<String, Object>> modelObjectResultData = new ResultData<>();
		modelObjectResultData.putBody("t",modelObject);
		return modelObjectResultData;
	}
	
}
