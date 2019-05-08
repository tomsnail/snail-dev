package cn.tomsnail.snail.example.core.framework.dubbo;

import cn.tomsnail.snail.example.core.framework.dubbo.annotation.server.ModelObject;

import javax.validation.constraints.NotNull;

public interface DemoService {

    public String hello(@NotNull String name);

    public String vtest(ModelObject modelObject);

}
