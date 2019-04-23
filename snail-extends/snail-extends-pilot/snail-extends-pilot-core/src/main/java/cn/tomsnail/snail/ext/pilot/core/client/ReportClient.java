package cn.tomsnail.snail.ext.pilot.core.client;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 下午3:26:56
 * @see 
 */
public abstract class ReportClient extends AClient implements IReport{
	
	private boolean isRun = true;
	
	private long sleepTime = 5000l;
	
	protected IReportContent reportContent;

	public ReportClient(IClient client,IReportContent reportContent) {
		super(client);
		this.reportContent = reportContent;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public IReportContent getReportContent() {
		return reportContent;
	}

	public void setReportContent(IReportContent reportContent) {
		this.reportContent = reportContent;
	}
	

}
