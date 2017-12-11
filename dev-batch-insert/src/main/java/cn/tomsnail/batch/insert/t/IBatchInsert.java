package cn.tomsnail.batch.insert.t;

import cn.tomsnail.batch.insert.model.DataInsertModel;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月20日 下午2:21:42
	* @see 
	*/     
    
public interface IBatchInsert extends IInsertThread{

	public  IInsertThread getBatchInsertThread(DataInsertModel dataInsertModel);
	
}
