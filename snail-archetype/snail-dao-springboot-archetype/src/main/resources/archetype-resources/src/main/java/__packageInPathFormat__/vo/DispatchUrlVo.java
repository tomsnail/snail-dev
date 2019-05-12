#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.vo;

import cn.tomsnail.core.util.validator.annotation.BeanValidator;
import cn.tomsnail.core.util.validator.annotation.FieldValidator;
import cn.tomsnail.core.util.validator.annotation.ValidType;
import cn.tomsnail.snail.core.obj.base.BaseVo;
import ${package}.api.DispatchUrlMo;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * DispatchUrl model
 * @author Yangsong
 * @version 0.0.1-SNAPSHOT
 */
@BeanValidator(isAllValidator=true)
public class DispatchUrlVo  extends BaseVo<DispatchUrlMo> {
	
	@FieldValidator(onlyToBean=true,mapperName="",validTypes={ValidType.UPDATE,ValidType.DELETE})
	private String id;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String strategyId;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String urlType;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String urlContext;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String urlUserName;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String urlPassword;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String urlAdditional;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private Timestamp createDate;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String createBy;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private Timestamp updateDate;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String updateBy;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String delFlag;		// 
	@FieldValidator(onlyToBean=true,mapperName="")
	private String remarks;		// 
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}
	
	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	
	public String getUrlContext() {
		return urlContext;
	}

	public void setUrlContext(String urlContext) {
		this.urlContext = urlContext;
	}
	
	public String getUrlUserName() {
		return urlUserName;
	}

	public void setUrlUserName(String urlUserName) {
		this.urlUserName = urlUserName;
	}
	
	public String getUrlPassword() {
		return urlPassword;
	}

	public void setUrlPassword(String urlPassword) {
		this.urlPassword = urlPassword;
	}
	
	public String getUrlAdditional() {
		return urlAdditional;
	}

	public void setUrlAdditional(String urlAdditional) {
		this.urlAdditional = urlAdditional;
	}
	
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}