package cn.tomsnail.mq.aliyun.http;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.tomsnail.mq.aliyun.MD5;

import com.aliyun.openservices.ons.api.impl.authority.AuthUtil;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

public class HttpMQProducer {

	private String accessKey;
	private String secretKey;
	private String producerId;
	private String url;
	private String topic;

	private static final String NEWLINE = "\n";
	private static final Logger log = LogManager.getLogger();

	/**
	 * 发送普通消息
	 * 
	 * @param msg
	 * @param tag
	 * @param key
	 * @return
	 */
	public boolean send(String msg, String tag, String key) {
		return send(msg, tag, key, null);
	}

	/**
	 * 发送定时消息
	 * 
	 * @param msg
	 * @param tag
	 * @param key
	 * @param startDeliverTime
	 * @return
	 */
	public boolean send(String msg, String tag, String key, Long startDeliverTime) {
		long time = System.currentTimeMillis();
		HttpRequestWithBody req = Unirest.post(url);
		String signString = topic + NEWLINE + producerId + NEWLINE + MD5.getInstance().getMD5String(msg) + NEWLINE
				+ time;
		String sign = AuthUtil.calSignature(signString.getBytes(StandardCharsets.UTF_8), secretKey);
		req.header("Signature", sign);
		req.header("AccessKey", accessKey);
		req.header("ProducerID", producerId);
		req.field("topic", topic);
		req.field("time", time);
		if (startDeliverTime != null) {
			req.field("startdelivertime", startDeliverTime);
		}
		req.body(msg);

		try {
			HttpResponse<String> res = req.asString();
			if (res.getCode() == 201) {
				return true;
			} else {
				log.error("post message error: {}", msg, res.getBody());
			}
		} catch (UnirestException e) {
			log.error("post message error: {}", msg, e);
		}
		return false;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getProducerId() {
		return producerId;
	}

	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
}
