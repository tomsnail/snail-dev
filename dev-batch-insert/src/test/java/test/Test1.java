package test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tomsnail.batch.insert.model.DataInsertModel;
import cn.tomsnail.batch.insert.obj.annotation.ObjectMapperFactory;
import cn.tomsnail.batch.insert.util.DataInsertFactory;
import cn.tomsnail.unit.test.BaseTest;

public class Test1 extends BaseTest{

	@Autowired
	private DataInsertFactory dataInsertFactory;
	
	@Test
	public void test(){
		
		DataInsertModel dataInsertModel = ObjectMapperFactory.getDataInsertModel(new User());
		dataInsertFactory.put(dataInsertModel);
		this.sleep(10000);
	}
	
}
