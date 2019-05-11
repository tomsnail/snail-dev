package cn.tomsnail.snail.ext.db.batch.insert.t;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertModel;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月14日 上午10:57:47
 * @see 
 */
@Component
public class JdbcTemplateInsertThread implements IInsertThread{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private boolean isRun = true;
	
	
	private LinkedBlockingQueue<DataInsertModel> QUEUE = new LinkedBlockingQueue<DataInsertModel>();
	
	public JdbcTemplateInsertThread(){
		new Thread(this).start();
	}
	


	@Override
	public void run() {
		long count = 0;
		int size = 100;
		int c = 0;
		DataInsertModel insertModel = null;
		String[] sql = new String[size];
		while(isRun){
			try {
				insertModel = QUEUE.poll(20, TimeUnit.MILLISECONDS);
				if(insertModel!=null){
					sql[c++%size] = insertModel.getSQL().toString();
				}
				if((count!=0&&count%size==0)||c==size){
					try {
						String[] _sql = new String[c];
						for(int i=0;i<c;i++){
							_sql[i] = sql[i];
							sql[i] = null;
						}
						if(_sql.length>0) jdbcTemplate.batchUpdate(_sql);
						_sql = null;
						c = 0;
						System.out.println(Thread.currentThread().getName()+" "+QUEUE.size()+"-------------"+count);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				count++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void init() {
		
	}

	@Override
	public void close() {
		
	}

	@Override
	public boolean put(DataInsertModel insertModel) {
		try {
			QUEUE.put(insertModel);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	

}
