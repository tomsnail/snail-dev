package cn.tomsnail.snail.example.gen.ex.springboot;

import cn.tomsnail.snail.example.gen.ex.api.DispatchUrlMo;
import cn.tomsnail.snail.example.gen.ex.api.DispatchUrlService;
import cn.tomsnail.snail.example.gen.ex.vo.DispatchUrlVo;
import cn.tomsnail.snail.core.framework.validator.SnailValidator;
import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.log.annotation.LogPoint;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/DispatchUrl")
public class DispatchUrlSpringBoot  {

	@Autowired
	@Reference(check = false)
	private DispatchUrlService dispatchUrlService;

	@LogPoint
	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	@SnailValidator
	@ResponseBody
	public ResultData<Map<String,Object>> findList(@RequestBody RequestData<DispatchUrlVo> dispatchUrlMoRequestData) {
		ResultData<Map<String,Object>> resultData = new ResultData<>();
		resultData.putBody("data",dispatchUrlService.findList(dispatchUrlMoRequestData.getBody().getModel(DispatchUrlMo.class)));
		return resultData;
	}



	@LogPoint
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@SnailValidator
	@ResponseBody
	public ResultData<Map<String,Object>> save(@RequestBody RequestData<DispatchUrlVo> dispatchUrlMoRequestData) {
		ResultData<Map<String,Object>> resultData = new ResultData<>();
		resultData.putBody("data",dispatchUrlService.save(dispatchUrlMoRequestData.getBody().getModel(DispatchUrlMo.class)));
		return resultData;
	}



	@LogPoint
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@SnailValidator
	@ResponseBody
	public ResultData<Map<String,Object>> delete(@RequestBody RequestData<DispatchUrlVo> dispatchUrlMoRequestData) {
		ResultData<Map<String,Object>> resultData = new ResultData<>();
		resultData.putBody("data",dispatchUrlService.delete(dispatchUrlMoRequestData.getBody().getModel(DispatchUrlMo.class).getId()));
		return resultData;
	}


	@LogPoint
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@SnailValidator
	@ResponseBody
	public ResultData<Map<String,Object>> update(@RequestBody RequestData<DispatchUrlVo> dispatchUrlMoRequestData) {
		ResultData<Map<String,Object>> resultData = new ResultData<>();
		resultData.putBody("data",dispatchUrlService.update(dispatchUrlMoRequestData.getBody().getModel(DispatchUrlMo.class)));
		return resultData;
	}



}