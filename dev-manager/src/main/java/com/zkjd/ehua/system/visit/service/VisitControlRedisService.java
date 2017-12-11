package com.zkjd.ehua.system.visit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.zkjd.ehua.system.visit.entity.VisitControl;

@Service
public class VisitControlRedisService {
	
	private static final String VC = "VC:";
	
	@Autowired
	private VisitControlService visitControlService;
	
	public String refresh(){
		
		VisitControl visitControl = new VisitControl();
		List<VisitControl> visitControls = visitControlService.findList(visitControl);
		if(visitControls==null||visitControls.size()==0){
			return "no data";
		}
		for(VisitControl control:visitControls){
			if(control.getIsLimit()==null||control.getIsLimit().equals("0")){
				JedisUtils.del(VC+control.getVisitIp());
				continue;
			}
			if(control.getLimitTime()==null){
				JedisUtils.set(VC+control.getVisitIp(), "1", 0);
			}else{
				Date date = control.getLimitTime();
				long t1 = date.getTime();
				long t2 = System.currentTimeMillis();
				long t = (t1-t2)/1000;
				if(t>0){
					System.out.println((int)t);
					JedisUtils.set(VC+control.getVisitIp(), "1", (int)t);
				}
			}
		}
		return "ok";
	}
	
	public String clear(){
		VisitControl visitControl = new VisitControl();
		List<VisitControl> visitControls = visitControlService.findList(visitControl);
		if(visitControls==null||visitControls.size()==0){
			return "no data";
		}
		for(VisitControl control:visitControls){
			JedisUtils.del(VC+control.getVisitIp());
		}
		return "ok";
	}

}
