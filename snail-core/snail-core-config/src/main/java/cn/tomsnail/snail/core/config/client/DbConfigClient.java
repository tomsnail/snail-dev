package cn.tomsnail.snail.core.config.client;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import cn.tomsnail.snail.core.starter.spring.SpringBeanUtil;
import cn.tomsnail.snail.core.util.string.StringUtils;



public class DbConfigClient extends AConfigCilent{
	
	private JdbcTemplate jdbcTemplate;
	
	public DbConfigClient(AConfigCilent configCilent){
		this.configCilent = configCilent;
		try {
			if(isDo()){
				inited = true;
			}
		} catch (Exception e) {
		}
	}
	
	@Override
	public String getConfig(String key) {
		
		if(!isDo()){
			return null;
		}
		if(jdbcTemplate==null){
			init();
			if(jdbcTemplate==null){
				return null;
			}
		}
		
		if(StringUtils.isBlank(key)){
			return null;
		}
		
		String sql = "select value from ts_config where name = '"+key+"' and del_flag = '0' ";
		
		List<Map<String,Object>> maps =  jdbcTemplate.queryForList(sql);
		if(maps!=null&&maps.size()==1){
			Map<String,Object> m = maps.get(0);
			if(m!=null){
				Object v = m.get("value");
				if(v!=null){
					return v.toString();
				}
			}
		}
		
		return null;
	}

	
	private void init(){
		try {
			jdbcTemplate = (JdbcTemplate) SpringBeanUtil.getBean(JdbcTemplate.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Override
	protected String getName() {
		return "mysql";
	}

}
