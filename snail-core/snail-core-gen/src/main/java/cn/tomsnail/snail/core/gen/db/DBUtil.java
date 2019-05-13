package cn.tomsnail.snail.core.gen.db;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import cn.tomsnail.snail.core.gen.GenConfigModel;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;


public class DBUtil {

	private static Pattern linePattern = Pattern.compile("_(\\w)"); 
	

	 
	
	public static GenTable getTableInfo(String table,String object) throws Exception {

		GenTable genTable = new GenTable();
		List<GenTableColumn> columnList = new ArrayList<GenTableColumn>();
		genTable.setColumnList(columnList);
		genTable.setTableName(table);
		genTable.setClassName(object);
		
		String driver = GenConfigModel.getInstance().gen_jdbc_driver;
		String url = GenConfigModel.getInstance().gen_jdbc_url;
		String user = GenConfigModel.getInstance().gen_jdbc_username;
		String password = GenConfigModel.getInstance().gen_jdbc_password;
		String jdbctype = GenConfigModel.getInstance().gen_dao_jdbctype;
		String feildtype = GenConfigModel.getInstance().gen_dao_feild_type;//UPPER、LOWER、Hump
		String dbType = GenConfigModel.getInstance().gen_dao_db;//mysql、oracle
		if(StringUtils.isNotBlank(dbType)) {
			genTable.setDbType(dbType);
		}else {
			if("UPPER".equals(feildtype)) {
				genTable.setDbType("oracle");
			}else {
				genTable.setDbType("mysql");
			}
		}
		

		if(StringUtils.isBlank(driver)||StringUtils.isBlank(url)){
			return null;
		}
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		if (!conn.isClosed())
			System.out.println("Succeeded connecting to the Database!");
		else
			System.err.println("connect filed");
		Statement statement = conn.createStatement();
		String sql_table="select * from "+table+" limit 10,1";
		if(driver.indexOf("oracle")>-1){	
			sql_table="select * from "+table+" where rownum < 10";
		}
		ResultSet resultSet = statement.executeQuery(sql_table);
		// 获取列名
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 0; i < metaData.getColumnCount(); i++) {
			GenTableColumn column = new GenTableColumn();
			column.setGenTable(genTable);
			String columnName = metaData.getColumnName(i + 1);
			int type = metaData.getColumnType(i + 1);
			if(columnName.equalsIgnoreCase("id")){
				column.setIsPk("1");
			}else{
				if(metaData.isAutoIncrement(i+1)){
					column.setIsPk("1");
				}
			}
			
			String jdbcType = "";
			String javaType = "";
			if (Types.INTEGER == type|| type == Types.SMALLINT || type == Types.TINYINT) {
				jdbcType = "varchar";
				javaType = "Integer";
			}else if (Types.BIGINT == type) {
				jdbcType = "varchar";
				javaType = "Long";
			}else if (Types.NUMERIC == type) {
				jdbcType = "varchar";
				javaType = "BigDecimal";
			}else if (Types.DECIMAL == type) {
				jdbcType = "varchar";
				javaType = "BigDecimal";
			}else if (Types.FLOAT == type) {
				jdbcType = "varchar";
				javaType = "Double";
			}else if (Types.DOUBLE == type) {
				jdbcType = "varchar";
				javaType = "Double";
			} else if(type == Types.DATE || type == Types.TIME ||type == Types.TIMESTAMP) {
				jdbcType = "varchar";
				javaType = "Timestamp"; 
			}else if(type == Types.CLOB || type == Types.BLOB) {
				jdbcType = "varchar";
				javaType = "byte[]"; 
			}else{
				jdbcType = "varchar";
				javaType = "String";
			}
			column.setComments("");
			if(jdbctype.equals("mybatis")){
				if("UPPER".equalsIgnoreCase(feildtype)){
					column.setJavaField4Dto(columnName.toUpperCase());
				}else if("LOWER".equalsIgnoreCase(feildtype)){
					column.setJavaField4Dto(columnName.toLowerCase());
				}else{
					column.setJavaField4Dto(StringUtils.lineToHump(linePattern,columnName));
				}
				column.setJavaField(StringUtils.lineToHump(linePattern,columnName));
			}else{
				column.setJavaField(columnName);
			}
			column.setQueryType("=");
			column.setJavaType(javaType);
			column.setIsInsert("1");
			column.setIsQuery("1");
			column.setIsEdit("1");
			column.setJdbcType(jdbcType);
			column.setName(columnName);
			column.setId((i+1)+"");
			columnList.add(column);
		}
		statement.close();
		conn.close();
		return genTable;
	}

	public static String convertDatabaseCharsetType(String in, String type) {
		String dbUser;
		if (in != null) {
			if (type.equals("oracle")) {
				dbUser = in.toUpperCase();
			} else if (type.equals("postgresql")) {
				dbUser = "public";
			} else if (type.equals("mysql")) {
				dbUser = null;
			} else if (type.equals("mssqlserver")) {
				dbUser = null;
			} else if (type.equals("db2")) {
				dbUser = in.toUpperCase();
			} else {
				dbUser = in;
			}
		} else {
			dbUser = "public";
		}
		return dbUser;
	}

	public static List<String> getTables() {

		String driver = GenConfigModel.getInstance().gen_jdbc_driver;
		String url = GenConfigModel.getInstance().gen_jdbc_url;
		String user = GenConfigModel.getInstance().gen_jdbc_username;
		String password = GenConfigModel.getInstance().gen_jdbc_password;
		
		List<String> tables = new ArrayList<String>();
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			else {
				System.err.println("connect filed");
				return tables;
			}
				
			
			DatabaseMetaData dbMetData = conn.getMetaData();
			ResultSet rs = dbMetData.getTables(null,
					convertDatabaseCharsetType("root", "mysql"), null,
					new String[] { "TABLE", "VIEW" });
			
			while (rs.next()) {
				if (rs.getString(4) != null
						&& (rs.getString(4).equalsIgnoreCase("TABLE") || rs
								.getString(4).equalsIgnoreCase("VIEW"))) {
					String tableName = rs.getString(3).toLowerCase();
					System.out.println(tableName);
					tables.add(tableName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tables;
	}
	public static void main(String[] args) {
		try {
			//getTableInfo("wx_user");
			String[] tables = "*".split(",");
			String[] objects = "".split(",");
			List<String> tableList = new ArrayList<String>();
			tableList.add("sys_file_all");
			
			if(tables.length==1&&tables[0].equals("*")) {
				tableList.toArray(tables);
				for(int i=0;i<tables.length;i++) {
					objects[i] = StringUtils.toUpperCaseFirstOne(StringUtils.lineToHump(linePattern,tables[i]));
				}
			}
			System.out.println(tables[0]);
			System.out.println(objects[0]);
			System.out.println(StringUtils.toUpperCaseFirstOne(StringUtils.lineToHump(linePattern,"sys_file_all")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
