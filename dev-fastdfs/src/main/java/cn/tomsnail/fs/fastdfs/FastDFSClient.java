package cn.tomsnail.fs.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.util.configfile.ConfigHelp;

import com.github.tobato.fastdfs.conn.ConnectionManager;
import com.github.tobato.fastdfs.conn.FdfsConnectionPool;
import com.github.tobato.fastdfs.conn.PooledConnectionFactory;
import com.github.tobato.fastdfs.conn.TrackerConnectionManager;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

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
		 ByteInputStream inputStream = new ByteInputStream(fileBytes, fileBytes.length);
		 Set<MateData> metaDataSet = new HashSet<>();
		 metaDataSet.add(new MateData("MD5KEY",key));
	     StorePath storePath = storageClient.uploadFile(inputStream,fileBytes.length, extFileName,metaDataSet);
		 return storePath.getFullPath();
	 }
	 
	 public static void main(String[] args) {
		 try {
			 InputStream input = new FileInputStream(new File("E:/02_workspace/35_npcmis/npc-dc/npc-dc-basic/npc-dc-basic-webapi/sign/293196195522586.pdf"));

			 byte[] byt = new byte[input.available()];

			 input.read(byt);
			System.out.println(upload(byt,"pdf","234324325"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
