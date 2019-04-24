package cn.tomsnail.snail.e3.aliyun.mq.tcp;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.aliyun.openservices.ons.api.order.OrderProducer;

public class OrderProducerBean implements OrderProducer{

	private Properties properties;

    private OrderProducer producer;


    @Override
    public void start() {
        if (null == this.properties) {
            throw new ONSClientException("properties not set");
        }

        this.producer = ONSFactory.createOrderProducer(this.properties);
        this.producer.start();
    }


    @Override
    public void shutdown() {
        if (this.producer != null) {
            this.producer.shutdown();
        }
    }

    public Properties getProperties() {
        return properties;
    }


    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean isStarted() {
        return this.producer.isStarted();
    }

    @Override
    public boolean isClosed() {
        return this.producer.isClosed();
    }


	@Override
	public SendResult send(Message message, String shardingKey) {
		return this.producer.send(message, shardingKey);
	}
	
}
