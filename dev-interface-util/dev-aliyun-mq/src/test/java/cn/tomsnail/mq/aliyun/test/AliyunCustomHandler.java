package cn.tomsnail.mq.aliyun.test;


import cn.tomsnail.mq.aliyun.tcp.CustomMessageListener;

public class AliyunCustomHandler extends CustomMessageListener{

	@Override
	public void handler(String topic, String tag, String msgId, String key,
			String body, long time) {
		System.out.println("1:"+msgId+":"+body);
	}

}
