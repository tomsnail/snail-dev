package com.thinkgem.jeesite.modules.cms.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CreateHtmlUtil {

	  public static String createHTML(HttpServletRequest context,Map<String,Object> data,String templatePath,String targetHtmlPath){  
	        Configuration freemarkerCfg = new Configuration();  
	        //设置加载模板文件的路径  
	        freemarkerCfg.setServletContextForTemplateLoading(context.getSession().getServletContext(), "/templates/cmsfiles");  
	        freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");  
	          
	        try {  
	            Template template = freemarkerCfg.getTemplate(templatePath,"UTF-8");  
	            template.setEncoding("UTF-8");  
	              
	            //创建生成静态文件的路径  
	            String htmlPath = context.getRealPath("/cmsfiles")+targetHtmlPath;  
	            File htmlFile = new File(htmlPath);   
	            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));   
	            //处理模版并开始输出静态页面   
	            template.process(data, out);   
	            out.flush();   
	            out.close();   
	            return htmlPath;
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (TemplateException e) {  
	            e.printStackTrace();  
	        }  
	        return null;
	    }  
	
}
