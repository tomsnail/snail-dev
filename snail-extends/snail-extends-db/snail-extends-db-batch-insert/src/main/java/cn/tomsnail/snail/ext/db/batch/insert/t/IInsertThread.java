package cn.tomsnail.snail.ext.db.batch.insert.t;

import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertModel;

public interface IInsertThread extends Runnable{
	
	public void init();
	
	public void close();
	
	public boolean put(DataInsertModel insertModel);
	
}
