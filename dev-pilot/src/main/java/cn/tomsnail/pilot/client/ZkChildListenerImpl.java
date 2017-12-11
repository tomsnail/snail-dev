package cn.tomsnail.pilot.client;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;

public class ZkChildListenerImpl implements IZkChildListener{
	
	
	boolean status = true;
	
	public ZkChildListenerImpl(){
	}

	@Override
	public void handleChildChange(String parentPath, List<String> currentChilds)
			throws Exception {
		if(currentChilds==null){
			status = false;
		}
	}

	public boolean getStatus(){
		return status;
	}
	
}
