package cn.tomsnail.snail.ext.db.batch.insert.obj.annotation;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertField;
import cn.tomsnail.snail.ext.db.batch.insert.model.DataInsertModel;



  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月20日 下午2:57:23
	* @see 
	*/
public class ObjectMapperFactory {

	public static <T>DataInsertModel getDataInsertModel(T obj){
		
		if(obj==null||!obj.getClass().isAnnotationPresent(BatchModel.class)){
			return null;
		}
		BatchModel batchModel = obj.getClass().getAnnotation(BatchModel.class);
		
		DataInsertModel dataInsertModel = new DataInsertModel();
		dataInsertModel.setBatchModel(batchModel);
		
		if(StringUtils.isBlank(batchModel.tablename())){
			dataInsertModel.setTable(obj.getClass().getSimpleName().toLowerCase());
		}else{
			dataInsertModel.setTable(batchModel.tablename().toLowerCase());
		}
		
		for(Field f:obj.getClass().getDeclaredFields()){
			try {
				f.setAccessible(true);
				String name = f.getName();
				Object v = f.get(obj);
				DataInsertField dataInsertField = new DataInsertField(name,v.toString(),f.getType().getName());
				dataInsertModel.addField(name, dataInsertField);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataInsertModel;
	}
	
}
