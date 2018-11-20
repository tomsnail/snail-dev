package cn.tomsnail.task.server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 下午4:28:46
 * @see 
 */
public class ZkTimeSlot extends TimeSlot{
	
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	private String path;
	
	private String result;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getTaskInfo(){
		return this.getType()+","+this.getName()+","+System.currentTimeMillis()+","+this.getTime()+","+this.getStatus();
	}
	
	public ZkTimeSlot(String str){
		String[] perproties = str.split(",");
		this.setType(Integer.valueOf(perproties[0]));
		this.setName(perproties[1]);
		this.setPath(TimeSlotConfig.TIME_SLOT_ROOT+"/"+this.getName());
		this.setTime(perproties[3]);
		this.setStatus(Integer.valueOf(perproties[4]));
	}
	
	public void calculate(){
		if(this.getType()==TimeSlotType.once_1.ordinal()+1){
			try {
				this.setsTime(df.parse(this.getTime()).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(this.getType()==TimeSlotType.once_2.ordinal()+1){
			long startTime = System.currentTimeMillis()+Long.valueOf(this.getTime());
			this.setsTime(startTime);
		}
		if(this.getType()==TimeSlotType.cycle.ordinal()+1){
			long startTime = System.currentTimeMillis()+Long.valueOf(this.getTime());
			this.setsTime(startTime);
		}
	}
	
	public boolean add(){
		if(this.getType()==TimeSlotType.once_1.ordinal()+1){
			if(Long.valueOf(this.getsTime())>System.currentTimeMillis()){
				if(this.getStatus()==TimeSlotStatus.wait.ordinal()){
					return true;
				}
			}
		}
		if(this.getType()==TimeSlotType.once_2.ordinal()+1){
			if(Long.valueOf(this.getsTime())>System.currentTimeMillis()){
				if(this.getStatus()==TimeSlotStatus.wait.ordinal()){
					return true;
				}
			}
		}
		if(this.getType()==TimeSlotType.cycle.ordinal()+1){
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		return this.getTaskInfo().equals(((ZkTimeSlot)obj).getTaskInfo());
	}
	
}
