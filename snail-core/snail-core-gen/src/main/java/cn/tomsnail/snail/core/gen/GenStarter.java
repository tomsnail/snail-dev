package cn.tomsnail.snail.core.gen;

import cn.tomsnail.snail.core.gen.db.DBUtil;
import cn.tomsnail.snail.core.gen.db.GenTable;
import cn.tomsnail.snail.core.gen.template.GenConfig;
import cn.tomsnail.snail.core.gen.template.GenScheme;
import cn.tomsnail.snail.core.gen.template.GenTemplate;
import cn.tomsnail.snail.core.gen.utils.GenUtils;
import cn.tomsnail.snail.core.gen.utils.LoggerUtils;
import cn.tomsnail.snail.core.util.JsonUtil;
import cn.tomsnail.snail.core.util.commons.CollectionUtils;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class GenStarter {


	private static Pattern linePattern = Pattern.compile("_(\\w)");



	public static void main(String[] args) throws Exception {


		new GenStarter().gen();

	}

	public GenStarter(){

	}

	public void gen(){
		String[] tables = GenConfigModel.getInstance().gen_jdbc_tables.split(",");
		String[] objects = GenConfigModel.getInstance().gen_class_objects.split(",");


		if(tables.length==1&&tables[0].equals("*")) {
			tables = DBUtil.getTables().toArray(tables);
			objects = new String[tables.length];
			for(int i=0;i<tables.length;i++) {
				objects[i] = StringUtils.toUpperCaseFirstOne(StringUtils.lineToHump(linePattern,tables[i]));
			}
		}


		List<GenTemplate> templateList = GenConfig.getTemplateList();
		if(CollectionUtils.isEmpty(templateList)){
			return;
		}

		for(int i=0;i<tables.length;i++){
			if(!StringUtils.isBlank(tables[i])&&!StringUtils.isBlank(objects[i])){
//				GenTable genTable = DBUtil.getTableInfo(tables[i],objects[i]);
				String  genTableStr = "{\"childList\":[],\"className\":\"DispatchUrl\",\"columnList\":[{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"id\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"1\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":true,\"isPk\":\"1\",\"isQuery\":\"1\",\"javaField\":\"id\",\"javaField4Dto\":\"id\",\"javaFieldAttrs\":[],\"javaFieldId\":\"id\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"id\",\"nameAndComments\":\"id  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"id\",\"simpleJavaType\":\"String\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"strategyId\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"2\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"strategyId\",\"javaField4Dto\":\"strategyId\",\"javaFieldAttrs\":[],\"javaFieldId\":\"strategyId\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"strategy_id\",\"nameAndComments\":\"strategy_id  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"strategyId\",\"simpleJavaType\":\"String\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"urlType\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"3\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"urlType\",\"javaField4Dto\":\"urlType\",\"javaFieldAttrs\":[],\"javaFieldId\":\"urlType\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"url_type\",\"nameAndComments\":\"url_type  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"urlType\",\"simpleJavaType\":\"String\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"urlContext\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"4\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"urlContext\",\"javaField4Dto\":\"urlContext\",\"javaFieldAttrs\":[],\"javaFieldId\":\"urlContext\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"url_context\",\"nameAndComments\":\"url_context  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"urlContext\",\"simpleJavaType\":\"String\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"urlUserName\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"5\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"urlUserName\",\"javaField4Dto\":\"urlUserName\",\"javaFieldAttrs\":[],\"javaFieldId\":\"urlUserName\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"url_user_name\",\"nameAndComments\":\"url_user_name  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"urlUserName\",\"simpleJavaType\":\"String\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"urlPassword\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"6\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"urlPassword\",\"javaField4Dto\":\"urlPassword\",\"javaFieldAttrs\":[],\"javaFieldId\":\"urlPassword\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"url_password\",\"nameAndComments\":\"url_password  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"urlPassword\",\"simpleJavaType\":\"String\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"urlAdditional\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"7\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"urlAdditional\",\"javaField4Dto\":\"urlAdditional\",\"javaFieldAttrs\":[],\"javaFieldId\":\"urlAdditional\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"url_additional\",\"nameAndComments\":\"url_additional  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"urlAdditional\",\"simpleJavaType\":\"String\"},{\"annotationList\":[\"javax.validation.constraints.NotNull(message=\\\"不能为空\\\")\"],\"comments\":\"\",\"csimpleJavaField\":\"createDate\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"8\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"createDate\",\"javaField4Dto\":\"createDate\",\"javaFieldAttrs\":[],\"javaFieldId\":\"createDate\",\"javaFieldName\":\"\",\"javaType\":\"Timestamp\",\"jdbcType\":\"varchar\",\"name\":\"create_date\",\"nameAndComments\":\"create_date  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[\"NotNull(message=\\\"不能为空\\\")\"],\"simpleJavaField\":\"createDate\",\"simpleJavaType\":\"Timestamp\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"createBy\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"9\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"createBy\",\"javaField4Dto\":\"createBy\",\"javaFieldAttrs\":[],\"javaFieldId\":\"createBy\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"create_by\",\"nameAndComments\":\"create_by  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"createBy\",\"simpleJavaType\":\"String\"},{\"annotationList\":[\"javax.validation.constraints.NotNull(message=\\\"不能为空\\\")\"],\"comments\":\"\",\"csimpleJavaField\":\"updateDate\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"10\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"updateDate\",\"javaField4Dto\":\"updateDate\",\"javaFieldAttrs\":[],\"javaFieldId\":\"updateDate\",\"javaFieldName\":\"\",\"javaType\":\"Timestamp\",\"jdbcType\":\"varchar\",\"name\":\"update_date\",\"nameAndComments\":\"update_date  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[\"NotNull(message=\\\"不能为空\\\")\"],\"simpleJavaField\":\"updateDate\",\"simpleJavaType\":\"Timestamp\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"updateBy\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"11\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"updateBy\",\"javaField4Dto\":\"updateBy\",\"javaFieldAttrs\":[],\"javaFieldId\":\"updateBy\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"update_by\",\"nameAndComments\":\"update_by  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"updateBy\",\"simpleJavaType\":\"String\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"delFlag\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"12\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"delFlag\",\"javaField4Dto\":\"delFlag\",\"javaFieldAttrs\":[],\"javaFieldId\":\"delFlag\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"del_flag\",\"nameAndComments\":\"del_flag  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"delFlag\",\"simpleJavaType\":\"String\"},{\"annotationList\":[],\"comments\":\"\",\"csimpleJavaField\":\"remarks\",\"dataLength\":\"0\",\"dictType\":\"\",\"genTable\":{\"$ref\":\"$\"},\"id\":\"13\",\"isEdit\":\"1\",\"isInsert\":\"1\",\"isNotBaseField\":true,\"isPK\":false,\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"remarks\",\"javaField4Dto\":\"remarks\",\"javaFieldAttrs\":[],\"javaFieldId\":\"remarks\",\"javaFieldName\":\"\",\"javaType\":\"String\",\"jdbcType\":\"varchar\",\"name\":\"remarks\",\"nameAndComments\":\"remarks  :  \",\"queryType\":\"=\",\"simpleAnnotationList\":[],\"simpleJavaField\":\"remarks\",\"simpleJavaType\":\"String\"}],\"createDateExists\":true,\"dbType\":\"mysql\",\"delFlagExists\":true,\"importList\":[\"javax.validation.constraints.NotNull\"],\"nameAndComments\":\"null\",\"parentExists\":false,\"tableName\":\"dispatch_url\",\"updateDateExists\":true}";
				GenTable genTable = JsonUtil.getObject(genTableStr,GenTable.class);
				if(genTable==null) continue;
				GenScheme genScheme =GenScheme.genScheme(genTable);
				for (GenTemplate tpl : templateList){
					System.out.println(GenUtils.generateToFile(tpl, genScheme.dataModel(), genScheme.getReplaceFile()));
				}
			}
		}
	}
	

	
}
