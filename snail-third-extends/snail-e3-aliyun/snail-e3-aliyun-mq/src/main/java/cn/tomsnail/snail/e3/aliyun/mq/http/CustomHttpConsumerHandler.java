package cn.tomsnail.snail.e3.aliyun.mq.http;

import java.util.List;


public abstract class CustomHttpConsumerHandler {
	
	protected HttpMQConsumer httpMQConsumer;
	
	public void doJob(){
		List<SimpleMessage> list = httpMQConsumer.pull();
		if (list != null && list.size() > 0) {
			for (SimpleMessage simpleMessage : list) {

				// 当消息处理成功后，需要进行delete，如果不及时delete将会导致重复消费此消息
				String msgHandle = simpleMessage.getMsgHandle();
				doHandler(simpleMessage);
				if (httpMQConsumer.delete(msgHandle)) {
					System.out.println("delete success: " + msgHandle);
				} else {
					System.out.println("delete failed: " + msgHandle);
				}
			}
		}
	}
	

	public abstract void doHandler(SimpleMessage simpleMessage);

	public HttpMQConsumer getHttpMQConsumer() {
		return httpMQConsumer;
	}


	public void setHttpMQConsumer(HttpMQConsumer httpMQConsumer) {
		this.httpMQConsumer = httpMQConsumer;
	}
	
	
}
