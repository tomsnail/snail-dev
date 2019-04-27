package cn.tomsnail.snail.example.core.framework.dubbox;

import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.log.annotation.LogPoint;
import com.alibaba.dubbo.config.annotation.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("test")
@Service
public class DemoFacadeImpl implements DemoFacade{

    @GET
    @Path("get/{no}")
    @Produces({MediaType.APPLICATION_JSON})
    @LogPoint
    public ResultData testGet(@PathParam("no")String no) {
        ResultData resultData = new ResultData();
        resultData.putBody("no",no);
        return resultData;
    }

    @POST
    @Path("post")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResultData testPost(RequestData requestData) {
        return ResultData.createMapResult("timestamp",System.currentTimeMillis());
    }

}