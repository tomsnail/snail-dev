/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.tomsnail.snail.core.gen.template;


import cn.tomsnail.snail.core.gen.GenConfigModel;
import cn.tomsnail.snail.core.gen.db.GenTable;
import cn.tomsnail.snail.core.util.JsonUtil;
import cn.tomsnail.snail.core.util.bean.BeanUtil;
import cn.tomsnail.snail.core.util.string.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class GenScheme  {
	

	private String name; 	// 名称
	private String packageName;		// 生成包路径
	private String moduleName;		// 生成模块名
	private String subModuleName;		// 生成子模块名
	private String functionName;		// 生成功能名
	private String functionNameSimple;		// 生成功能名（简写）
	private String functionAuthor;		// 生成功能作者
	private String parentGroupId;
	private String artifactId;
	private String parentArtifactId;
	private String parentVersion;
	private String version;



	private GenTable genTable;
	private String dbType;
	

	private Boolean replaceFile;	// 是否替换现有文件    0：不替换；1：替换文件

	public static GenScheme genScheme(GenTable genTable){
		GenScheme genScheme = new GenScheme();
		genScheme.genTable = genTable;
		genScheme.packageName = GenConfigModel.getInstance().gen_class_packageName;
		genScheme.moduleName = GenConfigModel.getInstance().gen_class_moduleName;
		genScheme.subModuleName = "";
		genScheme.functionName = "";
		genScheme.functionNameSimple = "";
		genScheme.functionAuthor = GenConfigModel.getInstance().gen_class_author;
		genScheme.parentGroupId = GenConfigModel.getInstance().gen_pom_parent_groupId;
		genScheme.artifactId = GenConfigModel.getInstance().gen_pom_artifactId;
		genScheme.parentArtifactId = GenConfigModel.getInstance().gen_pom_parent_artifactId;
		genScheme.parentVersion = GenConfigModel.getInstance().gen_pom_parent_version;
		genScheme.version = GenConfigModel.getInstance().gen_pom_version;
		genScheme.dbType = GenConfigModel.getInstance().gen_dao_db;
		genScheme.replaceFile = GenConfigModel.getInstance().gen_replace;
		return genScheme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionNameSimple() {
		return functionNameSimple;
	}

	public void setFunctionNameSimple(String functionNameSimple) {
		this.functionNameSimple = functionNameSimple;
	}

	public String getFunctionAuthor() {
		return functionAuthor;
	}

	public void setFunctionAuthor(String functionAuthor) {
		this.functionAuthor = functionAuthor;
	}

	public String getParentGroupId() {
		return parentGroupId;
	}

	public void setGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getParentArtifactId() {
		return parentArtifactId;
	}

	public void setParentArtifactId(String parentArtifactId) {
		this.parentArtifactId = parentArtifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public GenTable getGenTable() {
		return genTable;
	}

	public void setGenTable(GenTable genTable) {
		this.genTable = genTable;
	}


	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public Boolean getReplaceFile() {
		return replaceFile;
	}

	public void setReplaceFile(Boolean replaceFile) {
		this.replaceFile = replaceFile;
	}

	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	public String getParentVersion() {
		return parentVersion;
	}

	public void setParentVersion(String parentVersion) {
		this.parentVersion = parentVersion;
	}

	public  Map<String, Object> dataModel(){
		Map<String,Object> model = new HashMap<>();


		model.put("genTable",this.genTable);
		model.put("packageName",this.packageName);
		model.put("moduleName",this.moduleName);
		model.put("subModuleName",this.subModuleName);
		model.put("functionName",this.functionName);
		model.put("functionNameSimple",this.functionNameSimple);
		model.put("functionAuthor",this.functionAuthor);
		model.put("parentGroupId",this.parentGroupId);
		model.put("artifactId",this.artifactId);
		model.put("parentArtifactId",this.parentArtifactId);
		model.put("parentVersion",this.parentVersion);
		model.put("version",this.version);
		model.put("dbType",this.dbType);
		model.put("replaceFile",this.replaceFile);

		model.put("packageName", StringUtils.lowerCase(this.getPackageName()));
		model.put("lastPackageName", StringUtils.substringAfterLast((String)model.get("packageName"),"."));
		model.put("moduleName", StringUtils.lowerCase(this.getModuleName()));
		model.put("functionVersion", this.getVersion());
		model.put("subModuleName", StringUtils.lowerCase(this.getSubModuleName()));
		model.put("functionAuthor", StringUtils.capitalize(this.getFunctionAuthor()));
		model.put("urlPrefix", model.get("moduleName")+(StringUtils.isNotBlank(this.getSubModuleName())
				?"/"+StringUtils.lowerCase(this.getSubModuleName()):"")+"/"+model.get("className"));
		model.put("viewPrefix", //StringUtils.substringAfterLast(model.get("packageName"),".")+"/"+
				model.get("urlPrefix"));
		model.put("permissionPrefix", model.get("moduleName")+(StringUtils.isNotBlank(this.getSubModuleName())
				?":"+StringUtils.lowerCase(this.getSubModuleName()):"")+":"+model.get("className"));
		try {
			if(this.getGenTable()!=null){
				model.put("tableName", StringUtils.capitalize(this.getGenTable().getTableName()));
				model.put("className", StringUtils.uncapitalize(this.getGenTable().getClassName()));
				model.put("ClassName", StringUtils.capitalize(this.getGenTable().getClassName()));
				model.put("functionName", StringUtils.capitalize(this.getGenTable().getClassName()));
				model.put("table", this.getGenTable());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("packagePath", StringUtils.lowerCase(this.getPackageName()).replaceAll("\\.","/"));

		return model;
	}
}


