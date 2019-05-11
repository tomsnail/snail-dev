package cn.tomsnail.snail.ext.db.batch.insert.t;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import com.mysql.jdbc.MySQLConnection;

import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertModel;

@Component
public class BatchInsertThreadManager implements IBatchInsert{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Map<String,List<IInsertThread>> threadListMap = new HashMap<String, List<IInsertThread>>();
	
	private static Map<String,Integer> countMap = new HashMap<String, Integer>();
	
	private int dbSize = 5;
	
	public  IInsertThread getBatchInsertThread(DataInsertModel dataInsertModel,boolean fair,int size){
		if(dataInsertModel==null||dataInsertModel.getPsSQL()==null){
			return null;
		}
		if(threadListMap.containsKey(dataInsertModel.getTable())){
		}else{
			int threadSize = dbSize;
			if(size/dbSize==1||size/dbSize==2){
				threadSize = size;
			}
			List<IInsertThread> batchInsertThreads = new ArrayList<IInsertThread>();
			try {
				MySQLConnection connection =  (MySQLConnection) jdbcTemplate.getDataSource().getConnection().getMetaData().getConnection();
				String url = connection.getURL();
				String username = connection.getUser();
				String password = connection.getProperties().getProperty("password");
				for(int i=0;i<threadSize;i++){
					MySQLBatchInsertThread mySQLBatchInsertThread = new MySQLBatchInsertThread(url,username,password, dataInsertModel.getPsSQL().toString());
					batchInsertThreads.add(mySQLBatchInsertThread);
					new Thread(mySQLBatchInsertThread).start();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			threadListMap.put(dataInsertModel.getTable(), batchInsertThreads);
			countMap.put(dataInsertModel.getTable(), threadSize);
		}
		List<IInsertThread> threads = threadListMap.get(dataInsertModel.getTable());
		int threadSize = countMap.get(dataInsertModel.getTable());
		if(fair){
			countMap.put(dataInsertModel.getTable(),threadSize+1);
			return threads.get(threadSize%threads.size());
		}else{
			return threads.get((int)System.currentTimeMillis()%threads.size());
		}
	}
	
	public  IInsertThread getBatchInsertThread(DataInsertModel dataInsertModel){
		return getBatchInsertThread(dataInsertModel,false,10);
	}
	public int getDbSize() {
		return dbSize;
	}

	public void setDbSize(int dbSize) {
		this.dbSize = dbSize;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void close() {
		
	}

	@Override
	public boolean put(DataInsertModel insertModel) {
		return getBatchInsertThread(insertModel).put(insertModel);
	}

	@Override
	public void run() {
		
	}
	
	

	
}
