package cn.tomsnail.pilot.client.content;

import cn.tomsnail.pilot.client.AReportContent;
import cn.tomsnail.pilot.client.IReportContent;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 下午2:53:54
 * @see 
 */
public class CpuReportContent extends AReportContent{

	public CpuReportContent(IReportContent reportContent) {
		super(reportContent);
		this.reportKey = "CPU";
	}

	@Override
	public void content() {
		this.reportValue = Runtime.getRuntime().availableProcessors()+"";
	}

}
