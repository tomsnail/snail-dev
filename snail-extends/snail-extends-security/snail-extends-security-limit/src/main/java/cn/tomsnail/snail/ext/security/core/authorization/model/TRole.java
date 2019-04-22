package cn.tomsnail.snail.ext.security.core.authorization.model;

import java.util.List;

/**
 *        角色
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 下午5:17:36
 * @see 
 */
public class TRole {

	private String uuid;
	
	private String roleName;
	
	private List<TResource> resources;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<TResource> getResources() {
		return resources;
	}

	public void setResources(List<TResource> resources) {
		this.resources = resources;
	}
	
	public boolean isContains(TResource resource){
		if(resources==null||resources.size()<1){
			return true;
		}
		for(TResource _resource:resources){
			if(_resource.isEquls(resource)){
				return true;
			}
		}
		return false;
	} 
	
}
