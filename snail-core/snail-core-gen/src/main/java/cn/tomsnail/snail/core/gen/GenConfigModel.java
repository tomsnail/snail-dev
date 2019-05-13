package cn.tomsnail.snail.core.gen;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;

public final  class GenConfigModel {

    private GenConfigModel(){

    }

    private static final GenConfigModel instance = new GenConfigModel();

    public final static GenConfigModel getInstance(){
        return instance;
    }
    public final Boolean gen_test = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.test",false);
    public final Boolean gen_replace = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.replace",true);

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
    public final Boolean gen_pom = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom",true);
    public final Boolean gem_pom_modules_muliti = ConfigHelp.getInstance("genConfig").getLocalConfig("gem.pom.modules.muliti",false);
    public final String gen_pom_parent_groupId = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.parent.groupId","");
    public final String gen_pom_parent_artifactId = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.parent.artifactId","");
    public final String gen_pom_parent_version = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.parent.version","");
    public final String gen_pom_groupId = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.groupId","");
    public final String gen_pom_artifactId = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.artifactId","");
    public final String gen_pom_version = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.pom.version","");
    public final Boolean gen_dao = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao",true);
    public final String gen_dao_jdbctype = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.jdbctype","");
    public final String gen_dao_db = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.db","");
    public final String gen_dao_feild_type = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.feild.type","");
    public final Boolean gen_dao_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.service",true);
    public final Boolean gen_dao_page = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.page",true);
    public final Boolean gen_dao_vo = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.vo",true);
    public final Boolean gen_dao_dubbo = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.dao.dubbo",true);

    public final Boolean gen_api_dao_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.api.dao.service",true);
    public final Boolean gen_api_service_dubbo = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.api.service.dubbo",true);
    public final Boolean gen_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.service",true);
    public final Boolean gen_service_dubbo = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.service.dubbo",true);
    public final Boolean gen_http = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.http",true);
    public final String gen_http_type = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.http.type","");
    public final Boolean gen_junit = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit",true);
    public final Boolean gen_junit_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit.service",true);
    public final Boolean gen_junit_http = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit.http",true);
    public final Boolean gen_junit_dao = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit.dao",true);
    public final Boolean gen_junit_dao_service = ConfigHelp.getInstance("genConfig").getLocalConfig("gen.junit.dao.service",true);

    public final String gen_xml_filepath = ConfigHelp.getInstance("config").getLocalConfig("gen.xml.filepath","/templates/config.xml");

    public final String gen_xml_template_path = ConfigHelp.getInstance("config").getLocalConfig("gen.xml.template.path","/templates/");

    public final Boolean gen_resource_java = ConfigHelp.getInstance("config").getLocalConfig("gen.resource.java",true);
    public final Boolean gen_resource_test = ConfigHelp.getInstance("config").getLocalConfig("gen.resource.test",true);
    public final Boolean gen_resource_system = ConfigHelp.getInstance("config").getLocalConfig("gen.resource.system",true);

    public final Boolean gen_http_springboot_dubbo = ConfigHelp.getInstance("config").getLocalConfig("gen.http.springboot.dubbo",true);

    public final Boolean gen_pom_package = ConfigHelp.getInstance("config").getLocalConfig("gen.pom.package",true);

    public Boolean getGen_replace() {
        return gen_replace;
    }

    public String getGen_jdbc_driver() {
        return gen_jdbc_driver;
    }

    public String getGen_jdbc_url() {
        return gen_jdbc_url;
    }

    public String getGen_jdbc_username() {
        return gen_jdbc_username;
    }

    public String getGen_jdbc_password() {
        return gen_jdbc_password;
    }

    public String getGen_jdbc_tables() {
        return gen_jdbc_tables;
    }

    public String getGen_class_objects() {
        return gen_class_objects;
    }

    public String getGen_class_packageName() {
        return gen_class_packageName;
    }

    public String getGen_class_moduleName() {
        return gen_class_moduleName;
    }

    public String getGen_class_version() {
        return gen_class_version;
    }

    public String getGen_class_author() {
        return gen_class_author;
    }

    public Boolean getGen_pom() {
        return gen_pom;
    }

    public Boolean getGem_pom_modules_muliti() {
        return gem_pom_modules_muliti;
    }

    public String getGen_pom_parent_groupId() {
        return gen_pom_parent_groupId;
    }

    public String getGen_pom_parent_artifactId() {
        return gen_pom_parent_artifactId;
    }

    public String getGen_pom_parent_version() {
        return gen_pom_parent_version;
    }

    public String getGen_pom_groupId() {
        return gen_pom_groupId;
    }

    public String getGen_pom_artifactId() {
        return gen_pom_artifactId;
    }

    public String getGen_pom_version() {
        return gen_pom_version;
    }

    public Boolean getGen_dao() {
        return gen_dao;
    }

    public String getGen_dao_jdbctype() {
        return gen_dao_jdbctype;
    }

    public String getGen_dao_db() {
        return gen_dao_db;
    }

    public String getGen_dao_feild_type() {
        return gen_dao_feild_type;
    }

    public Boolean getGen_dao_service() {
        return gen_dao_service;
    }

    public Boolean getGen_dao_page() {
        return gen_dao_page;
    }

    public Boolean getGen_dao_vo() {
        return gen_dao_vo;
    }

    public Boolean getGen_dao_dubbo() {
        return gen_dao_dubbo;
    }

    public Boolean getGen_api_dao_service() {
        return gen_api_dao_service;
    }

    public Boolean getGen_api_service_dubbo() {
        return gen_api_service_dubbo;
    }

    public Boolean getGen_service() {
        return gen_service;
    }

    public Boolean getGen_service_dubbo() {
        return gen_service_dubbo;
    }

    public Boolean getGen_http() {
        return gen_http;
    }

    public String getGen_http_type() {
        return gen_http_type;
    }

    public Boolean getGen_junit() {
        return gen_junit;
    }

    public Boolean getGen_junit_service() {
        return gen_junit_service;
    }

    public Boolean getGen_junit_http() {
        return gen_junit_http;
    }

    public Boolean getGen_junit_dao() {
        return gen_junit_dao;
    }

    public Boolean getGen_junit_dao_service() {
        return gen_junit_dao_service;
    }

    public String getGen_xml_filepath() {
        return gen_xml_filepath;
    }

    public String getGen_xml_template_path() {
        return gen_xml_template_path;
    }

    public Boolean getGen_resource_java() {
        return gen_resource_java;
    }

    public Boolean getGen_resource_test() {
        return gen_resource_test;
    }

    public Boolean getGen_resource_system() {
        return gen_resource_system;
    }

    public Boolean getGen_http_springboot_dubbo() {
        return gen_http_springboot_dubbo;
    }

    public Boolean getGen_pom_package() {
        return gen_pom_package;
    }

    public Boolean getGen_test() {
        return gen_test;
    }
}
