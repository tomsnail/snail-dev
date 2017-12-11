package cn.tomsnail.task.log;

public interface ITaskLogRecord {
	/**
	 * 
	 * @descript TODO
	 * @param @param name 任务节点名称
	 * @param @param path  任务节点路径
	 * @param @param source 操作源
	 * @param @param operate 操作类型
	 * @param @param result  操作结果
	 * @param @param dodate  操作时间
	 * @return void
	 * @status 正常
	 * @throw
	 */
	public void recordLog(String name,String path,String source,int operate,String result,String dodate);
}
