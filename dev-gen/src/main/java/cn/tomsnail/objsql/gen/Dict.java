/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.tomsnail.objsql.gen;

import javax.xml.bind.annotation.XmlAttribute;



/**
 * 字典Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Dict  {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String value;	// 数据值
	private String label;	// 标签名
	private String type;	// 类型
	private String description;// 描述
	private Integer sort;	// 排序
	private String parentId;//父Id

	public Dict() {
		super();
	}
	
	public Dict(String id){
		this.id=id;
	}
	
	public Dict(String value, String label){
		this.value = value;
		this.label = label;
	}
	
	@XmlAttribute
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@XmlAttribute
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Override
	public String toString() {
		return label;
	}
}