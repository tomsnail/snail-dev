package cn.tomsnail.framwork.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.tomsnail.framwork.http.ResultData;



/**
 *        验证失败处理
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:08:52
 * @see 
 */
public class ValidatorFailHandler {
	
	public static List<Object> handler(String failMsg,String command){
		List<Object> list=new ArrayList<Object>();
		ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
		resultData.setCommand(command);
		resultData.setStatus("-10");
		resultData.setMsg(failMsg);
		list.add(resultData);
		return list;
	}
	
}
