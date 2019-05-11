package cn.tomsnail.snail.e3.util.qiniu;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;


  
   /**
	*        远程调用Token，七牛云上传本地文件服务
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年3月12日 下午4:25:22
	* @see 
	*/     
public class RometeQiniuUtil {
	
	private static final Configuration cfg = new Configuration(Zone.autoZone());
	
	private static final Logger logger = LoggerFactory.getLogger(RometeQiniuUtil.class);
	
	private static final UploadManager uploadManager = new UploadManager(cfg);
	
	
	private static final String url = ConfigHelp.getInstance("config").getLocalConfig("system.qiniu.url", "http://192.168.169.157:8291/qiniu");
	  
	   /**
		*        远程上传，key:文件名称  bucket:七牛云空间 
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年3月12日 下午4:25:53
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static boolean upload(String localFile,String key,String bucket,String type){
		File file = new File(localFile);
        PostMethod filePost = new PostMethod(url+"/upload");
        HttpClient client = new HttpClient();
        
        try {
            filePost.addRequestHeader("key", key);
            filePost.addRequestHeader("bucket", bucket);
            filePost.addRequestHeader("type", type);
            Part[] parts = { new FilePart(file.getName(), file) };
            filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
            	logger.info("上传成功");
                return true;
            } else {
            	logger.info("上传失败");
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            filePost.releaseConnection();
        }
	}
	
	  
	   /**
		*       本地上传 ，key:文件名称  bucket:七牛云空间名称,token:上传令牌，通过getToken获取
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年3月17日 上午9:38:33
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static boolean localUpload(String localFile,String key,String bucket){
		BufferedReader in = null;  
	    String content = null;  
		try {
			 // 定义HttpClient  
        	DefaultHttpClient client = new DefaultHttpClient();  
            // 实例化HTTP方法  
            HttpGet request = new HttpGet();  
            request.setURI(new URI(url+"/token/"+bucket+"/"+key+""));  
            HttpResponse response = client.execute(request);  
  
            in = new BufferedReader(new InputStreamReader(response.getEntity()  
                    .getContent()));  
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            String NL = System.getProperty("line.separator");  
            while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            }  
            in.close();  
            content = sb.toString();  
            content = content.replaceAll("\r\n", "").replace("\n", "").replace("\r", "");
			Response res = null;
			System.out.println(localFile);
			System.out.println(key);
			System.out.println(content);
			res = uploadManager.put(new File(localFile), key, content);
			DefaultPutRet ret = res.jsonToObject(DefaultPutRet.class);
			logger.info(res.bodyString());
			System.out.println(res.bodyString());
			return true;
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
			return false;
		} finally {  
            if (in != null) {  
                try {  
                    in.close();
                } catch (Exception e) {  
                	logger.error("", e);
                }  
            }  
        }  
	}
	public static void main(String[] args) {
		localUpload("E://201703121489312011589.jpg","images/photo/2017/12/04/1235454.jpg","ehua");
	}
}
