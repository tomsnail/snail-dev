package cn.tomsnail.weixin.util.accout;

import cn.tomsnail.weixin.util.Constants;


public class WXAccount {

    /**
     * 公众号原始ID
     */
    private String mpId = Constants.WXAPPID;

    /**
     * 公众号昵称
     */
    private String nickName;

    /**
     * 应用Id
     */
    private String appId = Constants.WXAPPID;
    
    /**
     * 
     */
    private String mchId = Constants.MCH_ID;
    
    /**
     * 
     */
    private String mchName = Constants.MCH_NAME;
    
    /**
     * 
     */
    private String mchKey = Constants.MCH_KEY;

    /**
     * 应用密钥
     */
    private String appSecret = Constants.appSecret;

    /**
     * 令牌
     */
    private String token = Constants.TOKEN;

    /**
     * AES安全加密密钥
     */
    private String AESKey;

    /**
     * 公众号类型
     * D:订阅号
     * E:企业号
     * S:服务号
     */
    private String mpType = "D";

    /**
     * 是否认证
     */
    private boolean pass = false;

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAESKey() {
        return AESKey;
    }

    public void setAESKey(String aESKey) {
        AESKey = aESKey;
    }

    public String getMpType() {
        return mpType;
    }

    public void setMpType(String mpType) {
        this.mpType = mpType;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
    
    public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchName() {
		return mchName;
	}

	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	@Override
    public String toString() {
        return "MPAccount [mpId="
               + mpId
               + ", nickName="
               + nickName
               + ", appId="
               + appId
               + ", appSecret="
               + appSecret
               + ", token="
               + token
               + ", AESKey="
               + AESKey
               + ", mpType="
               + mpType
               + ", pass="
               + pass
               + "]";
    }
}
