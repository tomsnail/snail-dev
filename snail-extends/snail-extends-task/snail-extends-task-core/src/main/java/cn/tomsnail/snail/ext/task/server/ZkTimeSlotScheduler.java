package cn.tomsnail.snail.ext.task.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.snail.core.http.CommonMessage;
import cn.tomsnail.snail.core.util.zkclient.ZkHelper;
import cn.tomsnail.snail.core.util.zkclient.address.ZooKeeperAddress;
import cn.tomsnail.snail.ext.task.log.DefaultTaskLogRecord;
import cn.tomsnail.snail.ext.task.log.ITaskLogRecord;





/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月28日 下午4:36:39
 * @see 
 */
public class ZkTimeSlotScheduler extends ATimeSlotScheduler{
	private ITaskChangeListener taskChangeListener = new DefaultTaskChangeListener();
	private ITaskLogRecord taskLogRecord = new DefaultTaskLogRecord();
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkTimeSlotScheduler.class);
	private  ZkHelper zkHelper = null;
	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public ZkTimeSlotScheduler(boolean nowStart){
		super();
		if(nowStart){
			this.init();
			this.start();
		}
	}
	
	public ZkTimeSlotScheduler(){
		super();
	}

	@Override
	public void schedule() {
		try {
			LOGGER.debug("[ZkTimeSlotScheduler]执行线程：schedule()开始...");
			ZkTimeSlot zkTimeSlot = (ZkTimeSlot) container.take();			
			if(!zkHelper.isExtisPath(zkTimeSlot.getPath())){//没有了  可能是客户端自己删除了 可能是数据节点出问题了,但我决定都不在调度该任务执行了，因为执行不了了
				LOGGER.debug("[ZkTimeSlotScheduler]container取出的任务，没有path，结束。");
				return;
			}
			long ctime = System.currentTimeMillis();
			LOGGER.debug("开始执行schedule任务"+zkTimeSlot.getName()+"，任务执行time:"+Long.valueOf(zkTimeSlot.getsTime()));
			LOGGER.debug("开始执行schedule任务"+zkTimeSlot.getName()+"，当前time:"+ctime+rate);
			if((Long.valueOf(zkTimeSlot.getsTime()))<=ctime+rate){
				if(!zkHelper.isExtisPath(TimeSlotConfig.TIME_SLOT_RUN)){
					zkHelper.createPath(TimeSlotConfig.TIME_SLOT_RUN);
				}
				if(zkHelper.isExtisPath(TimeSlotConfig.TIME_SLOT_RUN+"/"+zkTimeSlot.getName()+"_"+TimeSlotStatus.running)){
					return;
				}
				boolean isSuccessd = zkHelper.createPath(TimeSlotConfig.TIME_SLOT_RUN+"/"+zkTimeSlot.getName()+"_"+TimeSlotStatus.running,true);
				if(!isSuccessd){
					return;
				}
				LOGGER.info(zkTimeSlot.getName()+" is start ");
				zkTimeSlot.setStatus(TimeSlotStatus.running.ordinal());
				zkHelper.writeNode(zkTimeSlot.getPath(), zkTimeSlot.getTaskInfo());
				LOGGER.debug("开始执行schedule任务"+zkTimeSlot.getName()+"，修改为执行状态。监听zk节点变化，以及数据变化。");

				if(taskChangeListener!=null ) taskChangeListener.changed(zkTimeSlot.getPath(), zkTimeSlot.getTaskInfo(), ChangeType.schedule);
				if(taskLogRecord!=null) taskLogRecord.recordLog(zkTimeSlot.getName(), zkTimeSlot.getPath(),"系统服务", ChangeType.schedule,CommonMessage.SUCCESS,sdf.format(new Date()));
				addListener(zkTimeSlot.getPath());
			}else{
				LOGGER.debug("开始执行schedule任务"+zkTimeSlot.getName()+"，执行时间不符合条件，重新放回容器。");
				container.add(zkTimeSlot);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		LOGGER.info("[ZkTimeSlotScheduler]执行初始化：init()...");
		String address = ZooKeeperAddress.ZK_ADDRESS;
		zkHelper = ZkHelper.getInstance(address);
		
		if(!zkHelper.isExtisPath(TimeSlotConfig.TIME_SLOT_ROOT)){
			zkHelper.createPathWithParent(TimeSlotConfig.TIME_SLOT_ROOT, true);
		}
		
		List<String> subNodes = zkHelper.getPath(TimeSlotConfig.TIME_SLOT_ROOT);
		if(subNodes!=null){
			for(String node:subNodes){
				String str = (String) zkHelper.readNode(TimeSlotConfig.TIME_SLOT_ROOT+"/"+node);
				ZkTimeSlot zkTimeSlot = new ZkTimeSlot(str);
				zkTimeSlot.calculate();
				LOGGER.info("服务端监听到zk变化，变化节点加入容器："+zkTimeSlot.getName()+",运行时间："+zkTimeSlot.getTime());
				if(zkTimeSlot.add()&&!container.contains(zkTimeSlot)){
					container.add(zkTimeSlot);
					if(zkTimeSlot.getStatus()==TimeSlotStatus.running.ordinal()){
						addListener(zkTimeSlot.getPath());
					}
				}
			}
		}
		watchChilds();
		LOGGER.info("[ZkTimeSlotScheduler]执行初始化init()完成。");
	}
	
	public void watchChilds(){
		zkHelper.getZkClient().subscribeChildChanges(TimeSlotConfig.TIME_SLOT_ROOT, new IZkChildListener() {

			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds)
					throws Exception {
				//监听目录 如果有新增的节点就加入任务
				for(String currentChild:currentChilds){
					String str = (String) zkHelper.readNode(parentPath+"/"+currentChild);
					ZkTimeSlot zkTimeSlot = new ZkTimeSlot(str);
					zkTimeSlot.calculate();
					if(zkTimeSlot.add()&&!container.contains(zkTimeSlot)){
						container.add(zkTimeSlot);
						if(zkTimeSlot.getStatus()==TimeSlotStatus.running.ordinal()){
							addListener(zkTimeSlot.getPath());
						}
					}
				}
			}
		});
	}
	
	
	private void addListener(String path){
		LOGGER.info("[ZkTimeSlotScheduler]addListener监听运行的任务数据变化...");
		zkHelper.getZkClient().subscribeDataChanges(path, new IZkDataListener() {
			
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				LOGGER.info(dataPath+" is delete ");
				zkHelper.deletePath(TimeSlotConfig.TIME_SLOT_RUN+"/"+dataPath.replace(TimeSlotConfig.TIME_SLOT_ROOT+"/", "")+"_"+TimeSlotStatus.running);
				zkHelper.getZkClient().unsubscribeDataChanges(dataPath, this);
			}
			
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				ZkTimeSlot zkTimeSlot = new ZkTimeSlot(data.toString());
				zkTimeSlot.calculate();
				if(zkTimeSlot.getStatus()==TimeSlotStatus.wait.ordinal()){
					LOGGER.info(zkTimeSlot.getName()+" wait " );
				}else if(zkTimeSlot.getStatus()==TimeSlotStatus.running.ordinal()){
					LOGGER.info(zkTimeSlot.getName()+" running " );
				}else if(zkTimeSlot.getStatus()==TimeSlotStatus.finisied.ordinal()){
					LOGGER.info(zkTimeSlot.getName()+" finisied " +" "+data);
					if(zkTimeSlot.add()){
						if(!container.contains(zkTimeSlot)){
							container.add(zkTimeSlot);
						}
					}else{
						zkTimeSlot.setStatus(TimeSlotStatus.cancel.ordinal());
						zkHelper.writeNode(zkTimeSlot.getPath(), zkTimeSlot.getTaskInfo());
						if(taskChangeListener!=null ) taskChangeListener.changed(zkTimeSlot.getPath(), zkTimeSlot.getTaskInfo(), ChangeType.refresh);
						if(taskLogRecord!=null) taskLogRecord.recordLog(zkTimeSlot.getName(), zkTimeSlot.getPath(),"系统服务", ChangeType.refresh,CommonMessage.SUCCESS,sdf.format(new Date()));

					}
					zkHelper.getZkClient().unsubscribeDataChanges(dataPath, this);
				}else if(zkTimeSlot.getStatus()==TimeSlotStatus.cancel.ordinal()){
					LOGGER.info(zkTimeSlot.getName()+" cancel " );
					container.remove(zkTimeSlot);
					zkHelper.getZkClient().unsubscribeDataChanges(dataPath, this);
				}
			}
		});
	}
	public static void main(String[] args) {
		new ZkTimeSlotScheduler(true);
	}

	public ITaskChangeListener getTaskChangeListener() {
		return taskChangeListener;
	}

	public void setTaskChangeListener(ITaskChangeListener taskChangeListener) {
		this.taskChangeListener = taskChangeListener;
	}

	public ITaskLogRecord getTaskLogRecord() {
		return taskLogRecord;
	}

	public void setTaskLogRecord(ITaskLogRecord taskLogRecord) {
		this.taskLogRecord = taskLogRecord;
	}


}
