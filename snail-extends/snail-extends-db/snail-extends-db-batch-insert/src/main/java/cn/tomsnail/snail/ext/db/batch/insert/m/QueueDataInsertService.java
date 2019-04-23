package cn.tomsnail.snail.ext.db.batch.insert.m;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertField;
import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertModel;
import cn.tomsnail.snail.ext.db.batch.insert.t.IInsertThread;
import cn.tomsnail.snail.ext.db.batch.insert.util.DataInsertFactory;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年5月24日 下午6:42:11
 * @see 
 */
@Service("QueueDataInsertService")
public class QueueDataInsertService implements DataInsertService,Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(QueueDataInsertService.class);
		
	private static final LinkedBlockingQueue<DataInsertModel> QUEUE = new LinkedBlockingQueue<DataInsertModel>();
	
	@Autowired
	private DataInsertFactory dataInsertFactory;
	
	
	public QueueDataInsertService(){
		new Thread(this).start();		
	}
	
	@Override
	public void insert(MapperData mapperData){
		Map<String,List<Map<String,Object>>> tragetData = null;
		if(mapperData.isUpdate()){
			tragetData = mapperData.getUpdateData();
		}else{
			tragetData = mapperData.getTragetData();
		}
		if(tragetData==null||tragetData.size()<1){
			return;
		}
		Iterator<String> it = tragetData.keySet().iterator();
		while(it.hasNext()){
			String table = it.next();
			List<Map<String,Object>> dataList = tragetData.get(table);
			
			if(dataList==null||dataList.size()<1){
				continue;
			}
			for(Map<String,Object> data:dataList){
				DataInsertModel insertModel = new DataInsertModel();
				insertModel.setTable(table);
				LinkedHashMap<String,DataInsertField> fieldMap = new LinkedHashMap<String, DataInsertField>();
				Iterator<String> fit = data.keySet().iterator();
				while(fit.hasNext()){
					String fname = fit.next();
					Object v = data.get(fname);
					DataInsertField dataInsertField = new DataInsertField();
					dataInsertField.setName(fname);
					dataInsertField.setType(0);
					dataInsertField.setValue(v.toString());
					fieldMap.put(fname, dataInsertField);
				}
				insertModel.setFieldMap(fieldMap);
				try {
					QUEUE.put(insertModel);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
		mapperData.clear();
		mapperData = null;
	}


	@Override
	public void run() {
		DataInsertModel insertModel = null;
		while(true){
			try {
				insertModel = QUEUE.take();
				IInsertThread insertThread = dataInsertFactory.getInsertThread(insertModel, false);
				if(insertThread!=null){
					boolean isSucced = insertThread.put(insertModel);
					if(!isSucced){
						QUEUE.put(insertModel);
					}
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
	
}

