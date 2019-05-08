package cn.tomsnail.snail.example.core.framework.dubbo;

import javax.validation.constraints.NotNull;

public interface DemoService {

    public String hello(@NotNull String name);

    public String vtest(ModelObject modelObject);

}