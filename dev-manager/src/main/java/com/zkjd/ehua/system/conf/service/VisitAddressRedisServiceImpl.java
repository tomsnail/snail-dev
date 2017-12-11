package com.zkjd.ehua.system.conf.service;

import java.util.HashMap;
import java.util.List;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.zkjd.ehua.system.conf.dao.VisitAddressDao;
import com.zkjd.ehua.system.conf.entity.VisitAddress;

@Service
public class VisitAddressRedisServiceImpl implements VisitAddressRedisService{

	@Autowired
	private VisitAddressDao visitAddressDao;
	
	
	
	@Override
	public boolean refresh() {
		try {
			VisitAddress visitAddress = new VisitAddress();
			visitAddress.setIsRelease("1");
			visitAddress.setIsNewRecord(false);
			visitAddress.setIsInner("0");
			visitAddress.setDelFlag("0");
			List<VisitAddress> visitAddresses = visitAddressDao.findList(visitAddress);
			if(visitAddresses!=null&&visitAddresses.size()>0){
				Map<String,String> rmap = new HashMap<String,String>();
				for(VisitAddress address:visitAddresses){
					
					if(address.getIsRelease()!=null&&address.getIsRelease().equals("1")){
						String reverseAgncAddr = "";
						if(address.getAddressType().equals("202101")){
							reverseAgncAddr = address.getRealAddress();
						}else if(address.getAddressType().equals("202102")){
							reverseAgncAddr = address.getTestAddress();
						}else if(address.getAddressType().equals("202103")){
							reverseAgncAddr = address.getComAddress();
						}else{
							reverseAgncAddr = address.getComAddress();
						}	
						boolean isAddVer = false;
						if(StringUtils.isNoneBlank(address.getIsAddVer())&&address.getIsAddVer().equals("1")){
							isAddVer = true;
						}
 
						if(reverseAgncAddr.contains(";")){
							StringBuffer buffer = new StringBuffer();
							String[] raArray = reverseAgncAddr.split(";");
							for(String ra:raArray){
								if(isAddVer){
									buffer.append(ra+ address.getVerInfo().replace(".", "")+"/"+address.getVisitAddr()).append(";");
								}else{
									buffer.append(ra+ address.getVisitAddr()).append(";");
								}
							}
							reverseAgncAddr = buffer.toString();
						}else{
							if(isAddVer){
								reverseAgncAddr = reverseAgncAddr+ address.getVerInfo().replace(".", "")+"/"+address.getVisitAddr();
							}else{
								reverseAgncAddr = reverseAgncAddr+address.getVisitAddr();

							}
						}
						address.setReverseAgncAddr(reverseAgncAddr);
						String info = JsonMapper.toJsonString(address);
						rmap.put(address.getVerInfo().replace(".", "")+"/"+address.getVisitAddr(), info);
						if(StringUtils.isNotBlank(address.getIsDefVer())&&"1".equals(address.getIsDefVer())){
							rmap.put(address.getVisitAddr(), info);
						}
					}
				}
				System.out.println(JsonMapper.toJsonString(rmap));
				JedisUtils.setMap("urlmap", rmap, 0);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public boolean userOper(VisitAddress address) {
		try {
			if(address.getIsRelease().equals("1")){
				Map<String,String> map = new HashMap<String,String>();
				
				if(address.getIsRelease()!=null&&address.getIsRelease().equals("1")){
					String reverseAgncAddr = "";
					if(address.getAddressType().equals("202101")){
						reverseAgncAddr = address.getRealAddress();
					}else if(address.getAddressType().equals("202102")){
						reverseAgncAddr = address.getTestAddress();
					}else if(address.getAddressType().equals("202103")){
						reverseAgncAddr = address.getComAddress();
					}else{
						reverseAgncAddr = address.getComAddress();
					}
					boolean isAddVer = false;
					if(StringUtils.isNoneBlank(address.getIsAddVer())&&address.getIsAddVer().equals("1")){
						isAddVer = true;
					}

					if(reverseAgncAddr.contains(";")){
						StringBuffer buffer = new StringBuffer();
						String[] raArray = reverseAgncAddr.split(";");
						for(String ra:raArray){
							if(isAddVer){
								buffer.append(ra+ address.getVerInfo().replace(".", "")+"/"+address.getVisitAddr()).append(";");
							}else{
								buffer.append(ra+ address.getVisitAddr()).append(";");
							}
						}
						reverseAgncAddr = buffer.toString();
					}else{
						if(isAddVer){
							reverseAgncAddr = reverseAgncAddr+ address.getVerInfo().replace(".", "")+"/"+address.getVisitAddr();
						}else{
							reverseAgncAddr = reverseAgncAddr+address.getVisitAddr();

						}
					}
					address.setReverseAgncAddr(reverseAgncAddr);
					String info = JsonMapper.toJsonString(address);
					map.put(address.getVerInfo().replace(".", "")+"/"+address.getVisitAddr(), info);
					if(StringUtils.isNotBlank(address.getIsDefVer())&&"1".equals(address.getIsDefVer())){
						map.put(address.getVisitAddr(), info);
					}
				}
				JedisUtils.mapPut("urlmap", map);
			}else{
				JedisUtils.mapRemove("urlmap", address.getVerInfo().replace(".", "")+"/"+address.getVisitAddr());
				if(StringUtils.isNotBlank(address.getIsDefVer())&&"1".equals(address.getIsDefVer())){
					JedisUtils.mapRemove("urlmap", address.getVisitAddr());
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
