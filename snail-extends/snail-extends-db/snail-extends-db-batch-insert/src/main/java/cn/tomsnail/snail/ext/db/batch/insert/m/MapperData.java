package cn.tomsnail.snail.ext.db.batch.insert.m;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年5月18日 下午5:21:22
 * @see 
 */
public class MapperData {


	/**
	 * 
	 */
	private String[] fileds;
	
	/**
	 * 数据集
	 */
	private List<Map<String,Object>> sourceData;
	
	/**
	 * 数据集
	 */
	private Map<String,List<Map<String,Object>>> tragetData;
	
	
	/**
	 * 数据集
	 */
	private Map<String,List<Map<String,Object>>> updateData = new HashMap<String, List<Map<String,Object>>>();;
	
	/**
	 * 
	 */
	private boolean update = false;
	

	public String[] getFileds() {
		return fileds;
	}

	public void setFileds(String[] fileds) {
		this.fileds = fileds;
	}

	public List<Map<String, Object>> getSourceData() {
		return sourceData;
	}

	public void setSourceData(List<Map<String, Object>> sourceData) {
		this.sourceData = sourceData;
	}

	public Map<String, List<Map<String, Object>>> getTragetData() {
		return tragetData;
	}

	public void setTragetData(Map<String, List<Map<String, Object>>> tragetData) {
		this.tragetData = tragetData;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public Map<String, List<Map<String, Object>>> getUpdateData() {
		return updateData;
	}

	public void setUpdateData(Map<String, List<Map<String, Object>>> updateData) {
		this.updateData = updateData;
	}
	
	public void clear(){
		if(updateData!=null){
			updateData.clear();
			updateData = null;
		}
		if(tragetData!=null){
			tragetData.clear();
			tragetData = null;
		}
		if(sourceData!=null){
			sourceData.clear();
			sourceData = null;
		}
		if(fileds!=null){
			fileds = null;
		}
	}
	
}
