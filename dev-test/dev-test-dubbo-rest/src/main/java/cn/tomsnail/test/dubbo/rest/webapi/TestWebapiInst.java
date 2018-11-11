package cn.tomsnail.test.dubbo.rest.webapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.config.annotation.Service;

import cn.tomsnail.log.annotation.LogLevel;
import cn.tomsnail.log.annotation.LogPoint;
import cn.tomsnail.log.annotation.LogTarget;
import cn.tomsnail.test.dubbo.rest.mo.RequestData;
import cn.tomsnail.test.dubbo.rest.mo.ResponseData;

@Path("test")
@Service(filter={"tomsnail"})
public class TestWebapiInst implements TestWebapi{

	@GET
    @Path("get/{no}")
	@Produces({MediaType.APPLICATION_JSON})
	@LogPoint(desc = "test", level = LogLevel.INFO, target = LogTarget.LOG)
	//@HttpRestService(serviceName = "test", group = "yzd_test")
	public ResponseData testGet(@PathParam("no")String no) {
		return new ResponseData(no);
	}

	@POST
    @Path("post")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseData testPost(RequestData requestData) {
		return new ResponseData(System.currentTimeMillis()+"");
	}

}
