package com.zkjd.ehua.system.conf.service;

import com.zkjd.ehua.system.conf.entity.VisitAddress;

/**
 * 访问地址的redis操作
 * @author yangsong
 *
 */
public interface VisitAddressRedisService {

	/**
	 * 刷新整个缓存
	 */
	public boolean refresh();
	
	
	/**
	 * 用户操作，停用，启用
	 * @param visitAddress
	 * @return
	 */
	public boolean userOper(VisitAddress visitAddress);
}
