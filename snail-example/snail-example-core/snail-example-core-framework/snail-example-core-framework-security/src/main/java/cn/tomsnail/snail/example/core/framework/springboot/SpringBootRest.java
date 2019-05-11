package cn.tomsnail.snail.example.core.framework.springboot;

import cn.tomsnail.core.util.validator.RuleType;
import cn.tomsnail.core.util.validator.ValidatorFactory;
import cn.tomsnail.snail.core.BaseContext;
import cn.tomsnail.snail.core.BaseContextManager;
import cn.tomsnail.snail.core.framework.validator.SnailValidator;
import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.core.obj.base.BaseWebApi;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import cn.tomsnail.snail.example.core.framework.dubbo.ModelObject;
import cn.tomsnail.snail.example.core.framework.m.Mo;
import cn.tomsnail.snail.example.core.framework.v.Vo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/springboot")
public class SpringBootRest extends BaseWebApi {


    @LogPoint
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @SnailValidator(true)
    @ResponseBody
    public ResultData<ModelObject> post(@RequestBody RequestData<ModelObject> requestData) {
        ResultData<ModelObject> modelObjectResultData = new ResultData<>();
        ModelObject modelObject = modelObjectResultData.getBody();
        modelObjectResultData.setBody(requestData.getBody());
        return modelObjectResultData;
    }

    @LogPoint
    @RequestMapping(value = "/post1", method = RequestMethod.POST)
    @SnailValidator(mapClass = Vo.class)
    @ResponseBody
    public ResultData<Map<String, Object>> post2(@RequestBody RequestData<Map<String, Object>> requestData) {


        Vo modelObject = (Vo) requestData.getTargetObj();
        logger.info("{}", modelObject);
        ResultData<Map<String, Object>> modelObjectResultData = new ResultData<>();
        modelObjectResultData.putBody("t", modelObject);
        System.out.println(BaseContextManager.get(BaseContext.USER_UUID));
        System.out.println(BaseContextManager.get("t"));
        return modelObjectResultData;
    }

}
