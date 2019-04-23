package cn.tomsnail.snail.ext.db.batch.insert.t;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;



import com.mysql.jdbc.PreparedStatement;

import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertField;
import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertModel;

public class MySQLBatchInsertThread extends ABatchInsertThread {
	
	private LinkedBlockingQueue<DataInsertModel> QUEUE = new LinkedBlockingQueue<DataInsertModel>();
	
	private String psSql;

	public MySQLBatchInsertThread(String url, String username, String password,String psSql) {
		super(url, username, password);
		this.psSql = psSql;
		init();
	}
	@Override
	public boolean put(DataInsertModel insertModel){
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
		DataInsertModel insertModel = null;
		while(true){
			try {
				insertModel = QUEUE.poll(10, TimeUnit.MILLISECONDS);
				if(pst==null||con==null||pst.isClosed()||con.isClosed()){
					init();
				}
				if(insertModel!=null){
					int c =1;
					Iterator<String> it = insertModel.getFieldMap().keySet().iterator();
					while(it.hasNext()){
						String fname = it.next();
						DataInsertField insertField = insertModel.getFieldMap().get(fname);
						if(insertField.parseValue().equals("NULL")){
							pst.setString(c++, null);
						}else{
							pst.setString(c++, insertField.parseValue()+"");
						}
					}
					pst.addBatch();
				}
				if(count++%100==0){
					try {
						pst.executeBatch(); 
						con.commit();  
					} catch (Exception e) {
					}
					pst.clearBatch();
					System.out.println(Thread.currentThread().getName()+" "+QUEUE.size()+"-------------"+count);
				}
			} catch (Exception e) {
				e.printStackTrace();
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
			pst = (PreparedStatement) con.prepareStatement(psSql);  
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	@Override
	public void close(){
		if(pst!=null){
			try {
				pst.close();
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
	}
}
