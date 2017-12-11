import org.junit.Test;







import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tomsnail.config.client.AnnotationConfigListener;
import cn.tomsnail.config.client.ConfigExample;
import cn.tomsnail.config.client.annotation.TestClass;
import cn.tomsnail.config.server.ZookeeperConfigService;
import cn.tomsnail.config.server.s.file.DefaultFileConfigSource;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring-config-client.xml"})  
public class TestConfig {

	@Test
	public void testServer(){
		DefaultFileConfigSource source = new DefaultFileConfigSource("C:\\Users\\yangsong\\Desktop\\de\\listener",2000);
		new ZookeeperConfigService(source);
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClient1(){
		
		while(true){
			System.out.println(ConfigExample.TEST_KEY);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testClient2(){
		AnnotationConfigListener listener = new AnnotationConfigListener();
		listener.setClassName("cn.tomsnail.config.client.annotation.TestClass");
		while(true){
			System.out.println(TestClass.TEST_KEY);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void testSpringClient(){
		while(true){
			System.out.println(TestClass.TEST_KEY);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
