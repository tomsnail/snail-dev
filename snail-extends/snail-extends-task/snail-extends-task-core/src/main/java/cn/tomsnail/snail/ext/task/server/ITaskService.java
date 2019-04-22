package cn.tomsnail.snail.ext.task.server;

import java.util.List;
import java.util.Map;


public interface ITaskService {
		//查询
		public List<Map<String,Object>> getTask(String name,String status,int currentPage,int pageSize);
		//删除
		public Map<String,Object> delTask(String name);
		//取消
		public Map<String,Object> cancelTask(String name);
		//执行
		public Map<String,Object> runTask(String name);
		//查询日志
		public List<Map<String,Object>> queryTaskLog(String name);
}
