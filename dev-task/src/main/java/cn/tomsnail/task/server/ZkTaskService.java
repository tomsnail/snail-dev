package cn.tomsnail.task.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.framwork.http.CommonMessage;
import cn.tomsnail.util.configfile.ConfigHelp;
import cn.tomsnail.zkclient.address.ZooKeeperAddress;
import cn.tomsnail.framwork.zk.ZkHelper;
import cn.tomsnail.task.log.DefaultTaskLogRecord;
import cn.tomsnail.task.log.ITaskLogRecord;

public class ZkTaskService extends ATimeSlotScheduler implements ITaskService {
	private ITaskChangeListener taskChangeListener = new DefaultTaskChangeListener();
	private ITaskLogRecord taskLogRecord = new DefaultTaskLogRecord();
	private  ZkHelper zkHelper = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkTaskService.class);
	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public List<Map<String, Object>> getTask(String name, String status,
			int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> delTask(String name) {
		// TODO Auto-generated method stub
		// 删除任务
				/**
				 * 0.判断节点状态，是否为运行状态
				 * 1.删除zookeeper中节点
				 * 2.更新mongodb
				 */
				if(zkHelper==null){
					String address = ZooKeeperAddress.ZK_ADDRESS;
					zkHelper = ZkHelper.getInstance(address);
				}
				Map<String,Object> resultmap=new HashMap<String,Object>();
				String path = TimeSlotConfig.TIME_SLOT_ROOT+"/"+name.replace(".", "/");
				LOGGER.info("要删除的任务路径："+path );
				System.out.println("要删除的任务路径："+path);
				String str =null;
				try {
					str = (String) zkHelper.readNode(TimeSlotConfig.TIME_SLOT_ROOT+"/"+name);
				} catch (Exception e1) {
					e1.printStackTrace();
					return null;
				}
				ZkTimeSlot zkTimeSlot = new ZkTimeSlot(str);
				
				if(zkTimeSlot.getStatus()==TimeSlotStatus.running.ordinal()){
					resultmap.put("result", "fail");
					resultmap.put("reason","node is running!");
				}else{
					try {
						zkHelper.deletePath(path);
						System.out.println("taskChangeListener---"+taskChangeListener);
						if(taskChangeListener!=null ) taskChangeListener.changed(path, "", ChangeType.delete);
						if(taskLogRecord!=null) taskLogRecord.recordLog(zkTimeSlot.getName(), zkTimeSlot.getPath(),"接口服务", ChangeType.delete,CommonMessage.SUCCESS,sdf.format(new Date()));
						resultmap.put("result", "success");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						resultmap.put("result", "fail");
						resultmap.put("reason", "throw exception");
						if(taskLogRecord!=null) taskLogRecord.recordLog(zkTimeSlot.getName(), zkTimeSlot.getPath(),"接口服务", ChangeType.delete,CommonMessage.FAILED,sdf.format(new Date()));
						e.printStackTrace();
					}
					
				}
				return resultmap;
	}

	@Override
	public Map<String, Object> cancelTask(String name) {
		// 取消任务
				/**
				 * 1.修改zookeeper中节点
				 * 2.更新mongodb
				 */
				if(zkHelper==null){
					String address = ZooKeeperAddress.ZK_ADDRESS;
					zkHelper = ZkHelper.getInstance(address);
				}
				Map<String,Object> resultmap=new HashMap<String,Object>();
				String path = TimeSlotConfig.TIME_SLOT_ROOT+"/"+name.replace(".", "/");
				String str = (String) zkHelper.readNode(TimeSlotConfig.TIME_SLOT_ROOT+"/"+name);
				ZkTimeSlot zkTimeSlot = new ZkTimeSlot(str);
				if(zkTimeSlot.getStatus()==TimeSlotStatus.running.ordinal()){
					resultmap.put("result", "fail");
					resultmap.put("reason","node is running!");
				}else{
					try {
						zkTimeSlot.setStatus(TimeSlotStatus.cancel.ordinal());
						String tskinfo=zkTimeSlot.getTaskInfo();
						String nodepath=zkTimeSlot.getPath();
						zkHelper.writeNode(nodepath, tskinfo);
						if(taskChangeListener!=null ) taskChangeListener.changed(path, zkTimeSlot.getTaskInfo(), ChangeType.cancle);
						if(taskLogRecord!=null) taskLogRecord.recordLog(zkTimeSlot.getName(), zkTimeSlot.getPath(),"接口服务", ChangeType.cancle,CommonMessage.SUCCESS,sdf.format(new Date()));
						resultmap.put("result", "success");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						resultmap.put("result", "fail");
						resultmap.put("reason", "throw exception");
						if(taskLogRecord!=null) taskLogRecord.recordLog(zkTimeSlot.getName(), zkTimeSlot.getPath(),"接口服务", ChangeType.cancle,CommonMessage.FAILED,sdf.format(new Date()));
						e.printStackTrace();
					}
					
				}
				return resultmap;
	}

	@Override
	public Map<String, Object> runTask(String name) {
		// 立即执行
		/**
		 * 1.更新zookeeper状态
		 * 2.更新mongodb
		 */
		if(zkHelper==null){
			String address = ZooKeeperAddress.ZK_ADDRESS;
			zkHelper = ZkHelper.getInstance(address);
		}
		Map<String,Object> resultmap=new HashMap<String,Object>();
		String path = TimeSlotConfig.TIME_SLOT_ROOT+"/"+name.replace(".", "/");
		String str = (String) zkHelper.readNode(TimeSlotConfig.TIME_SLOT_ROOT+"/"+name);
		ZkTimeSlot zkTimeSlot = new ZkTimeSlot(str);
		if(zkTimeSlot.getStatus()==TimeSlotStatus.running.ordinal()){
			resultmap.put("result", "fail");
			resultmap.put("reason","node is running!");
		}else{
			try {
				zkTimeSlot.setStatus(TimeSlotStatus.running.ordinal());
				System.out.println("taskinfo--"+zkTimeSlot.getTaskInfo());
				zkHelper.writeNode(zkTimeSlot.getPath(), zkTimeSlot.getTaskInfo());
				if(!container.contains(zkTimeSlot)){
					container.add(zkTimeSlot);
				}
				if(taskChangeListener!=null ) taskChangeListener.changed(path, zkTimeSlot.getTaskInfo(), ChangeType.run);
				if(taskLogRecord!=null) taskLogRecord.recordLog(zkTimeSlot.getName(), zkTimeSlot.getPath(),"接口服务", ChangeType.run,CommonMessage.SUCCESS,sdf.format(new Date()));
				resultmap.put("result", "success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				resultmap.put("result", "fail");
				resultmap.put("reason", "throw exception");
				if(taskLogRecord!=null) taskLogRecord.recordLog(zkTimeSlot.getName(), zkTimeSlot.getPath(),"接口服务", ChangeType.run,CommonMessage.FAILED,sdf.format(new Date()));
				e.printStackTrace();
			}
			
		}
		return resultmap;
	}

	@Override
	public List<Map<String, Object>> queryTaskLog(String name) {
		// TODO Auto-generated method stub
		return null;
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

	public ZkHelper getZkHelper() {
		return zkHelper;
	}

	public void setZkHelper(ZkHelper zkHelper) {
		this.zkHelper = zkHelper;
	}

	@Override
	public void schedule() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
