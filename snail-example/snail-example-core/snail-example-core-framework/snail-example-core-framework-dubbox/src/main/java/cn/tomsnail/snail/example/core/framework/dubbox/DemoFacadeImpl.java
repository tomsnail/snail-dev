package cn.tomsnail.snail.example.core.framework.dubbox;

import cn.tomsnail.core.util.validator.ValidatorFactory;
import cn.tomsnail.snail.core.framework.validator.SnailValidator;
import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.log.annotation.LogPoint;

import cn.tomsnail.snail.core.obj.base.BaseWebApi;
import cn.tomsnail.snail.core.util.JsonUtil;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import cn.tomsnail.snail.example.core.framework.dubbo.ModelObject;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("test")
@Service(validation = "true")
public class DemoFacadeImpl extends BaseWebApi implements DemoFacade{
	
	
    @Reference
	private DemoService demoService;

    @GET
    @Path("get/{no}")
    @Produces({MediaType.APPLICATION_JSON})
    @LogPoint
    public ResultData testGet(@PathParam("no")String no) {
        ResultData resultData = new ResultData();
        resultData.putBody("no",demoService.hello(no));
        return resultData;
    }

    @POST
    @Path("post")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @LogPoint
    @SnailValidator
    public ResultData<ModelObject> testPost(RequestData<ModelObject> requestData) {
        ResultData<ModelObject> modelObjectResultData = new ResultData<>();

        modelObjectResultData.setBody(requestData.getBody());
        return modelObjectResultData;
    }

    @POST
    @Path("post1")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @LogPoint
    @SnailValidator(mapClass = Vo.class)
    public ResultData<Map<String, Object>> testPost1(RequestData<Map<String, Object>> requestData) {
        Vo modelObject = (Vo) requestData.getTargetObj();
        logger.info("{}",modelObject);
        ResultData<Map<String, Object>> modelObjectResultData = new ResultData<>();
        modelObjectResultData.putBody("t",modelObject);
        return modelObjectResultData;
    }



}
