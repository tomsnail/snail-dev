package cn.tomsnail.snail.e3.tx.weixin.util.core;

import java.util.Map;

import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;


/**
 * 封装微信api返回结果, 输出实体类
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@SuppressWarnings("unchecked")
public class ApiResult {

    private static final Log log = Logs.get();

 

    private Map<String, Object> content;
    private String json;
    private String errCode;
    private String errMsg;
    private String errCNMsg;

    public ApiResult(String json) {
        this.json = json;
        this.content = Json.fromJson(Map.class, json);
        this.errCode =( (this.content.get("errcode")+"")==null?(this.content.get("err_code")+""):(this.content.get("errcode")+""));
        this.errMsg = (String) this.content.get("errmsg");
        //this.errCNMsg = this.errCode == null ? "请求成功." : _cr.get(String.valueOf(this.errCode));

        if (log.isInfoEnabled()) {
            log.infof("Wechat api result: %s", json);
            log.infof("%s", this.getErrCNMsg());
        }
    }

    public static ApiResult create(String json) {
        return new ApiResult(json);
    }

    public Object get(String key) {
        return content.get(key);
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public String getJson() {
        return json;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg == null ? "Unknow Error!" : this.errMsg;
    }

    public String getErrCNMsg() {
        return this.errCNMsg == null ? "未知错误!" : this.errCNMsg;
    }

    public boolean isSuccess() {
        return (this.errCode == null || this.errCode.equals("0") || this.errCode.equals("null"));
    }

}
