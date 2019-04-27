package cn.tomsnail.snail.example.core.framework.dubbox;

import cn.tomsnail.snail.core.http.RequestData;
import cn.tomsnail.snail.core.http.ResultData;

public interface DemoFacade {

    public ResultData testGet(String no);

    public ResultData testPost(RequestData requestData);

}
