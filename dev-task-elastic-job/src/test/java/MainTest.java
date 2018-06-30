import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.ClassPathXmlApplicationContext;




public class MainTest {

	public static void main(String[] args) {
		
		String applicationContextXml = "applicationContext.xml";
	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:"+applicationContextXml);
		context.start();
		
	}
}
