package cn.tomsnail.snail.example.core.framework.springboot;

import cn.tomsnail.snail.core.obj.base.BaseModel;

import javax.validation.constraints.NotNull;

public class ModelObject extends BaseModel {

    @NotNull
    private String id;

    private String name;

    private String age;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
