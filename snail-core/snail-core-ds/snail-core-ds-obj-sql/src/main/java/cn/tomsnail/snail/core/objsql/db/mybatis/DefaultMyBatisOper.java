package cn.tomsnail.snail.core.objsql.db.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.objsql.db.IDBoper;
import cn.tomsnail.snail.core.objsql.db.mybatis.common.dao.CommonMapper;
import cn.tomsnail.snail.core.util.MapToObj;



  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午1:47:22
	* @see 
	*/    
@Component
public class DefaultMyBatisOper implements IDBoper{
	
	private static final Logger logger  = LoggerFactory.getLogger(DefaultMyBatisOper.class);

	
	@Autowired(required=false)
	private CommonMapper commonMapper;

	@Override
	public String save(String sql) {
		logger.info(sql);
		try {
			Map map = new HashMap();
			map.put("sql", sql);
			commonMapper.saveData(map);
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(String sql) {
		logger.info(sql);
		Map map = new HashMap();
		map.put("sql", sql);
		return commonMapper.updateData(map);
	}

	@Override
	public int delete(String sql) {
		logger.info(sql);
		Map map = new HashMap();
		map.put("sql", sql);
		return commonMapper.deleteData(map);
	}

	@Override
	public <T> List<T> search(String sql, Class<T> elementType) {
		logger.info(sql);
		Map map = new HashMap();
		map.put("sql", sql);
		List<Map<String,Object>> resultData = commonMapper.searchData(map);
		if(resultData==null||resultData.size()==0){
			return null;
		}
		List<T> list = new ArrayList<T>();
		for(Map<String,Object> rmap:resultData){
			try {
				list.add(MapToObj.getObj(rmap, elementType));
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.clear();
		}
		resultData.clear();
		resultData = null;
		return list;
	}

}
