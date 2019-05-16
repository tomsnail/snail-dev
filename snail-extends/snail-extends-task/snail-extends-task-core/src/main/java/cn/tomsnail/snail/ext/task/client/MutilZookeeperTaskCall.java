package cn.tomsnail.snail.ext.task.client;


import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.snail.core.util.zkclient.ZkHelper;
import cn.tomsnail.snail.core.util.zkclient.address.ZooKeeperAddress;
import cn.tomsnail.snail.ext.task.server.TimeSlotConfig;
import cn.tomsnail.snail.ext.task.server.TimeSlotStatus;





/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 上午11:44:35
 * @see 
 */
public  class MutilZookeeperTaskCall extends MutilTaskCall{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MutilZookeeperTaskCall.class);
	
	private IZkDataListener iZkDataListener;
	
	public MutilZookeeperTaskCall()  {
		init();
	}
	private  ZkHelper zkHelper = null;
	
	@Override
	public synchronized <T> void register(String name,TaskCall<T> taskCall,int type,long time) {
		try {
			if(TaskCallMap.containsKey(name)){
				return;
			}
			String path = TimeSlotConfig.TIME_SLOT_ROOT;
			if(!zkHelper.isExtisPath(path)){
				zkHelper.createPathWithParent(path, true);
			}
			if(!zkHelper.isExtisPath(path+"/"+name)){
				zkHelper.createPath(path+"/"+name, true);
				zkHelper.writeNode(path+"/"+name, getTaskInfo(type,name,time,0));
			}
			subscribe(name);
			this.addTaskCall(name, taskCall);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void subscribe(String name){
		iZkDataListener = new IZkDataListener(){
			@Override
			public void handleDataChange(String dataPath, Object data)
					throws Exception {
				callback(data);
			}
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
			}
		} ;
		zkHelper.getZkClient().subscribeDataChanges(TimeSlotConfig.TIME_SLOT_ROOT+"/"+name, iZkDataListener);
	}

	@Override
	public  void callback(Object data) {
		String taskInfo = (String) data;
		int status = Integer.parseInt(taskInfo.split(",")[4]);
		LOGGER.info(taskInfo.split(",")[1]+":"+status);
		switch(status){
			case 0:break;
			case 1:call(data);break;
			case 3:cancel(taskInfo.split(",")[1]);break;
			default:break;
		}

	}

	@Override
	public void init() {
		String address = ZooKeeperAddress.ZK_ADDRESS;
		zkHelper = ZkHelper.getInstance(address);
		
	}

	@Override
	public void destory(String name) {
		//
	}

	@Override
	public void cancel(String name) {
		if(zkHelper.isExtisPath(TimeSlotConfig.TIME_SLOT_ROOT+"/"+name)){
			zkHelper.getZkClient().unsubscribeDataChanges(TimeSlotConfig.TIME_SLOT_ROOT+"/"+name, iZkDataListener);
			iZkDataListener = null;
			zkHelper.deletePath(TimeSlotConfig.TIME_SLOT_ROOT+"/"+name);
			removeTaskCall(name);
		}
	}

	@Override
	public void call(Object data) {
		String name = data.toString().split(",")[1];
		int type = Integer.parseInt(data.toString().split(",")[0]);
		long time = Long.parseLong(data.toString().split(",")[3]);
		if(MutilTaskCall.TaskCallMap.containsKey(name)){
			Object result = MutilTaskCall.TaskCallMap.get(name).call();
			if(result!=null){
				int status = TimeSlotStatus.finisied.ordinal();
				String taskInfo=this.getTaskInfo(type,name,time,status)+","+result;
				zkHelper.writeNode(TimeSlotConfig.TIME_SLOT_ROOT+"/"+name, taskInfo);
				zkHelper.deletePath(TimeSlotConfig.TIME_SLOT_RUN+"/"+name+"_"+TimeSlotStatus.running);
			}
		}
	}

	public String getTaskInfo(int type,String name,long time,int status){
		return type+","+name+","+System.currentTimeMillis()+","+time+","+status;
	}
	
}
