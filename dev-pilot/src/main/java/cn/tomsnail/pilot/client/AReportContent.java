package cn.tomsnail.pilot.client;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 上午10:02:11
 * @see 
 */
public abstract class AReportContent implements IReportContent{

	protected IReportContent reportContent;
	
	protected String reportKey;
	
	protected String reportValue;
	
	public AReportContent(IReportContent reportContent){
		this.reportContent = reportContent;
	}
	
	public String getContent(){
		this.content();
		StringBuffer sb = new StringBuffer();
		sb.append(reportKey).append(":").append(reportValue);
		if(this.reportContent!=null){
			sb.append(",").append(this.reportContent.getContent());
		}
		return sb.toString();
	}
	
	public abstract void content();
	
	
}
