/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.tomsnail.snail.core.gen.template;

import cn.tomsnail.snail.core.gen.GenConfigModel;
import cn.tomsnail.snail.core.gen.utils.JaxbMapper;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 生成方案Entity
 * @author ThinkGem
 * @version 2013-10-15
 */
@XmlRootElement(name="config")
public class GenConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<GenCategory> categoryList;	// 代码模板分类


	public GenConfig() {
		super();
	}

	@XmlElementWrapper(name = "module")
	@XmlElement(name = "module")
	public List<GenCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<GenCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public static List<GenTemplate> getTemplateList(){
		GenConfig config = getConfig();
		List<GenTemplate> templateList = Lists.newArrayList();
		if (config !=null && config.getCategoryList() != null){
			String rootPath = GenConfigModel.getInstance().gen_xml_template_path;
			for (GenCategory e : config.getCategoryList()){
				System.out.println(e.getName());
				if(ConfigHelp.getInstance("genConfig").getLocalConfig("gen."+e.getName(),false)){
					List<String> list = e.getTemplate();
					if (list != null){
						for (String s : list){
							GenTemplate template = fileToObject(rootPath+s, GenTemplate.class);
							if (template != null){
								templateList.add(template);
							}
						}
					}
				}

			}
		}
		return templateList;
	}


	/**
	 * XML文件转换为对象
	 * @param filepath
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fileToObject(String filepath, Class<?> clazz){
		try {
			String pathName =filepath;
//			logger.debug("File to object: {}", pathName);
			Resource resource = new ClassPathResource(pathName);
			InputStream is = resource.getInputStream();
			if(is==null){
				return null;
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			while (true) {
				String line = br.readLine();
				if (line == null){
					break;
				}
				sb.append(line).append("\r\n");
			}
			is.close();
			br.close();
			return (T) JaxbMapper.fromXml(sb.toString(), clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取代码生成配置对象
	 * @return
	 */
	public static GenConfig getConfig(){
		return fileToObject(GenConfigModel.getInstance().gen_xml_filepath, GenConfig.class);
	}

	
}