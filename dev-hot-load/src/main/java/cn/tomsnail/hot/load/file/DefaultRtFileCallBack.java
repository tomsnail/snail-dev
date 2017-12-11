package cn.tomsnail.hot.load.file;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.tomsnail.hot.load.m.HotLoadManager;
import cn.tomsnail.hot.load.m.LoadJar;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月22日 上午10:08:33
	* @see 
	*/     
public class DefaultRtFileCallBack implements RtFileCallBack{

	private LoadJar loadJar;
	
	private HotLoadManager hotLoadManager;
	
	
	
	
	@Override
	public void call(File file, FileChangeType fileChangeType) {
		if(fileChangeType==null||file==null){
			return;
		}
		switch(fileChangeType){
			case FCreate:_FCreate(file);break;
			case FChange:_FChange(file);break;
			default:break;
		}
	}
	
	public DefaultRtFileCallBack(ClassLoader classLoader,HotLoadManager hotLoadManager) {
		this.loadJar = new LoadJar(classLoader);
		this.hotLoadManager = hotLoadManager;
	}

	private void _FCreate(File file){
		try {
			loadJar.loadFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void _FChange(File file){
		if(file.getName().endsWith("service.properties")){
			try {
				updateConfig(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			_FCreate(file);
		}
	}
	
	private synchronized void updateConfig(File file) {
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		Properties properties = null;
		try {
			properties =  new Properties(); 
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream);
			properties.load(inputStreamReader);
			Iterator<Object> it = properties.keySet().iterator();
			while(it.hasNext()){
				String key = it.next().toString();
				System.out.println("key:"+key);
				if(HotLoadManager.ClassMapList.containsKey(key)){
				}else{
					List<String> nList = new CopyOnWriteArrayList<String>();
					HotLoadManager.ClassMapList.put(key, nList);
				}
				List<String> list = HotLoadManager.ClassMapList.get(key);
				List<String> nList = new CopyOnWriteArrayList<String>();
				boolean isUpdate = false;
				if(list==null){
					list = new CopyOnWriteArrayList<String>();
					isUpdate = true;
				}
				String value = properties.getProperty(key);
				String[] clazzs = value.split(";");
				int size = clazzs.length;
				for(String clazz:clazzs){
					nList.add(clazz);
					if(list.contains(clazz)){
						size--;
					}
				}
				if(size!=0){
					isUpdate = true;
					HotLoadManager.ClassMapList.put(key, nList);
				}
				if(isUpdate){
					hotLoadManager.update(key);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(inputStreamReader!=null){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(properties!=null){
				properties.clear();
				properties = null;
			}
		}		
	}

}
