package cn.tomsnail.snail.ext.pilot.core.client.content;

import cn.tomsnail.snail.ext.pilot.core.client.AReportContent;
import cn.tomsnail.snail.ext.pilot.core.client.IReportContent;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月4日 下午2:53:25
 * @see 
 */
public class MemReportContent extends AReportContent{

	public MemReportContent(IReportContent reportContent) {
		super(reportContent);
		this.reportKey = "MEM";
	}

	@Override
	public void content() {
		this.reportValue =(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/Runtime.getRuntime().totalMemory()*100+"";
	}
	
	public static void main(String[] args) {
		MemReportContent memReportContent = new MemReportContent(new CpuReportContent(null));
		System.out.println(memReportContent.getContent());
	}

}
