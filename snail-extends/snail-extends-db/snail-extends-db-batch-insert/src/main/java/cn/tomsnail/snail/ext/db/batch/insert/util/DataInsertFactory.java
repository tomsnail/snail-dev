package cn.tomsnail.snail.ext.db.batch.insert.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertModel;
import cn.tomsnail.snail.ext.db.batch.insert.t.BatchInsertThreadManager;
import cn.tomsnail.snail.ext.db.batch.insert.t.IInsertThread;
import cn.tomsnail.snail.ext.db.batch.insert.t.JdbcTemplateInsertThread;
import cn.tomsnail.snail.ext.db.batch.insert.t.SmartInsertThreadManager;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月14日 上午10:55:38
 * @see 
 */
@Component
@ComponentScan(basePackages={"cn.tomsnail.batch.insert.t"})
public class DataInsertFactory {
	
	@Autowired
	private BatchInsertThreadManager batchInsertThreadManager;
	
	@Autowired
	private SmartInsertThreadManager smartInsertThreadManager;
	
	@Autowired
	private JdbcTemplateInsertThread jdbcTemplateInsertThread;
	
	
	public IInsertThread getInsertThread(DataInsertModel dataInsertModel,boolean isBatch){
		if(!isBatch){
			jdbcTemplateInsertThread.put(dataInsertModel);
			return jdbcTemplateInsertThread;
		}else{
			return batchInsertThreadManager.getBatchInsertThread(dataInsertModel);
		}
	}
	
	public void put(DataInsertModel dataInsertModel){
		if(!dataInsertModel.getBatchModel().isBatch()){
			jdbcTemplateInsertThread.put(dataInsertModel);
		}else{
			if(!dataInsertModel.getBatchModel().isSmart()){
				batchInsertThreadManager.put(dataInsertModel);
			}
			smartInsertThreadManager.put(dataInsertModel);
		}
	}


	public BatchInsertThreadManager getBatchInsertThreadManager() {
		return batchInsertThreadManager;
	}

	public void setBatchInsertThreadManager(
			BatchInsertThreadManager batchInsertThreadManager) {
		this.batchInsertThreadManager = batchInsertThreadManager;
	}

	public SmartInsertThreadManager getSmartInsertThreadManager() {
		return smartInsertThreadManager;
	}

	public void setSmartInsertThreadManager(
			SmartInsertThreadManager smartInsertThreadManager) {
		this.smartInsertThreadManager = smartInsertThreadManager;
	}

	public JdbcTemplateInsertThread getJdbcTemplateInsertThread() {
		return jdbcTemplateInsertThread;
	}


	public void setJdbcTemplateInsertThread(
			JdbcTemplateInsertThread jdbcTemplateInsertThread) {
		this.jdbcTemplateInsertThread = jdbcTemplateInsertThread;
	}
	
	
}
