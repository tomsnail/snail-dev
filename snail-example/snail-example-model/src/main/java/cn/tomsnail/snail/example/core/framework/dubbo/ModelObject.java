package cn.tomsnail.snail.example.core.framework.dubbo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ModelObject implements Serializable {

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
