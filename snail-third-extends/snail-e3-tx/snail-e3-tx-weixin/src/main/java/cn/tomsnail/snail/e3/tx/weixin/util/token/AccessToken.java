package cn.tomsnail.snail.e3.tx.weixin.util.token;

import org.nutz.json.JsonField;
import org.nutz.lang.Lang;


public class AccessToken {

    /**
     * 获取到的凭证
     */
    @JsonField(value = "access_token")
    private String accessToken;

    /**
     * 凭证有效时间,单位:秒
     */
    @JsonField(value = "expires_in")
    private long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        // 考虑到服务器时间同步,故将刷新时间提前60秒.
        this.expiresIn = System.currentTimeMillis() + (expiresIn - 60) * 1000;
    }

    public boolean isAvailable() {
        if (!Lang.isEmpty(accessToken) && this.expiresIn >= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "AccessToken [accessToken=" + accessToken + ", expiresIn=" + expiresIn + "]";
    }
}
