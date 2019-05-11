package cn.tomsnail.tb;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;
import cn.tomsnail.tb.util.TbUtil;

public class TbServiceImpl implements TbService{
		
	@Override
	public void createTask(String taskId,String userId,String content,String involveMembers) {
		this._createTask(taskId, userId, content, involveMembers,3);
	}
	
	private void _createTask(String taskId,String userId,String content,String involveMembers,int count){
		if(count==0){
			return;
		}
		boolean refresh = false;
		try {
			String response = TbUtil.creatTask(taskId, userId, content, involveMembers);
			JSONObject jsonObject = JSONObject.fromObject(response);
			String id = jsonObject.getString("_id");
			if(StringUtils.isBlank(id)){
				refresh = true;
			}
		} catch (Exception e) {
			refresh = true;
		}
		if(refresh){
			//refresh();
		}
		this._createTask(taskId, userId, content, involveMembers, count-1);
	}
	
	private boolean refresh(){
		String token = TbUtil.token;
		int i= 10;
		TbUtil.authorize();
		while(i-->0){
			if(!token.equals(TbUtil.token)){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

}
