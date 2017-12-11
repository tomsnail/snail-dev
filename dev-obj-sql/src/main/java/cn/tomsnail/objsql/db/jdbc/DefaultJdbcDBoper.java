package cn.tomsnail.objsql.db.jdbc;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.tomsnail.objsql.db.IDBoper;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午1:40:01
	* @see 
	*/
@Component
public class DefaultJdbcDBoper implements IDBoper{
	
	private static final Logger logger  = LoggerFactory.getLogger(DefaultJdbcDBoper.class);
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String save(String sql) {
		logger.info(sql);
		try {
			 jdbcTemplate.execute(sql);
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(String sql) {
		logger.info(sql);
		return jdbcTemplate.update(sql);
	}

	@Override
	public int delete(String sql) {
		logger.info(sql);
		try {
			 jdbcTemplate.execute(sql);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public <T> List<T> search(String sql,Class<T> elementType) {
		logger.info(sql);
		return jdbcTemplate.query(sql+" ;", new Object[]{}, new BeanPropertyRowMapper<T>(elementType));
		
	}

}
