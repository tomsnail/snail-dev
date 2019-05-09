package cn.tomsnail.snail.example.core.framework.v;

import cn.tomsnail.core.util.validator.RuleType;
import cn.tomsnail.core.util.validator.annotation.BeanValidator;
import cn.tomsnail.core.util.validator.annotation.FieldValidator;
import cn.tomsnail.snail.core.obj.base.BaseVo;
import cn.tomsnail.snail.example.core.framework.m.Mo;

import javax.validation.constraints.NotNull;

@BeanValidator
public class Vo1 extends BaseVo<Mo> {

    @NotNull
    @FieldValidator(mapperName = "ID",onlyToBean = false)
    private String id;

    @FieldValidator(mapperName = "NickName",onlyToBean = false,rules = {RuleType.NotNullValue,RuleType.LIMITMAX},values = {"","20"})
    private String name;



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
}
