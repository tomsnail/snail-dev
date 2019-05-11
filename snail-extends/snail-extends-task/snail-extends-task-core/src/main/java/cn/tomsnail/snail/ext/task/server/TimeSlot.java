package cn.tomsnail.snail.ext.task.server;



/**
 *        时间槽模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年5月3日 下午12:04:29
 * @see 
 */
public class TimeSlot {

	private int type;
	
	private String name;
	
	private String time;
	
	private long sTime;
	
	private int status;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getsTime() {
		return sTime;
	}

	public void setsTime(long sTime) {
		this.sTime = sTime;
	}
	
	
	
	
}
