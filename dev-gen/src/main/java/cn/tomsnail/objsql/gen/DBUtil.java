package cn.tomsnail.objsql.gen;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.util.configfile.ConfigHelp;




public class DBUtil {

	private static Pattern linePattern = Pattern.compile("_(\\w)"); 
	
	 public static String lineToHump(String str){  
         str = str.toLowerCase();  
         Matcher matcher = linePattern.matcher(str);  
         StringBuffer sb = new StringBuffer();  
         while(matcher.find()){  
             matcher.appendReplacement(sb, matcher.group(1).toUpperCase());  
         }  
         matcher.appendTail(sb);  
         return sb.toString();  
     }  
	
	public static GenTable getTableInfo(String table) throws Exception {

		GenTable genTable = new GenTable();
		List<GenTableColumn> columnList = new ArrayList<GenTableColumn>();
		genTable.setColumnList(columnList);
		genTable.setTableName(table);
		
		String driver = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc_driver", "");
		String url = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc_url", "");
		String user = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc_username", "");
		String password = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc_password", "");
		String jdbctype = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbctype", "");
		String feildtype = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.feild.type", "");//UPPER、LOWER、Hump

		if(StringUtils.isBlank(driver)||StringUtils.isBlank(url)||StringUtils.isBlank(user)){
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
			if (Types.INTEGER == type) {
				jdbcType = "integer";
				javaType = "String";
			} else{
				jdbcType = "varchar";
				javaType = "String";
			}
			column.setComments("");
			if(jdbctype.equals("mybatis")){
				if("UPPER".equalsIgnoreCase(feildtype)){
					column.setJavaField(columnName.toUpperCase());
				}else if("LOWER".equalsIgnoreCase(feildtype)){
					column.setJavaField(columnName.toLowerCase());
				}else{
					column.setJavaField(DBUtil.lineToHump(columnName));
				}
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

	private static void getTables(Connection conn) throws SQLException {
		DatabaseMetaData dbMetData = conn.getMetaData();
		// mysql convertDatabaseCharsetType null
		ResultSet rs = dbMetData.getTables(null,
				convertDatabaseCharsetType("root", "mysql"), null,
				new String[] { "TABLE", "VIEW" });

		while (rs.next()) {
			if (rs.getString(4) != null
					&& (rs.getString(4).equalsIgnoreCase("TABLE") || rs
							.getString(4).equalsIgnoreCase("VIEW"))) {
				String tableName = rs.getString(3).toLowerCase();
				System.out.print(tableName + "\t");
				// 根据表名提前表里面信息：
				ResultSet colRet = dbMetData.getColumns(null, "%", tableName,
						"%");
				while (colRet.next()) {
					String columnName = colRet.getString("COLUMN_NAME");
					String columnType = colRet.getString("TYPE_NAME");
					int datasize = colRet.getInt("COLUMN_SIZE");
					int digits = colRet.getInt("DECIMAL_DIGITS");
					int nullable = colRet.getInt("NULLABLE");
					// System.out.println(columnName + " " + columnType + " "+
					// datasize + " " + digits + " " + nullable);
				}

			}
		}
		System.out.println();
	}
	public static void main(String[] args) {
		try {
			//getTableInfo("wx_user");
			System.out.println(DBUtil.lineToHump("goods_name_id"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
