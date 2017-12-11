package cn.tomsnail.zkclient.address;

import org.apache.commons.lang.StringUtils;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月29日 下午6:52:41
 * @see 
 */
public class EnvAddress implements IAddress{

	@Override
	public String getAddress() {
		
		String address = System.getenv("ZooKeeperHosts");
		if(StringUtils.isBlank(address)){
			return null;
		}
		return address;
	}

}
