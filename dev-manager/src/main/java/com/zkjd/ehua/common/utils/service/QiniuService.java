package com.zkjd.ehua.common.utils.service;




import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.BaseService;

import cn.tomsnail.qiniu.QiniuPicUtil;
import cn.tomsnail.util.configfile.ConfigHelp;

@Service
public class QiniuService extends BaseService{
	
	public static int REFRESH_NUM = 500;
	
	private static final Logger LOGGER = Logger.getLogger(QiniuService.class);
	
	private static final byte[] lock = new byte[1];
	

	public boolean upload(String path){
		String fileName = ConfigHelp.getInstance("qiniu").getLocalConfig("qiniu.basePath", "")+"images/"+path;
		QiniuPicUtil.upload(fileName, "images"+path);
		return true;
	}
	
	
	
	public static void upload(String path, String bucket) {
		String fileName = ConfigHelp.getInstance("qiniu").getLocalConfig("qiniu.basePath", "")+"files/"+path;
		QiniuPicUtil.uploadF(fileName, "files"+path,bucket);
	}
	
	public static void upload(String path, String key,String bucket) {
		QiniuPicUtil.uploadF(path, "files"+key,bucket);
	}
	
	public static void delete(String key,String bucket) {
		QiniuPicUtil.delete(bucket, key);
	}
	
	public static boolean refresh(String url) {
		
		synchronized (lock) {
			int s =  QiniuPicUtil.refresh(url);
			if(s>=0){
				REFRESH_NUM = s;
				return true;
			}
			return false;
		}
	
	}
	
	
	public static void main(String[] args) throws Exception {
		String fileName = "E://02_workspace//26_ehua//ehua-manager//src//main//webapp//images//photo//2017//03//201703141489455626562.jpg";
		//RometeQiniuUtil.upload(fileName, "images/201703141489455626562.jpg", "ehua", "images","http://192.168.169.157:8291/qiniu/upload");
		//RometeQiniuUtil.upload(new File(fileName), "files"+path, "ehua", RometeQiniuUtil.getToken("http://192.168.169.124:8291/qiniu/getUploadToken?bucket=ehuaprice"));
		  
		// boolean ok = RometeQiniuUtil.localUpload(fileName, "images/201703141489455626563.jpg", "ehua", "http://192.168.169.124:8291/qiniu/token");
		 
		// System.out.println(ok);
	}
	
	
}
