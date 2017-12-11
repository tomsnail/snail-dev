package cn.tomsnail.batch.insert.t;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.tomsnail.batch.insert.model.DataInsertModel;
import cn.tomsnail.batch.insert.util.Constants;

import com.mysql.jdbc.MySQLConnection;

@Component
public class SmartInsertThreadManager implements Runnable,IBatchInsert{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final 	List<IInsertThread> insertThreads = Collections.synchronizedList(new ArrayList<IInsertThread>());
	
	private int coreSize = Constants.coreSize;
	
	private int maxSize = Constants.maxSize;
	
	private int zeroCount = Constants.zeroCount;
	
	private boolean isRun = Constants.isRun;
	
	private int dbSize = Constants.dbSize;
	
	private long insertCount  = Constants.insertCount;
	
	public SmartInsertThreadManager(){
		
	}
	
	public  IInsertThread getBatchInsertThread(DataInsertModel dataInsertModel){
		if(dataInsertModel==null){
			return null;
		}
		return insertThreads.get((int)(insertCount++%dbSize));
	}
	
	@PostConstruct
	public void init(){
		try {
			MySQLConnection connection =  (MySQLConnection) jdbcTemplate.getDataSource().getConnection().getMetaData().getConnection();
			String url = connection.getURL();
			String username = connection.getUser();
			String password = connection.getProperties().getProperty("password");
			for(int i=0;i<coreSize;i++){
				MySQLSmartInsertThread mySQLSmartInsertThread = new MySQLSmartInsertThread(url,username,password);
				insertThreads.add(mySQLSmartInsertThread);
				new Thread(mySQLSmartInsertThread).start();
			}
			dbSize = insertThreads.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年6月27日 下午1:54:33
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	private void addNewThread(){
		try {
			if(insertThreads.size()<maxSize){
				MySQLConnection connection =  (MySQLConnection) jdbcTemplate.getDataSource().getConnection().getMetaData().getConnection();
				String url = connection.getURL();
				String username = connection.getUser();
				String password = connection.getProperties().getProperty("password");
				MySQLSmartInsertThread mySQLSmartInsertThread = new MySQLSmartInsertThread(url,username,password);
				insertThreads.add(mySQLSmartInsertThread);
				new Thread(mySQLSmartInsertThread).start();
			}
			dbSize = insertThreads.size();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(isRun){
			int totalCount = getTotalCount();
			if(isFixed(totalCount)){
				addNewThread();
			}
			tryCloseSleepThread(totalCount);
			if(totalCount==0&&insertThreads.size()==coreSize){
				zeroCount = 0;
			}
			if(totalCount>0){
				zeroCount = 0;
			}
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年6月27日 下午1:52:26
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	private void tryCloseSleepThread(int totalCount) {
		if(totalCount==0&&insertThreads.size()>coreSize){
			zeroCount++;
			if(zeroCount==30){
				dbSize = this.coreSize;
				for(int i=coreSize;i<insertThreads.size();i++){
					MySQLSmartInsertThread mySQLSmartInsertThread = (MySQLSmartInsertThread) insertThreads.get(i);
					mySQLSmartInsertThread.reClose();
				}
			}
			if(zeroCount>=60){
				Iterator<IInsertThread> it = insertThreads.iterator();
				while(it.hasNext()){
					MySQLSmartInsertThread mySQLSmartInsertThread = (MySQLSmartInsertThread) it.next();
					if(mySQLSmartInsertThread!=null&&!mySQLSmartInsertThread.isRun()){
						if(mySQLSmartInsertThread.getQueueSize()==0){
							mySQLSmartInsertThread.close();
							mySQLSmartInsertThread = null;
							it.remove();
						}
					}
				}
			}
		}
	}

	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年6月27日 下午1:49:56
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	private boolean isFixed(int totalCount) {
		return totalCount>insertThreads.size()*1000;
	}
	
	private int getTotalCount(){
		int totalCount = 0;
		for(int i=0;i<insertThreads.size();i++){
			MySQLSmartInsertThread mySQLSmartInsertThread = (MySQLSmartInsertThread) insertThreads.get(i);
			totalCount += mySQLSmartInsertThread.getQueueSize();
		}
		return totalCount;
	}

	@Override
	public void close() {
		
	}

	@Override
	public boolean put(DataInsertModel insertModel) {
		return getBatchInsertThread(insertModel).put(insertModel);
	}

}
