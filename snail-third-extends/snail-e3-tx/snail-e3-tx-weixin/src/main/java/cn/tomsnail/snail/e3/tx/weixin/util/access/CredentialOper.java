package cn.tomsnail.snail.e3.tx.weixin.util.access;

import java.util.List;


public interface CredentialOper {

    /**
     * 获取access_token地址
     */
    String get_at = "/token?grant_type=client_credential&appid=%s&secret=%s";
    
    String get_code_at = " https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 获取微信服务器IP地址
     */
    String cb_ips = "/getcallbackip?access_token=";

    /**
     * 长链接转短链接地址
     */
    String short_url = "/shorturl?access_token=";

    /**
     * JSSDK临时凭证地址
     */
    String js_ticket = "/ticket/getticket?type=jsapi&access_token=";

    /**
     * 获取微信服务凭证
     * 
     * @return 凭证
     */
    String getAccessToken();

    /**
     * 获取微信服务器IP地址
     * 
     * @return IP地址
     */
    List<String> getServerIps();

    /**
     * 长链接转短链接
     * 
     * @param longUrl
     *            需要转换的长链接,支持http://,https://,weixin://wxpay 格式的url
     * @return 短链接
     */
    String getShortUrl(String longUrl);

    /**
     * 获取JSSDK凭证
     * 
     * @return 凭证
     */
    String getJSTicket();
    
    String getCodeAt(String code);
    
    String getCodeOpenId(String code);
}
