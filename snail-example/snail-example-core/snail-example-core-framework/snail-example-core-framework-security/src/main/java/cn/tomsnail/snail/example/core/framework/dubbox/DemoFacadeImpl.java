package cn.tomsnail.snail.example.core.framework.dubbox;

import cn.tomsnail.snail.core.BaseContext;
import cn.tomsnail.snail.core.BaseContextManager;
import cn.tomsnail.snail.core.framework.validator.SnailValidator;
import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.core.obj.base.BaseWebApi;
import cn.tomsnail.snail.core.util.JsonUtil;
import cn.tomsnail.snail.example.core.framework.dubbo.ModelObject;
import cn.tomsnail.snail.example.core.framework.v.Vo;
import com.alibaba.dubbo.config.annotation.Service;

//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
import java.util.Map;

//@Path("dubbox")
@Service(validation = "true",filter = "tomsnail")
public class DemoFacadeImpl extends BaseWebApi implements DemoFacade{
	
	


//    @POST
//    @Path("post")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    @LogPoint
//    @SnailValidator(false)
//    public ResultData<ModelObject> testPost(RequestData<ModelObject> requestData) {
//        ResultData<ModelObject> modelObjectResultData = new ResultData<>();
//        modelObjectResultData.setBody(requestData.getBody());
//        return modelObjectResultData;
//    }
//
//    @POST
//    @Path("post1")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    @LogPoint
//    @SnailValidator(mapClass = Vo.class,valid = true)
//    public ResultData<Map<String, Object>> testPost1(RequestData<Map<String, Object>> requestData) {
//        Vo modelObject = (Vo) requestData.getTargetObj();
//        logger.info("{}",modelObject);
//        ResultData<Map<String, Object>> modelObjectResultData = new ResultData<>();
//        modelObjectResultData.putBody("t",modelObject);
//        System.out.println(BaseContextManager.get(BaseContext.USER_UUID));
//        System.out.println(BaseContextManager.get("t"));
//        return modelObjectResultData;
//    }



}
