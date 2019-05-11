package cn.tomsnail.snail.example.core.framework.springboot;

import cn.tomsnail.core.util.validator.RuleType;
import cn.tomsnail.core.util.validator.annotation.BeanValidator;
import cn.tomsnail.core.util.validator.annotation.FieldValidator;
import cn.tomsnail.snail.core.obj.base.BaseVo;

import javax.validation.constraints.NotNull;

@BeanValidator
public class Vo extends BaseVo<Mo> {

    @NotNull
    @FieldValidator(mapperName = "ID",onlyToBean = false)
    private String id;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
