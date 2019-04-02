package cn.tomsnail.i.dd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.CorpDingTaskCreateRequest;
import com.dingtalk.api.request.CorpDingTaskCreateRequest.TaskSendVo;
import com.dingtalk.api.request.OapiCalendarCreateRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiUserListbypageRequest;
import com.dingtalk.api.request.OapiWorkrecordAddRequest;
import com.dingtalk.api.request.OapiWorkrecordAddRequest.FormItemVo;
import com.dingtalk.api.request.OapiWorkrecordUpdateRequest;
import com.dingtalk.api.response.CorpDingTaskCreateResponse;
import com.dingtalk.api.response.OapiCalendarCreateResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.dingtalk.api.response.OapiWorkrecordAddResponse;
import com.dingtalk.api.response.OapiWorkrecordUpdateResponse;

public class TestMain {

	public static void main(String[] args) throws Exception {
		DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
		OapiGettokenRequest request = new OapiGettokenRequest();
		request.setAppkey("dingb52fzmucil5jsosd");
		request.setAppsecret("xIw9jEYpA7sovlYYjiQj0cQBabE0PMcvxFrYa1GiujMkREaKUEB1TQcFZXamZFb-");
		request.setHttpMethod("GET");
		OapiGettokenResponse response = client.execute(request);
		
//		getDeptUser(response.getAccessToken());
//		createTask(response.getAccessToken(),"0254551848847126");
//		createTask(response.getAccessToken(),"0959046701976526");
		
//		updateTask(response.getAccessToken(),"0254551848847126","recordd9fcb1286c2953026199d903e45dcfb9");
//		updateTask(response.getAccessToken(),"0959046701976526","record491566bf2efe529f66df145c580b07be");
		
//		createWorkNotify(response.getAccessToken(),"0254551848847126");
//		createWorkNotify(response.getAccessToken(),"0959046701976526");
//		createTask(response.getAccessToken(),"0959046701976526");
//		createTask(response.getAccessToken(),"0254551848847126");
		
	}
	
	public static void getDeptUser(String accessToken) throws Exception {
//		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getDeptMember");
//		OapiUserGetDeptMemberRequest req = new OapiUserGetDeptMemberRequest();
//		req.setDeptId("1");
//		req.setHttpMethod("GET");
//		OapiUserGetDeptMemberResponse rsp = client.execute(req, accessToken);
//		System.out.println(rsp.getBody());
		
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/listbypage");
		OapiUserListbypageRequest request = new OapiUserListbypageRequest();
		request.setDepartmentId(1L);
		request.setOffset(0L);
		request.setSize(10L);
		request.setOrder("entry_desc");
		request.setHttpMethod("GET");
		OapiUserListbypageResponse rsp = client.execute(request,accessToken);
		System.out.println(rsp.getBody());
	}
	
	public static void createWC(String accessToken,String userId) throws Exception {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/add");
		OapiWorkrecordAddRequest req = new OapiWorkrecordAddRequest();
		req.setUserid(userId);
		req.setCreateTime(1496678400000L);
		req.setTitle("测试任务");
		req.setUrl("https://oa.dingtalk.com");
		List<FormItemVo> list2 = new ArrayList<FormItemVo>();
		FormItemVo obj3 = new FormItemVo();
		list2.add(obj3);
		obj3.setTitle("测试");
		obj3.setContent("测试任务");
		req.setFormItemList(list2);
		OapiWorkrecordAddResponse rsp = client.execute(req, accessToken);
		System.out.println(rsp.getBody());
	}
	
	public static void updateTask(String accessToken,String userId,String rId ) throws Exception {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/workrecord/update");
		OapiWorkrecordUpdateRequest req = new OapiWorkrecordUpdateRequest();
		req.setUserid(userId);
		req.setRecordId(rId);
		OapiWorkrecordUpdateResponse rsp = client.execute(req, accessToken);
		System.out.println(rsp.getBody());
	}
	
	public static void createWorkNotify(String accessToken,String userId) throws Exception {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");

		OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
		request.setUseridList(userId);
		request.setAgentId(231758990L);
		request.setToAllUser(false);

		OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
		msg.setMsgtype("text");
		msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
		msg.getText().setContent("test123");
		request.setMsg(msg);
		
		OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request,accessToken);
		System.out.println(response.getBody());
	}
	
	public static void createRC(String accessToken,String userId) throws Exception {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/calendar/create");
		OapiCalendarCreateRequest request = new OapiCalendarCreateRequest();
		OapiCalendarCreateRequest.OpenCalendarCreateVo creatVo = new OapiCalendarCreateRequest.OpenCalendarCreateVo();
		creatVo.setUuid("ingaae5cff5bbcd35c2f4657eb6378f123456");
		creatVo.setBizId("231758990");
		creatVo.setCreatorUserid(userId);
		creatVo.setSummary("测试日历创建");
		creatVo.setReceiverUserids(Arrays.asList(userId));
		OapiCalendarCreateRequest.DatetimeVo start = new OapiCalendarCreateRequest.DatetimeVo();
		start.setUnixTimestamp(System.currentTimeMillis()-10000);
		creatVo.setStartTime(start);
		OapiCalendarCreateRequest.DatetimeVo end = new OapiCalendarCreateRequest.DatetimeVo();
		end.setUnixTimestamp(System.currentTimeMillis());
		creatVo.setEndTime(end);
		creatVo.setCalendarType("task");
		OapiCalendarCreateRequest.OpenCalendarSourceVo source = new OapiCalendarCreateRequest.OpenCalendarSourceVo();
		source.setTitle("测试日历");
		source.setUrl("http://baidu.com");
		creatVo.setSource(source);
		request.setCreateVo(creatVo);
		OapiCalendarCreateResponse response = client.execute(request, accessToken);
		System.out.println(response.getBody());
	}
	
	public static void createTask(String accessToken,String userId) throws Exception {
		DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
		CorpDingTaskCreateRequest req = new CorpDingTaskCreateRequest();
		TaskSendVo obj1 = new TaskSendVo();
		obj1.setSendUserid(userId);
		obj1.setContentType(1L);
		obj1.setContent("下午5点开会");
		obj1.setRemindType(1L);
		obj1.setRemindTime(1551086100000L);
		obj1.setReceiverUserid(userId);
		obj1.setDeadLine(1551087000000L);
		req.setTaskSendVO(obj1);
		CorpDingTaskCreateResponse rsp = client.execute(req, accessToken);
		System.out.println(rsp.getBody());
	}
}
