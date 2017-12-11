/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zkjd.ehua.system.conf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.zkjd.ehua.system.conf.entity.VisitAddress;
import com.zkjd.ehua.system.conf.dao.VisitAddressDao;

/**
 * 访问地址Service
 * @author yangsong
 * @version 2017-03-03
 */
@Service
@Transactional(readOnly = true)
public class VisitAddressService extends CrudService<VisitAddressDao, VisitAddress> {

	public VisitAddress get(String id) {
		return super.get(id);
	}
	
	public List<VisitAddress> findList(VisitAddress visitAddress) {
		return super.findList(visitAddress);
	}
	
	public Page<VisitAddress> findPage(Page<VisitAddress> page, VisitAddress visitAddress) {
		return super.findPage(page, visitAddress);
	}
	
	@Transactional(readOnly = false)
	public void save(VisitAddress visitAddress) {
		String dv = visitAddress.getIsDefVer();
		if(StringUtils.isNotBlank(dv)&&dv.equals("1")){
			VisitAddress va = new VisitAddress();
			va.setVisitAddr(visitAddress.getVisitAddr());
			va.setGrpgName(visitAddress.getGrpgName());
			List<VisitAddress> addresses = this.dao.findList(va);
			if(addresses!=null&&addresses.size()>0){
				for(VisitAddress address:addresses){
					address.setIsDefVer("0");
					this.dao.update(address);
				}
			}
		}
		super.save(visitAddress);
	}
	
	@Transactional(readOnly = false)
	public void delete(VisitAddress visitAddress) {
		super.delete(visitAddress);
	}
	
}