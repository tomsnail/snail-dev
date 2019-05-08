package cn.tomsnail.snail.example.e3.task.elastic.job;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiverService {
	
	@KafkaListener(topics = {"Ealstic_JOB"}, groupId = "receiver")
    public void registryReceiver(ConsumerRecord<Integer, String> integerStringConsumerRecords) {
        System.out.println("接收到Kafka消息："+integerStringConsumerRecords.value());
    }


}
