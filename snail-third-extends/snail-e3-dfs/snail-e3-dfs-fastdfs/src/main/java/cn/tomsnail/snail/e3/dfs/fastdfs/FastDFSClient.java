package cn.tomsnail.snail.e3.dfs.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;



import com.github.tobato.fastdfs.conn.ConnectionManager;
import com.github.tobato.fastdfs.conn.FdfsConnectionPool;
import com.github.tobato.fastdfs.conn.PooledConnectionFactory;
import com.github.tobato.fastdfs.conn.TrackerConnectionManager;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;

/**
 * @author yangsong
 *
 */
public class FastDFSClient {
	
	
	 private static FastFileStorageClient storageClient  = null;
	 
	 static {
		 
		 PooledConnectionFactory connectionFactory = new PooledConnectionFactory();
		 connectionFactory.setConnectTimeout(Integer.valueOf(ConfigHelp.getInstance("config").getLocalConfig("fastdfs.conn.timeout", "30000")));
		 connectionFactory.setSoTimeout(Integer.valueOf(ConfigHelp.getInstance("config").getLocalConfig("fastdfs.so.timeout", "20000")));
		 FdfsConnectionPool fdfsConnectionPool = new FdfsConnectionPool(connectionFactory);
		 List<String> trackerList = new ArrayList<String>();
		 trackerList.add(ConfigHelp.getInstance("config").getLocalConfig("fastdfs.tracker.address", "192.168.169.157:22122"));
		 TrackerConnectionManager trackerConnectionManager = new TrackerConnectionManager();
		 trackerConnectionManager.setTrackerList(trackerList);
		 trackerConnectionManager.setPool(fdfsConnectionPool);
		 trackerConnectionManager.initTracker();
		 TrackerClient trackerClient = new DefaultTrackerClient(trackerConnectionManager);
		 ConnectionManager connectionManager = new ConnectionManager(fdfsConnectionPool);	 
		 DefaultFastFileStorageClient defaultFastFileStorageClient = new DefaultFastFileStorageClient();
		 defaultFastFileStorageClient.setConnectionManager(connectionManager);
		 defaultFastFileStorageClient.setTrackerClientService(trackerClient);
		 storageClient = defaultFastFileStorageClient;
		 
	 }
	 

	 public static String upload(File file) throws Exception{
		 FileInputStream inputStream = new FileInputStream (file);
	     StorePath storePath = storageClient.uploadFile(inputStream,file.length(), FilenameUtils.getExtension(file.getName()),null);
		 return storePath.getFullPath();
	 }
	 
	 public static String upload(byte[] fileBytes,String extFileName,String key) throws Exception{
//		 ByteInputStream inputStream = new ByteInputStream(fileBytes, fileBytes.length);
//		 Set<MateData> metaDataSet = new HashSet<>();
//		 metaDataSet.add(new MateData("MD5KEY",key));
//	     StorePath storePath = storageClient.uploadFile(inputStream,fileBytes.length, extFileName,metaDataSet);
//		 return storePath.getFullPath();
		 return null;
	 }
	 
	 public static void delete(String path){
		 storageClient.deleteFile(path);
	 }
	 
	 public static void delete(String groupName,String path){
		 storageClient.deleteFile(groupName,path);
	 }
	 
	 public static void deleteAutoGroup(String path){
		 String _groupName = path.split("/")[0];
		 String _path = path.replace(_groupName+"/", "");
		 storageClient.deleteFile(_groupName,_path);
	 }
	 
	 public static void main(String[] args) {
		 try {
			 String path = "npc0/M00/00/00/wKgKAlsyZN6AVbLxAALKsKEINVk34.jpeg";
			 
			 String _groupName = path.split("/")[0];
			 String _path = path.replace(_groupName+"/", "");
			 System.out.println(_groupName);
			 System.out.println(_path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
