package cn.tomsnail.task.client;


import org.I0Itec.zkclient.IZkDataListener;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.util.configfile.ConfigHelp;
import cn.tomsnail.zkclient.address.ZooKeeperAddress;
import cn.tomsnail.framwork.zk.ZkHelper;
import cn.tomsnail.task.server.TimeSlotConfig;
import cn.tomsnail.task.server.TimeSlotStatus;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 上午11:44:35
 * @see 
 */
public abstract class ZookeeperTaskCall<T> extends ATaskCall<T>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperTaskCall.class);
	
	private IZkDataListener iZkDataListener;
	

	public ZookeeperTaskCall() throws Exception {
		super();
	}

	private  ZkHelper zkHelper = null;
	
	@Override
	public void register() {
		try {
			LOGGER.debug("[任务中心客户端]客户端注册启动...");
			init();//初始化
			String path = TimeSlotConfig.TIME_SLOT_ROOT;
			if(!zkHelper.isExtisPath(path)){
				zkHelper.createPathWithParent(path, true);
			}
			if(!zkHelper.isExtisPath(path+"/"+taskName)){
				zkHelper.createPath(path+"/"+taskName, true);
				zkHelper.writeNode(path+"/"+taskName, this.getTaskInfo());
			}
			subscribe();
			LOGGER.debug("[任务中心客户端]客户端注册完成。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void subscribe(){
		LOGGER.debug("[任务中心客户端]客户端监听数据变化开始...");

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
		zkHelper.getZkClient().subscribeDataChanges(TimeSlotConfig.TIME_SLOT_ROOT+"/"+taskName, iZkDataListener);
	}

	@Override
	public void callback(Object data) {
		
		String taskInfo = (String) data;
		int status = Integer.valueOf(taskInfo.split(",")[4]);
		LOGGER.info("[任务中心客户端]客户端监听到数据变化..."+taskInfo.split(",")[1]+":"+status);
		T result = null;
		switch(status){
			case 0:break;
			case 1:result = call();break;
			case 3:cancel();break;
			default:break;
		}
		if(result!=null){
			LOGGER.info("[任务中心客户端]客户端执行任务完成，"+taskInfo.split(",")[1]+":"+status);
			this.status = TimeSlotStatus.finisied.ordinal();
			taskInfo=this.getTaskInfo()+","+result;
			zkHelper.writeNode(TimeSlotConfig.TIME_SLOT_ROOT+"/"+taskName, taskInfo);
			zkHelper.deletePath(TimeSlotConfig.TIME_SLOT_RUN+"/"+taskName+"_"+TimeSlotStatus.running);
			
		}
	}

	@Override
	public void init() throws Exception {
		String address = ZooKeeperAddress.ZK_ADDRESS;
		zkHelper = ZkHelper.getInstance(address);
		if(StringUtils.isBlank(taskName)){
			throw new Exception(" taskName is null ");
		}
	}

	@Override
	public void destory() {
		//
	}

	@Override
	public void cancel() {
		LOGGER.info("[任务中心客户端]客户端执行取消任务，"+taskName);
		if(zkHelper.isExtisPath(TimeSlotConfig.TIME_SLOT_ROOT+"/"+taskName)){
			zkHelper.getZkClient().unsubscribeDataChanges(TimeSlotConfig.TIME_SLOT_ROOT+"/"+taskName, iZkDataListener);
			iZkDataListener = null;
//			zkHelper.deletePath(TimeSlotConfig.TIME_SLOT_ROOT+"/"+taskName);
		}
	}	
}
