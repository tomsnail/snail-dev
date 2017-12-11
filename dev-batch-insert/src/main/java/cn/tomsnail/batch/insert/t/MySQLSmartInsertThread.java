package cn.tomsnail.batch.insert.t;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import cn.tomsnail.batch.insert.model.DataInsertModel;

public class MySQLSmartInsertThread extends ABatchInsertThread {
	
	private boolean isRun = false;
	
	private LinkedBlockingQueue<DataInsertModel> QUEUE = new LinkedBlockingQueue<DataInsertModel>();
	

	public MySQLSmartInsertThread(String url, String username, String password) {
		super(url, username, password);
		init();
	}
	@Override
	public boolean put(DataInsertModel insertModel){
		if(!isRun){
			return false;
		}
		try {
			QUEUE.put(insertModel);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void run() {
		int count = 0;
		int size = 100;
		int c = 0;
		DataInsertModel insertModel = null;
		while(isRun){
			try {
				insertModel = QUEUE.poll(20, TimeUnit.MILLISECONDS);
				if(insertModel!=null){
					st.addBatch(insertModel.getSQL().toString());
				}
				if((count!=0&&count%size==0)||c==size){
					try {
						st.executeBatch();
						c = 0;
						System.out.println(Thread.currentThread().getName()+" "+QUEUE.size()+"-------------"+count);
					} catch (Exception e) {
					}
				}
				count++;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
	@Override
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			close();
			con = (Connection) DriverManager.getConnection(url,username,password);  
			con.setAutoCommit(false);  
			st = con.createStatement(); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	public void reClose(){
		this.isRun = false;
	}
	
	@Override
	public void close(){
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(QUEUE!=null){
			QUEUE = null;
		}
	}
	
	public int getQueueSize(){
		return QUEUE.size();
	}
	public boolean isRun() {
		return isRun;
	}
	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}
	
	
}
