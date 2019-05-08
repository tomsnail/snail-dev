package cn.tomsnail.snail.example.core.framework.springboot;

import cn.tomsnail.core.util.validator.annotation.BeanValidator;
import cn.tomsnail.core.util.validator.annotation.FieldValidator;
import cn.tomsnail.snail.core.obj.base.BaseVo;

import javax.validation.constraints.NotNull;

@BeanValidator
public class ViewObject extends BaseVo<ModelObject> {

    @FieldValidator(onlyToBean=true,mapperName = "ID")
    @NotNull
    private String id;
    @FieldValidator(onlyToBean=true,mapperName = "NICK_NAME")
    private String name;
    @FieldValidator(onlyToBean=true,mapperName = "AGE")
    private String age;
    @FieldValidator(onlyToBean=true,mapperName = "DESC")
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
