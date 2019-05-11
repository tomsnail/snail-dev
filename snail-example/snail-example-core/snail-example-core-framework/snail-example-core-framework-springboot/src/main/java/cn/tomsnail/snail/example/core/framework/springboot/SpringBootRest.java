package cn.tomsnail.snail.example.core.framework.springboot;

import cn.tomsnail.core.util.validator.RuleType;
import cn.tomsnail.core.util.validator.ValidatorFactory;
import cn.tomsnail.snail.core.framework.validator.SnailValidator;
import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.core.obj.base.BaseWebApi;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import com.alibaba.dubbo.config.annotation.Reference;
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

        ModelObject modelObject = modelObjectResultData.getBody();

        ValidatorFactory.getBeanValidator().valid(modelObject);



        modelObjectResultData.setBody(requestData.getBody());
        return modelObjectResultData;
    }

    @LogPoint
    @RequestMapping(value = "/post1", method = RequestMethod.POST)
    @SnailValidator(mapClass = Vo.class)
    @ResponseBody
    public ResultData<Map<String, Object>> post2(@RequestBody RequestData<Map<String, Object>> requestData) {

        Vo vo = (Vo) ValidatorFactory.getBeanValidator().getValidBean(requestData.getBody(),Vo.class);

        Mo mo = vo.getModel(Mo.class);

        vo.getMap(mo);


        ValidatorFactory.getHibernateObjectValidator().getValidBean(requestData.getBody(),Vo.class);

        ValidatorFactory.getStringValidator().getValidatorValue(requestData.getBody(),"ID");
        ValidatorFactory.getIntegerValidator().getValidatorValue(requestData.getBody(),"ID","id不能为空");
        ValidatorFactory.getStringValidator().add(RuleType.NotNull).add(RuleType.LIMITMAX,20).getValidatorValue(requestData.getBody(),"ID");



        Vo modelObject = (Vo) requestData.getTargetObj();
        logger.info("{}", modelObject);
        ResultData<Map<String, Object>> modelObjectResultData = new ResultData<>();
        modelObjectResultData.putBody("t", modelObject);
        return modelObjectResultData;
    }

}
