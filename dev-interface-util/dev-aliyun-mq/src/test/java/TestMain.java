import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.shade.org.apache.commons.codec.binary.Base64;

import cn.tomsnail.mq.aliyun.MD5;
import cn.tomsnail.mq.aliyun.tcp.OrderProducerBean;
import cn.tomsnail.unit.test.BaseTest;


public class TestMain extends BaseTest{

	@Resource(name="producer")
	private ProducerBean producerBean;
	
	private ProducerBean producerBean1;
	
	private OrderProducerBean orderProducerBean;
	
	//所有 GET POST DELETE 签名前字符串的拼接使用 \n
	private static final String NEWLINE="\n";
	//签名所有的 byte 使用编码 UTF-8，所有的 string 编码 UTF-8
	private static final String ENCODE = "UTF-8";
	//签名算法 HmacSHA1
	public static final String HmacSHA1 = "HmacSHA1";
	
	@Test
	public void test1(){
//		producerBean.send(new Message("testyangsong1", "a", "hello aliyun mq".getBytes()));
//		orderProducerBean.send(new Message("testyangsong2", "a", "hello aliyun mq".getBytes()),"12435");
		while(true){
			producerBean.send(new Message("dayuan_collect", "a", ("hello dayuan_collect "+System.currentTimeMillis()).getBytes()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		long t = System.currentTimeMillis();
		String url = "http://publictest-rest.ons.aliyun.com/message?topic=dayuan_collect&time="+t;
		System.out.println(t);
		String s = postSign("oSTThzZiwR58XwNDoG3awbSW33OF7p","dayuan_collect","PID_90aae0aa-451a-11e7-97e4-90b11c2f0682","123",t+"");
		System.out.println(s);
		httpPost(url,s);
		//1496166319391
		//1496139324380
		System.out.println(calSignature("01091082010101010064010115401401".getBytes(), "DAYUAN-ORIGIN"));
		//i/BW3g1wSfMq4e8M7StEdPdzbSw=
	}
	
	
	 public static void httpPost(String url,String s){
	        //post请求返回结果
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpPost method = new HttpPost(url);
	        method.setHeader("AccessKey", "LTAIkAtLfTVXEdcj");
	        method.setHeader("Signature", s);
	        method.setHeader("ProducerId", "PID_90aae0aa-451a-11e7-97e4-90b11c2f0682");
	        try {
	        	 StringEntity entity = new StringEntity("123", "utf-8");
	                entity.setContentEncoding("UTF-8");
	                method.setEntity(entity);
	            HttpResponse result = httpClient.execute(method);
	            url = URLDecoder.decode(url, "UTF-8");
	            /**请求发送成功，并得到响应**/
	            System.out.println(result.getStatusLine().getReasonPhrase());
	            System.out.println(result.getStatusLine().getStatusCode());
	            if (result.getStatusLine().getStatusCode() == 201) {
	                String str = "";
	                try {
	                    str = EntityUtils.toString(result.getEntity());
	                  System.out.println(str);
	                } catch (Exception e) {
	                    logger.error("post请求提交失败:" + url, e);
	                }
	            }
	        } catch (IOException e) {
	            logger.error("post请求提交失败:" + url, e);
	        }
	    }
	
	public static String postSign(String secretKey,String topic,String producerId,String body,String date) throws Exception {
		   //拼接消息
		    String signString=topic+NEWLINE+producerId+NEWLINE+ MD5.getInstance().getMD5String(body)+NEWLINE+date;
		    //计算签名
		    return calSignature(signString.getBytes(Charset.forName(ENCODE)), secretKey);
		}
	
	public static String calSignature(byte[] data,String key) throws Exception {
	    //采用 HmacSHA1 编码
	    Mac e = Mac.getInstance(HmacSHA1);
	    //key 转成二进制 UTF-8 编码
	    byte[] keyBytes = key.getBytes(ENCODE);
	    e.init(new SecretKeySpec(keyBytes, HmacSHA1));
	    //采用 HmacSHA1 计算编码结果
	    byte[] sha1EncodedBytes = e.doFinal(data);
	    //得到结果后将结果使用 Base64 编码，编码后的结果采用 UTF-8 转换为 String
	    return new String(Base64.encodeBase64(sha1EncodedBytes), ENCODE);
	}
	
}
