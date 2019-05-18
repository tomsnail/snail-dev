package cn.tomsnail.snail.e3.util.qiniu;

import java.io.File;




import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;








import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;

/**
 * 七牛云图片操作
 * 
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年3月3日 上午10:38:38
 * @see
 */

public class SyncQiniuPicUtil {
	
	private static final Configuration cfg = new Configuration(Zone.autoZone());


	private static final Logger logger = LoggerFactory.getLogger(QiniuPicUtil.class);

	private static String ACCESS_KEY = ConfigHelp.getInstance("config").getLocalConfig("qiniu.ACCESS_KEY", "CFJGWKLIt6zw7HudQgrpvzhpW1oPdwniI4ECnqxe");

	private static String SECRET_KEY = ConfigHelp.getInstance("config").getLocalConfig("qiniu.SECRET_KEY", "GsPv0Dt4izT2-Gqkti8kIHKFEmGvq09HK5C_3kOw");

	private static final String defaultBucket = ConfigHelp.getInstance("config").getLocalConfig("qiniu.defaultBucket", "cunctao");

	private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

	private static final UploadManager uploadManager = new UploadManager(cfg);
	
	private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1000); 
	
	private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.MINUTES, queue);  

	private static String getUpToken() {
		return getUpToken(defaultBucket);
	}
	
	  
	   /**
		*    初始化秘钥，如果没有此步将使用默认秘钥，但可能失败    
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2016年3月3日 上午11:12:41
		* @see 
		* @param     
		* 	access
		* @param
		* 	 secret             
		* @return               
		* @status 正常
		* @exception no
		*/
	public synchronized void initAccessSecret(String access,String secret){
		initAuth(access, secret);
	}

	public static void initAuth(String access,String secret){
		ACCESS_KEY = access;
		SECRET_KEY = secret;
		auth = null;
		auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	}



	/**
	 * 获取上传token,覆盖上传
	 * 
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年3月3日 上午10:45:45
	 * @see
	 * @param
	 * @return
	 * @status 正常
	 * @exception no
	 */
	private static String getUpToken(String bucket, String key) {
		if(bucket==null){
			return auth.uploadToken(defaultBucket, key);
		}else{
			return auth.uploadToken(bucket, key);
		}
	}

	/**
	 * 获取上传token,如果有已存在key,则会上传失败
	 * 
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年3月3日 上午10:45:45
	 * @see
	 * @param
	 * @return
	 * @status 正常
	 * @exception no
	 */
	private static String getUpToken(String bucket) {
		if (bucket == null) {
			return getUpToken();
		}
		return auth.uploadToken(bucket);
	}

	/**
	 * 上传图片，使用默认空间
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年3月3日 上午10:44:55
	 * @see
	 * @param
	 * 	file 图片路径
	 * @param
	 * 	key  图片名称
	 * @return
	 * @throws Exception 
	 * @status 正常
	 * @exception no
	 */
	public static void upload(String file, String key) throws Exception {
		 upload(file, key, null,false);
	}

	/**
	 * 上传
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年3月3日 上午10:44:55
	 * @see
	 * @param
	 * 	file 图片路径
	 * @param
	 * 	key  图片名称
	 * @param
	 * 	bucket 上传空间名称
	 * @return
	 * @throws Exception 
	 * @status 正常
	 * @exception no
	 */
	public static void upload(String file, String key, String bucket) throws Exception {
		upload(file, key, bucket,false);
	}

	/**
	 * 覆盖上传，使用默认空间
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年3月3日 上午10:44:55
	 * @see
	 * @param
	 * 	file 图片路径
	 * @param
	 * 	key  图片名称
	 * @return
	 * @throws Exception 
	 * @status 正常
	 * @exception no
	 */
	public static void uploadF(String file, String key) throws Exception {
		 upload(file, key, null,true);
	}

	/**
	 * 覆盖上传
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年3月3日 上午10:44:55
	 * @see
	 * @param
	 * 	file 图片路径
	 * @param
	 * 	key  图片名称
	 * @param
	 * 	bucket 上传空间名称
	 * @return
	 * @throws Exception 
	 * @status 正常
	 * @exception no
	 */
	public static void uploadF(String file, String key, String bucket) throws Exception {
		 upload(file, key, bucket,true);
	}

	/**
	 * 
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年3月3日 上午10:45:02
	 * @see
	 * @param
	 * @return
	 * @status 正常
	 * @exception no
	 */

	private static void upload(final String file,final String key,final String bucket,final boolean isF) throws Exception {
		logger.info(file+" is upload start!");
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					logger.info(file+" is upload thread  start!");
					Response res = null;
					File f = new File(file);
					if(isF){
						res = uploadManager.put(f, key, getUpToken(bucket,key));
					}else{
						res = uploadManager.put(f, key, getUpToken(bucket));
					}
					DefaultPutRet ret = res.jsonToObject(DefaultPutRet.class);
					logger.info(res.toString());
					logger.info(res.bodyString());
					logger.info(file+" is upload thread  end!");
				} catch (QiniuException e) {
					Response r = e.response;
					logger.error(r.toString());
					logger.error(file+" is upload thread  error!");
					try {
						logger.error(r.bodyString());
					} catch (QiniuException e1) {
						logger.error("", e1);
					}
				}
			}
		});     
		
	}
	
	
	public static void main(String[] args) throws Exception {
		for(int i=0;i<1000;i++){
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.currentThread().sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			});     
		}
	}

}