/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.tomsnail.objsql.gen;



/**
 * 生成方案Entity
 * @author ThinkGem
 * @version 2013-10-15
 */
public class GenScheme  {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name; 	// 名称
	private String category;		// 分类
	private String packageName;		// 生成包路径
	private String moduleName;		// 生成模块名
	private String subModuleName;		// 生成子模块名
	private String functionName;		// 生成功能名
	private String functionNameSimple;		// 生成功能名（简写）
	private String functionAuthor;		// 生成功能作者
	private String groupId;
	private String artifactId;
	private String partifactId;
	private String pomversion;
	private String jdbcUrl;
	private String jdbcUsername;
	private String jdbcPassword;
	private GenTable genTable;		// 业务表名
	private GenClass genClass;
	
	private String flag; 	// 0：保存方案； 1：保存方案并生成代码
	
	private Boolean replaceFile;	// 是否替换现有文件    0：不替换；1：替换文件

	public GenScheme() {
		super();
	}

	public GenScheme(String id){
		this.id = id;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public GenTable getGenTable() {
		return genTable;
	}

	public void setGenTable(GenTable genTable) {
		this.genTable = genTable;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Boolean getReplaceFile() {
		return replaceFile;
	}

	public void setReplaceFile(Boolean replaceFile) {
		this.replaceFile = replaceFile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getPartifactId() {
		return partifactId;
	}

	public void setPartifactId(String partifactId) {
		this.partifactId = partifactId;
	}

	public String getPomversion() {
		return pomversion;
	}

	public void setPomversion(String pomversion) {
		this.pomversion = pomversion;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	public GenClass getGenClass() {
		return genClass;
	}

	public void setGenClass(GenClass genClass) {
		this.genClass = genClass;
	}
	
	
	
}


