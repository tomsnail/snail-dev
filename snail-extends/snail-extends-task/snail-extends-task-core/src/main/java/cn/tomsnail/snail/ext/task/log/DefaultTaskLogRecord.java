package cn.tomsnail.snail.ext.task.log;

public class DefaultTaskLogRecord implements ITaskLogRecord {
	ITaskLogRecord taskLogRecord;
	
	public ITaskLogRecord getTaskLogRecord() {
		return taskLogRecord;
	}

	public void setTaskLogRecord(ITaskLogRecord taskLogRecord) {
		this.taskLogRecord = taskLogRecord;
	}


	@Override
	public void recordLog(String name, String path, String source, int operate,
			String result, String dodate) {
		// TODO Auto-generated method stub
		taskLogRecord.recordLog(name, path, source, operate, result, dodate);
	}

}
