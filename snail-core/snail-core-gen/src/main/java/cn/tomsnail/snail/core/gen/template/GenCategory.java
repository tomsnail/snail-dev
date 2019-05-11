/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.tomsnail.snail.core.gen.template;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 生成方案Entity
 * @author ThinkGem
 * @version 2013-10-15
 */
@XmlRootElement(name="module")
public class GenCategory implements Serializable {
	
	private List<String> template;			// 主表模板
	private String name;



	public GenCategory() {
		super();
	}

	@XmlElement(name = "template")
	public List<String> getTemplate() {
		return template;
	}

	public void setTemplate(List<String> template) {
		this.template = template;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}


