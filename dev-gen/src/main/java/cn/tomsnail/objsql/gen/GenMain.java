package cn.tomsnail.objsql.gen;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.util.configfile.ConfigHelp;

public class GenMain {

	public static void main(String[] args) {
		String tableStr = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.tables", "");
		String objectStr = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.objects", "");
		String[] tables = tableStr.split(",");
		String[] objects = objectStr.split(",");
		
		
		String scheme = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.name", "");
		
		if(StringUtils.isBlank(scheme)){
			System.out.println("gen.name is null , exit !");
			return;
		}
		if(scheme.equals("all")||scheme.equals("dao")||scheme.equals("daov")||scheme.equals("odaov")||scheme.equals("oservice")||scheme.equals("owebapi")){
			if(tables!=null&&tables.length>0){
				GenScheme genScheme = getScheme(scheme);
				GenConfig config = GenUtils.getConfig();
				List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
				for(int i=0;i<tables.length;i++){
					if(!StringUtils.isBlank(tables[i])&&!StringUtils.isBlank(objects[i])){
						GenTable genTable = getTable(tables[i]);
						if(genTable==null) continue;
						genTable.setClassName(objects[i]);
						genScheme.setGenTable(genTable);
						Map<String, Object> model = GenUtils.getDataModel(genScheme);
						for (GenTemplate tpl : templateList){
							System.out.println(tpl.getName());
							GenUtils.generateToFile(tpl, model, genScheme.getReplaceFile());
						}
					}
				}
			}
		}else if(scheme.equals("webapi")||scheme.equals("service")||scheme.equals("fall")){
			GenScheme genScheme = getScheme(scheme);
			GenConfig config = GenUtils.getConfig();
			List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
			GenTable genTable = null;
			genScheme.setGenTable(genTable);
			Map<String, Object> model = GenUtils.getDataModel(genScheme);
			for (GenTemplate tpl : templateList){
				System.out.println(tpl.getName());
				GenUtils.generateToFile(tpl, model, genScheme.getReplaceFile());
			}
		}else{
			System.out.println("scheme is not define : +"+scheme+"!");
		}
		
		
		
	
		System.out.println("main stop ok!");
	}
	
	public static final GenScheme getScheme(String category){
		GenScheme genScheme = new GenScheme();
		genScheme.setCategory(category);
		genScheme.setFunctionAuthor(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.functionAuthor", ""));
		genScheme.setFlag("1");
		genScheme.setFunctionName("");
		genScheme.setFunctionNameSimple(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.functionAuthor", ""));
		genScheme.setId("1");
		genScheme.setModuleName(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.moduleName", ""));
		genScheme.setName(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.name", ""));
		genScheme.setPackageName(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.packageName", ""));
		genScheme.setReplaceFile(true);
		genScheme.setSubModuleName("");
		genScheme.setArtifactId(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.artifactId", ""));
		genScheme.setGroupId(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.groupId", ""));
		genScheme.setPartifactId(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.partifactId", ""));
		genScheme.setPomversion(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pomversion", ""));
		genScheme.setJdbcUsername(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc_username", ""));
		genScheme.setJdbcUrl(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc_url", ""));
		genScheme.setJdbcPassword(ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc_password", ""));
		return genScheme;
	}
	
	public static final GenTable getTable(String table){
		
		try {
			return DBUtil.getTableInfo(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
