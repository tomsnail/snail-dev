package cn.tomsnail.jms.test.kafka;

import java.util.Map;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;


public class PartitionerDemo implements Partitioner {

	public PartitionerDemo(VerifiableProperties props) {

	}

	@Override
	public int partition(Object obj, int numPartitions) {
		int partition = 0;
		if (obj instanceof String) {
			String key = (String) obj;
			partition = Integer.parseInt(key.split(":")[1])
					% numPartitions;
		} else {
			partition = obj.toString().length() % numPartitions;
		}

		return partition;
	}



}
