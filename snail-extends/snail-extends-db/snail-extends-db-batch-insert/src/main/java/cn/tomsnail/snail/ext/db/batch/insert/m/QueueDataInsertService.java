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
		Iterator<Map.Entry<String,List<Map<String,Object>>>> it = tragetData.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,List<Map<String,Object>>> entry = it.next();
			List<Map<String,Object>> dataList =entry.getValue();
			
			if(dataList==null||dataList.size()<1){
				continue;
			}
			for(Map<String,Object> data:dataList){
				DataInsertModel insertModel = new DataInsertModel();
				insertModel.setTable(entry.getKey());
				LinkedHashMap<String,DataInsertField> fieldMap = new LinkedHashMap<String, DataInsertField>();
				Iterator<Map.Entry<String,Object>> fit = data.entrySet().iterator();
				while(fit.hasNext()){
					Map.Entry<String,Object> entry1 = fit.next();
					Object v = entry1.getValue();
					DataInsertField dataInsertField = new DataInsertField();
					dataInsertField.setName(entry1.getKey());
					dataInsertField.setType(0);
					dataInsertField.setValue(v.toString());
					fieldMap.put(entry1.getKey(), dataInsertField);
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

