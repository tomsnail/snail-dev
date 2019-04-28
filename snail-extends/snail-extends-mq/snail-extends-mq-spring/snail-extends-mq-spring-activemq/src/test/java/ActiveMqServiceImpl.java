import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

public class ActiveMqServiceImpl {

    private JmsTemplate jmsTemplate = null;

    public void send(){
        jmsTemplate.convertAndSend("test");
    }

    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void reciver(String message){
        System.out.println(message);

    }

}
