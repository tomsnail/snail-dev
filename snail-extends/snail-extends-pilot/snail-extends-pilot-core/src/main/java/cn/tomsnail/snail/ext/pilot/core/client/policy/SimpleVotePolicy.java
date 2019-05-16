package cn.tomsnail.snail.ext.pilot.core.client.policy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.snail.core.util.zkclient.ZkHelper;
import cn.tomsnail.snail.ext.pilot.core.client.SmartZkSubProxy;
import cn.tomsnail.snail.ext.pilot.core.model.CommandType;
import cn.tomsnail.snail.ext.pilot.core.model.Consts;
import cn.tomsnail.snail.ext.pilot.core.model.ServiceKeeper;
import cn.tomsnail.snail.ext.pilot.core.model.ServiceProcess;
import cn.tomsnail.snail.ext.pilot.core.model.ServiceStatus;





/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月11日 上午9:25:35
 * @see 
 */
public class SimpleVotePolicy implements IVotePolicy{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmartZkSubProxy.class);
	
	private ZkHelper zkHelper;
	
	public SimpleVotePolicy(ZkHelper zkHelper){
		this.zkHelper = zkHelper;
	}

	
	public String getNodePath(ServiceProcess serviceProcess) {
		ServiceProcess process = this.getNextServiceProcess(serviceProcess);
		if(process==null){
			return null;
		}
		return Consts.ZK_ROOT+"/"+process.getNode();
	}
	
	private ServiceProcess getNextServiceProcess(ServiceProcess process){
		String skPath = Consts.ZK_SERVICE_KEEPER+"/"+process.getName().split("_")[0];
		LOGGER.info(Consts.ZK_PROXY+" Change : skPath "+skPath);
		ServiceKeeper serviceKeeper = (ServiceKeeper) zkHelper.readNode(skPath);
		List<ServiceProcess> list = serviceKeeper.getNodes();
		int cn = Integer.parseInt(serviceKeeper.getCurrentCount());
		LOGGER.info(Consts.ZK_PROXY+" Change : list "+list.size());
		List<ServiceProcess> tempList = new ArrayList<ServiceProcess>();
		int c = getActiveCount(list, tempList);
		if(cn == c){
			return null;
		}
		serviceKeeper.setCurrentCount(c+"");
		zkHelper.writeNode(skPath, serviceKeeper);
		return getWillActive(tempList);
	}

	private ServiceProcess getWillActive(List<ServiceProcess> tempList) {
		ServiceProcess willActive = null;
		for(ServiceProcess _process:tempList){
			if(_process.getStatus().equals(ServiceStatus.SANDBY)){
				String pPath = Consts.ZK_PROXY+"/"+_process.getNode();
				String sPath = Consts.ZK_ROOT+"/"+_process.getNode()+"/"+_process.getName().split("_")[0];
				LOGGER.info(Consts.ZK_PROXY+" Change :pPath "+pPath);
				if(zkHelper.isExtisPath(pPath)&&!zkHelper.isExtisPath(sPath)){
					LOGGER.info(Consts.ZK_PROXY+" Change :pPath "+true);
					willActive = _process;
				}
			}
		}
		return willActive;
	}

	private int getActiveCount(List<ServiceProcess> list, List<ServiceProcess> tempList) {
		int c=0;
		for(ServiceProcess _process:list){
			String sPath = Consts.ZK_ROOT+"/"+_process.getNode()+"/"+_process.getName().split("_")[0];
			LOGGER.info(Consts.ZK_PROXY+" Change : sPath "+sPath);
			if(zkHelper.isExtisPath(sPath)){
				c++;
				LOGGER.info(Consts.ZK_PROXY+" Change : sPath extis");
			}else{
				tempList.add(_process);
				_process.setStatus(ServiceStatus.SANDBY);
			}
		}
		return c;
	}


	
	public boolean canStart(ServiceProcess serviceProcess) {
		LOGGER.info("canStart " +serviceProcess.getNode()+" "+serviceProcess.getName());
		boolean s = false;
		if(serviceProcess.getCommand().equals(CommandType.START)){
			if(tryGetLock(serviceProcess.getName())){
				try {
					String skPath = Consts.ZK_SERVICE_KEEPER+"/"+serviceProcess.getName().split("_")[0];
					ServiceKeeper serviceKeeper = (ServiceKeeper) zkHelper.readNode(skPath);
					s = getSstatus(serviceProcess,serviceKeeper);
					addNodes(serviceKeeper,serviceProcess);
					LOGGER.info("writeNode serviceKeeper " +serviceKeeper);
					zkHelper.writeNode(skPath, serviceKeeper);
				} catch (Exception e) {
					e.printStackTrace();
					s = false;
				}
				 unLock(serviceProcess.getName());
			}
		}else{
			s = true;
		}
		return s;
	}
	
	private void addNodes(ServiceKeeper serviceKeeper,ServiceProcess serviceProcess ){
		ServiceProcess canAdd = null;
		List<ServiceProcess> list = getServiceKeeperNodes(serviceKeeper);
		for(ServiceProcess process:list){
			if(process.getName().equals(serviceProcess.getName())&&process.getNode().equals(serviceProcess.getNode())){
				canAdd = process;
				break;
			}
		}
		if(canAdd==null){
			list.add(serviceProcess);
		}else{
			canAdd.setStatus(ServiceStatus.SANDBY);
		}
	}

	private boolean getSstatus(ServiceProcess serviceProcess, ServiceKeeper serviceKeeper) {
		boolean s = false;
		int ln = Integer.parseInt(serviceKeeper.getLimitNumber());
		int cn = getRealCurrentCount(serviceKeeper);
		if(cn<ln){
			serviceProcess.setStatus(ServiceStatus.ACTIVE);
			cn = cn+1;
			serviceKeeper.setCurrentCount(cn+"");
			s = true;
		}else{
			serviceProcess.setStatus(ServiceStatus.SANDBY);
		}
		return s;
	}
	
	private int getRealCurrentCount(ServiceKeeper serviceKeeper){
		int c = 0;
		List<ServiceProcess> processes = serviceKeeper.getNodes();
		for(ServiceProcess process:processes){
			String sPath = Consts.ZK_ROOT+"/"+process.getNode()+"/"+process.getName().split("_")[0];
			if(zkHelper.isExtisPath(sPath)){
				c++;
			}
		}
		return c;
	}

	private List<ServiceProcess> getServiceKeeperNodes(ServiceKeeper serviceKeeper) {
		List<ServiceProcess> list = serviceKeeper.getNodes();
		if(list!=null&&list.size()>0){
		}else{
			list = new ArrayList<ServiceProcess>();
			serviceKeeper.setNodes(list);
		}
		return list;
	}

	private void unLock(String serviceName) {
		try {
			zkHelper.deletePath("/pilot/lock_t/"+serviceName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean tryGetLock(String serviceName) {
		if(!zkHelper.isExtisPath("/pilot/lock_t")){
			zkHelper.createPath("/pilot/lock_t");
		}
		boolean unlock = false;
		int lock_c = 30;
		while(lock_c--<0){
			try {
				unlock = zkHelper.createPath("/pilot/lock_t/"+serviceName, true);
			} catch (Exception e1) {
				LOGGER.error("",e1);
			}
			if(unlock){
				break;
			}
		}
		LOGGER.info("unlock " +unlock);
		return unlock;
	}

}
