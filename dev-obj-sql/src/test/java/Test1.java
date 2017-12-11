import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import test.User;
import cn.tomsnail.objsql.IParamObjectSQL;
import cn.tomsnail.objsql.ISearchObjectSQL;
import cn.tomsnail.objsql.util.ObjectSQLFactory;
import cn.tomsnail.unit.test.BaseTest;


public class Test1 extends BaseTest{

	@Autowired
	private ObjectSQLFactory objectSQLFactory;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	public void test(){
		try {
			User u = new User();
			u.setId("2");
			u.setAge(1);
			u.setName("test2");
			u.setNickname("test5");
			IParamObjectSQL<String,User> basicObjectSQL = objectSQLFactory.getParamObjectSQL(User.class);
			//basicObjectSQL.updateById("2", u,new String[]{"nickname"});
			System.out.println(basicObjectSQL.searchById("2",new String[]{"id","nickname"}).get(0).getNickname());
			//basicObjectSQL.deleteById("1");
			
			ISearchObjectSQL<String,User> searchObjectSQL = objectSQLFactory.getSearchObjectSQL(User.class);
			
			System.out.println(searchObjectSQL.countByField("nickname", "test5"));
			
//			new ShutDownHook();
//			
//			for(int i=0;i<1000;i++){
//				//System.out.println();
//				searchObjectSQL.searchByFieldOrder("age", "1", null, 0, 10, "id").get(0).getNickname();
//				//jdbcTemplate.execute("select  id,name,nickname,age,sex from user where age =  '1'  order by  id limit 0 , 10");
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
