package cn.tomsnail.snail.core.gen;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;

public final  class GenConfigModel {

    private GenConfigModel(){

    }

    private static final GenConfigModel instance = new GenConfigModel();

    public final static GenConfigModel getInstance(){
        return instance;
    }

    public final boolean gen_replace = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.replace",true);

    public final String gen_jdbc_driver = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc.driver","");
    public final String gen_jdbc_url = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc.url","");
    public final String gen_jdbc_username = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc.username","");
    public final String gen_jdbc_password = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc.password","");
    public final String gen_jdbc_tables = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.jdbc.tables","");
    public final String gen_class_objects = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.class.objects","");
    public final String gen_class_packageName = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.class.packageName","");
    public final String gen_class_moduleName = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.class.moduleName","");
    public final String gen_class_version = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.class.version","");
    public final String gen_class_author = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.class.author","");
    public final boolean gen_pom = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom",true);
    public final boolean gem_pom_modules_muliti = ConfigHelp.getInstance("genConfig").getLocalConfig("gem.pom.modules.muliti",false);
    public final String gen_pom_parent_groupId = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.parent.groupId","");
    public final String gen_pom_parent_artifactId = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.parent.artifactId","");
    public final String gen_pom_parent_version = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.parent.version","");
    public final String gen_pom_groupId = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.groupId","");
    public final String gen_pom_artifactId = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.artifactId","");
    public final String gen_pom_version = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.version","");
    public final boolean gen_dao = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao",true);
    public final String gen_dao_jdbctype = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.jdbctype","");
    public final String gen_dao_db = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.db","");
    public final String gen_dao_feild_type = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.feild.type","");
    public final boolean gen_dao_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.service",true);
    public final boolean gen_dao_page = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.page",true);
    public final boolean gen_dao_vo = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.vo",true);
    public final boolean gen_dao_dubbo = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.dubbo",true);

    public final boolean gen_api_dao_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.api.dao.service",true);
    public final boolean gen_api_service_dubbo = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.api.service.dubbo",true);
    public final boolean gen_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.service",true);
    public final boolean gen_service_dubbo = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.service.dubbo",true);
    public final boolean gen_http = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.http",true);
    public final String gen_http_type = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.http.type","");
    public final boolean gen_junit = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit",true);
    public final boolean gen_junit_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit.service",true);
    public final boolean gen_junit_http = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit.http",true);
    public final boolean gen_junit_dao = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit.dao",true);
    public final boolean gen_junit_dao_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit.dao.service",true);

    public final String gen_xml_filepath = ConfigHelp.getInstance("config").getLocalConfig("gen.xml.filepath","/templates/config.xml");

    public final String gen_xml_template_path = ConfigHelp.getInstance("config").getLocalConfig("gen.xml.template.path","/templates/");

    public final boolean gen_resource_java = ConfigHelp.getInstance("config").getLocalConfig("gen.resource.java",false);
    public final boolean gen_resource_test = ConfigHelp.getInstance("config").getLocalConfig("gen.resource.test",false);
    public final boolean gen_resource_system = ConfigHelp.getInstance("config").getLocalConfig("gen.resource.system",false);

}
