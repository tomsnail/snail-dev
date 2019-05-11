package cn.tomsnail.snail.e3.util.qiniu;

import java.io.File;










import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
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

public class QiniuPicUtil {

	private static final Logger logger = LoggerFactory.getLogger(QiniuPicUtil.class);

	private static String ACCESS_KEY = ConfigHelp.getInstance("config").getLocalConfig("qiniu.ACCESS_KEY", "CFJGWKLIt6zw7HudQgrpvzhpW1oPdwniI4ECnqxe");

	private static String SECRET_KEY = ConfigHelp.getInstance("config").getLocalConfig("qiniu.SECRET_KEY", "GsPv0Dt4izT2-Gqkti8kIHKFEmGvq09HK5C_3kOw");

	private static final String defaultBucket = ConfigHelp.getInstance("config").getLocalConfig("qiniu.defaultBucket", "cunctao");

	private static final Configuration cfg = new Configuration(Zone.autoZone());
	
	private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

	private static final UploadManager uploadManager = new UploadManager(cfg);
	
	private static final BucketManager bucketManager = new BucketManager(auth,cfg);
	
	private static final CdnManager c = new CdnManager(auth);

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
	public static String getUpToken(String bucket, String key) {
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
	public static String getUpToken(String bucket) {
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
	 * @status 正常
	 * @exception no
	 */
	public static void upload(String file, String key) {
		upload(new File(file), key, null,false);
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
	 * @status 正常
	 * @exception no
	 */
	public static void upload(String file, String key, String bucket) {
		upload(new File(file), key, bucket,false);
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
	 * @status 正常
	 * @exception no
	 */
	public static void uploadF(String file, String key) {
		upload(new File(file), key, null,true);
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
	 * @status 正常
	 * @exception no
	 */
	public static void uploadF(String file, String key, String bucket) {
		upload(new File(file), key, bucket,true);
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

	public static void upload(File file, String key, String bucket,boolean isF) {
		try {
			Response res = null;
			if(isF){
				res = uploadManager.put(file, key, getUpToken(bucket,key));
			}else{
				res = uploadManager.put(file, key, getUpToken(bucket));
			}
			DefaultPutRet ret = res.jsonToObject(DefaultPutRet.class);
			System.out.println(res.toString());
			logger.info(res.toString());
			logger.info(res.bodyString());
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			logger.error(r.toString());
			System.out.println(r.toString());
			try {
				logger.error(r.bodyString());
				System.out.println(r.toString());
			} catch (QiniuException e1) {
				logger.error("", e1);
			}
		}
	}
	
	public static boolean delete(String bucket,String key){
		try {
			bucketManager.delete(bucket, key);
			return true;
		} catch (QiniuException e) {
			logger.error("", e);
		}
		return false;
	}
	
	public static int refresh(String url){
		
		 CdnResult.RefreshResult result;
		try {
			result = c.refreshUrls(new String[]{url});
			if(result!=null){
				logger.error(result.toString());
				System.out.println(result.toString());
				if(result.code==200)
					return result.urlSurplusDay;
				else{
					logger.error(result.code+"");
					System.out.println(result.code+"");
				}
			}
		} catch (QiniuException e) {
			logger.error("", e);
		}
		return -1;
	}
	
	public static void main(String[] args) {
		uploadF("C://Users//Public//Pictures//Sample Pictures//Chrysanthemum.jpg","Desert1.jp");
	}

}
