package cn.tomsnail.unit.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;






import org.apache.commons.lang3.StringUtils;

import cn.tomsnail.objsql.gen.GenClass;
import cn.tomsnail.objsql.gen.GenConfig;
import cn.tomsnail.objsql.gen.GenMethod;
import cn.tomsnail.objsql.gen.GenScheme;
import cn.tomsnail.objsql.gen.GenTemplate;
import cn.tomsnail.objsql.gen.GenUtils;
import cn.tomsnail.reflect.ClassUtil;
import cn.tomsnail.unit.test.annotation.AutoTester;
import cn.tomsnail.util.configfile.ConfigHelp;

public class UnitMain {

	public static void main(String[] args) throws Exception{  
		String scheme = "unitest";
		String backageName = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.unittest.backageName", "");
        List<Class<?>> classes = ClassUtil.getClasses(backageName);
        for (Class clas :classes) {  
            if(clas.isAnnotationPresent(AutoTester.class)){
            	GenScheme genScheme = getScheme(scheme,clas.getPackage().getName());
        		GenConfig config = GenUtils.getConfig();
        		List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
        		GenClass genClass = new GenClass();
        		genClass.setClassName(clas.getSimpleName());
        		List<GenMethod> genMethods = new ArrayList<GenMethod>();
        		genClass.setColumnList(genMethods);
        		genScheme.setGenClass(genClass);
        		Method[] ms = clas.getMethods();
        		for(Method m:ms){
        			GenMethod genMethod = new GenMethod();
        			genMethod.setName(m.getName());
        			genMethod.setUname(StringUtils.capitalize(m.getName()));
        			genMethod.setArgLength(m.getParameterCount());
        			if(m.getParameterCount()>0){
        				Class[] ps = m.getParameterTypes();
        				List<String> imports = new ArrayList<String>();
        				StringBuffer params = new StringBuffer();
        				genClass.setImportList(imports);
        				for(Class p:ps){
        					String pType = p.getName();
        					if(pType.equals("java.lang.String")){
        						//imports.add("java.lang.String");
        						params.append("\"\",");
        					}else if(pType.equals("java.lang.Integer")||pType.equals("int")){
        						//if(pType.equals("java.lang.Integer")) imports.add("java.lang.Integer");
        						params.append("0,");
        					}else if(pType.equals("java.lang.Float")||pType.equals("float")){
        						//if(pType.equals("java.lang.Float"))imports.add("java.lang.Float");
        						params.append("0F,");
        					}else if(pType.equals("java.lang.Double")||pType.equals("double")){
        						//if(pType.equals("java.lang.Double"))imports.add("java.lang.Double");
        						params.append("0D,");
        					}else if(pType.equals("java.lang.Boolean")||pType.equals("boolean")){
        						//if(pType.equals("java.lang.Boolean")) imports.add("java.lang.Boolean");
        						params.append("false,");
        					}else if(pType.equals("java.util.Map")||pType.equals("java.util.HashMap")){
        						imports.add(pType);
        						imports.add("java.util.HashMap");
        						params.append(" new HashMap(),");
        					}else if(pType.equals("java.util.List")||pType.equals("java.util.ArrayList")){
        						imports.add(pType);
        						imports.add("java.util.ArrayList");
        						params.append(" new ArrayList(),");
        					}else{
        						imports.add(p.getName());
        						params.append(" new "+p.getSimpleName()+"(),");
        					}
            				genMethod.setGenParamters(params.toString().substring(0, params.toString().length()-1));
        				}
        			}
        			genMethods.add(genMethod);
        		}
        		Map<String, Object> model = GenUtils.getDataModel(genScheme);
        		for (GenTemplate tpl : templateList){
        			System.out.println(tpl.getName());
        			GenUtils.generateToFile(tpl, model, genScheme.getReplaceFile());
        		}
            }
        }  
    }  

	public static final GenScheme getScheme(String category,String packageName){
		GenScheme genScheme = new GenScheme();
		genScheme.setCategory(category);
		genScheme.setFunctionAuthor("tomsnail.cn");
		genScheme.setFlag("1");
		genScheme.setFunctionName("");
		genScheme.setFunctionNameSimple("tomsnail.cn");
		genScheme.setId("1");
		genScheme.setModuleName("");
		genScheme.setName("");
		genScheme.setPackageName(packageName);
		genScheme.setReplaceFile(true);
		genScheme.setSubModuleName("");
		genScheme.setArtifactId("");
		genScheme.setGroupId("");
		genScheme.setPartifactId("");
		genScheme.setPomversion("");
		genScheme.setJdbcUsername("");
		genScheme.setJdbcUrl("");
		genScheme.setJdbcPassword("");
		return genScheme;
	}
	
}
