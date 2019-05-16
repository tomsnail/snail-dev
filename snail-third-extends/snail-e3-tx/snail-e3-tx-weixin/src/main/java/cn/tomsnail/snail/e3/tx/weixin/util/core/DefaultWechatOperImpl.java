package cn.tomsnail.snail.e3.tx.weixin.util.core;



import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.castor.Castors;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;

import cn.tomsnail.snail.e3.tx.weixin.util.accout.WXAccount;
import cn.tomsnail.snail.e3.tx.weixin.util.common.HttpTool;
import cn.tomsnail.snail.e3.tx.weixin.util.common.JsonMsgBuilder;
import cn.tomsnail.snail.e3.tx.weixin.util.common.SecUtil;
import cn.tomsnail.snail.e3.tx.weixin.util.common.XmlMsgBuilder;
import cn.tomsnail.snail.e3.tx.weixin.util.common.XmlParseUtil;
import cn.tomsnail.snail.e3.tx.weixin.util.menu.Menu;
import cn.tomsnail.snail.e3.tx.weixin.util.payment.PaymentResult;
import cn.tomsnail.snail.e3.tx.weixin.util.payment.SearchOrder;
import cn.tomsnail.snail.e3.tx.weixin.util.payment.ShortURL;
import cn.tomsnail.snail.e3.tx.weixin.util.payment.ShortURLResult;
import cn.tomsnail.snail.e3.tx.weixin.util.payment.UnifiedOrder;
import cn.tomsnail.snail.e3.tx.weixin.util.qrc.QRTicket;
import cn.tomsnail.snail.e3.tx.weixin.util.redpackets.GroupRedPackets;
import cn.tomsnail.snail.e3.tx.weixin.util.redpackets.RedPackets;
import cn.tomsnail.snail.e3.tx.weixin.util.sucai.Media;
import cn.tomsnail.snail.e3.tx.weixin.util.templatemsg.Template;
import cn.tomsnail.snail.e3.tx.weixin.util.token.AccessToken;
import cn.tomsnail.snail.e3.tx.weixin.util.token.AccessTokenMemoryCache;
import cn.tomsnail.snail.e3.tx.weixin.util.token.JSTicket;
import cn.tomsnail.snail.e3.tx.weixin.util.token.JSTicketMemoryCache;
import cn.tomsnail.snail.e3.tx.weixin.util.token.MemoryCache;
import cn.tomsnail.snail.e3.tx.weixin.util.user.FollowList;
import cn.tomsnail.snail.e3.tx.weixin.util.user.Follower;
import cn.tomsnail.snail.e3.tx.weixin.util.user.Follower2;
import cn.tomsnail.snail.e3.tx.weixin.util.user.Groups;


@SuppressWarnings("unchecked")
public class DefaultWechatOperImpl implements WechatOper {


	public static final WechatOper wechatOper = new DefaultWechatOperImpl(new WXAccount());

	
    static int RETRY_COUNT = 1;

    protected static MemoryCache<AccessToken> _atmc;

    protected static MemoryCache<JSTicket> _jstmc;

    private WXAccount mpAct;

    public DefaultWechatOperImpl(WXAccount mpAct) {
        this.mpAct = mpAct;
        synchronized (this) {
            if (_atmc == null) {
                _atmc = new AccessTokenMemoryCache();
            }
            if (_jstmc == null) {
                _jstmc = new JSTicketMemoryCache();
            }
        }
    }

    public static MemoryCache<AccessToken> getAccessTokenCache() {
        return _atmc;
    }

    public static MemoryCache<JSTicket> getJsTicketCache() {
        return _jstmc;
    }

    /**
     * WechatAPI 实现方法
     * 
     * @param mpAct
     *            微信公众号信息{@link MPAccount}
     * @return 对应的API
     */
    public static DefaultWechatOperImpl create(WXAccount mpAct) {
        return new DefaultWechatOperImpl(mpAct);
    }

    private String mergeCgiBinUrl(String url, Object... values) {
        if (!Lang.isEmpty(values)) {
            return cgi_bin + String.format(url, values);
        }
        return cgi_bin + url;
    }
    
    private String url(String url,Object... values){//https://mp.weixin.qq.com
    	 if (!Lang.isEmpty(values)) {
             return String.format(url, values);
         }
         return url;
    }

    /**
     * 强制刷新微信服务凭证
     */
    private synchronized void refreshAccessToken() {
        String url = mergeCgiBinUrl(get_at, mpAct.getAppId(), mpAct.getAppSecret());
        AccessToken at = null;
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.get(url));
            if (ar.isSuccess()) {
                at = Json.fromJson(AccessToken.class, ar.getJson());
                _atmc.set(mpAct.getMpId(), at);
            }

            if (at != null && at.isAvailable()) {
                return;
            }

			//LOGGER.error("Get mp[%s]access_token failed. There try %d items.", mpAct.getMpId(), i + 1);

        }

    }

    private synchronized void refreshJSTicket() {
        String url = mergeCgiBinUrl(js_ticket + getAccessToken());
        JSTicket jst = null;
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.get(url));
            if (ar.isSuccess()) {
                jst = Json.fromJson(JSTicket.class, ar.getJson());
                _jstmc.set(mpAct.getMpId(), jst);
            }

            if (jst != null && jst.isAvailable()) {
                return;
            }

            //LOGGER.error("Get mp[%s] JSSDK ticket failed. There try %d items.",
            //           mpAct.getMpId(),
            //           i + 1);

        }

    }

    @Override
    public String getAccessToken() {
        AccessToken at = _atmc.get(mpAct.getMpId());
        if (at == null || !at.isAvailable()) {
            refreshAccessToken();
            at = _atmc.get(mpAct.getMpId());
        }
        return at.getAccessToken();
    }

    @Override
    public List<String> getServerIps() {
        String url = mergeCgiBinUrl(cb_ips + getAccessToken());
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.get(url));
            if (ar.isSuccess()) {
                return Json.fromJsonAsList(String.class, Json.toJson(ar.get("ip_list")));
            }

            //LOGGER.error("Get mp[%s] server ips failed. There try %d items.", mpAct.getMpId(), i + 1);
        }
        return null;

    }

    @Override
    public String getShortUrl(String longUrl) {
        String url = mergeCgiBinUrl(short_url + getAccessToken());
        String data = "{\"action\":\"long2short\",\"long_url\":\"" + longUrl + "\"}";
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return String.valueOf(ar.get("short_url"));
            }

            //LOGGER.error("Create mp[%s] short url failed. There try %d items.", mpAct.getMpId(), i);
        }
        return null;

    }

    @Override
    public String getJSTicket() {
        JSTicket jst = _jstmc.get(mpAct.getMpId());
        if (jst == null || !jst.isAvailable()) {
            refreshJSTicket();
            jst = _jstmc.get(mpAct.getMpId());
        }
        return jst.getTicket();
    }

    @Override
    public List<Menu> getMenu() {
        String url = mergeCgiBinUrl(query_menu + getAccessToken());
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.get(url));
            if (ar.isSuccess()) {
                Map<String, Object> button = Json.fromJson(Map.class, Json.toJson(ar.get("menu")));
                return Json.fromJsonAsList(Menu.class, Json.toJson(button.get("button")));
            }

            // 菜单为空
            if (ar.getErrCode().equals("46003") ) {
                return null;
            }

            //LOGGER.error("Get mp[%s] custom menu failed. There try %d items.",
            //           mpAct.getAppId(),
            //           i + 1);
        }
        return null;

    }

    @Override
    public boolean createMenu(Menu... menu) {
        String url = mergeCgiBinUrl(create_menu + getAccessToken());
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("button", menu);
        String data = Json.toJson(body, JsonFormat.compact());
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return true;
            }

            //LOGGER.error("Create mp[%s] custom menu failed. There try %d items.",
            //           mpAct.getAppId(),
            //           i + 1);

        }
       return false;
    }

    @Override
    public boolean delMenu() {
        String url = mergeCgiBinUrl(del_menu + getAccessToken());
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.get(url));
            if (ar.isSuccess()) {
                return true;
            }

            //LOGGER.error("Delete mp[%s] custom menu failed. There try %d items.",
            //           mpAct.getMpId(),
            //           i + 1);
        }

        return false;
    }

    @Override
    public Media upMedia(String type, File media) {
        String url = mergeCgiBinUrl(upload_media, getAccessToken(), type);
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.upload(url, media));
            if (ar.isSuccess()) {
                return Json.fromJson(Media.class, ar.getJson());
            }

            //LOGGER.error("Upload mp[%s] media failed. There try %d items.", mpAct.getMpId(), i + 1);
        }

        return null;
    }

    @Override
    public File dlMedia(String mediaId) {
        String url = mergeCgiBinUrl(get_media, getAccessToken(), mediaId);
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            Object tmp = HttpTool.download(url);
            if (tmp instanceof File) {
                return (File) tmp;
            }

            ar = ApiResult.create((String) tmp);
            //LOGGER.error("Download mp[%s] media failed. There try %d items.", mpAct.getMpId(), i);
        }

        return null;
    }

    @Override
    public int createGroup(String name) {
        String url = mergeCgiBinUrl(create_groups + getAccessToken());
        String data = "{\"group\":{\"name\":\"" + name + "\"}}";
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                Groups g = Json.fromJson(Groups.class, Json.toJson(ar.get("group")));
                return g.getId();
            }

            //LOGGER.error("Create mp[%s] group name[%s] failed. There try %d items.",
            //           mpAct.getMpId(),
            //           name,
            //           i);
        }

        return 0;
    }

    @Override
    public List<Groups> getGroups() {
        String url = mergeCgiBinUrl(get_groups + getAccessToken());
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.get(url));
            if (ar.isSuccess()) {
                return Json.fromJsonAsList(Groups.class, Json.toJson(ar.get("groups")));
            }

            //LOGGER.error("Get mp[%s] groups failed. There try %d items.", mpAct.getMpId(), i);
        }

        return null;
    }

    @Override
    public int getGroup(String openId) {
        String url = mergeCgiBinUrl(get_member_group + getAccessToken());
        String data = "{\"openid\":\"" + openId + "\"}";
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return Integer.parseInt(String.valueOf(ar.get("groupid")));
            }

            //LOGGER.error("Get mp[%s] openId[%s] groups failed. There try %d items.",
            //           mpAct.getMpId(),
            //           openId,
            //           i);
        }

        return 0;
    }

    @Override
    public boolean renGroups(int id, String name) {
        String url = mergeCgiBinUrl(update_group + getAccessToken());
        String data = "{\"group\":{\"id\":" + id + ",\"name\":\"" + name + "\"}}";
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return true;
            }

            //LOGGER.error("Rename mp[%s] groups[%d-%s] failed. There try %d items.",
            //           mpAct.getMpId(),
            //           id,
            //           name,
            //           i);
        }

        return false;
    }

    @Override
    public boolean move2Group(String openId, int groupId) {
        String url = mergeCgiBinUrl(update_member_group + getAccessToken());
        String data = "{\"openid\":\"" + openId + "\",\"to_groupid\":" + groupId + "}";
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return true;
            }

            //LOGGER.error("Move mp[%s] openId[%s] to groups[%d] failed. There try %d items.",
            //          mpAct.getMpId(),
            //            openId,
            //           groupId,
            //           i);
        }

        return false;
    }

    @Override
    public boolean batchMove2Group(Collection<String> openIds, int groupId) {
        String url = mergeCgiBinUrl(update_members_group + getAccessToken());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("openid_list", Json.toJson(openIds));
        data.put("to_groupid", groupId);
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, Json.toJson(data, JsonFormat.compact())));
            if (ar.isSuccess()) {
                return true;
            }

            //LOGGER.error("Move mp[%s] openIds to groups[%d] failed. There try %d items.",
            //           mpAct.getMpId(),
            //           groupId,
            //           i);
        }

        return false;
    }

    @Override
    public boolean delGroup(int id) {
        String url = mergeCgiBinUrl(delete_groups + getAccessToken());
        String data = "{\"group\":{\"id\":" + id + "}}";
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return true;
            }

            //LOGGER.error("Delete mp[%s] groups[%d] failed. There try %d items.",
            //           mpAct.getMpId(),
            //           id,
            //           i);
        }

        return false;
    }

    @Override
    public QRTicket createQR(Object sceneId, int expireSeconds) {
        String url = mergeCgiBinUrl(create_qrcode + getAccessToken());
        ApiResult ar = null;
        NutMap data = new NutMap();
        NutMap scene;
        // 临时二维码
        if (expireSeconds > 0) {
            data.put("action_name", "QR_SCENE");
            data.put("expire_seconds", expireSeconds);

            scene = Lang.map("scene_id", Castors.me().castTo(sceneId, Integer.class));
        }
        // 永久二维码
        else if (sceneId instanceof Number) {
            data.put("action_name", "QR_LIMIT_SCENE");
            scene = Lang.map("scene_id", Castors.me().castTo(sceneId, Integer.class));
        }
        // 永久字符串二维码
        else {
            data.put("action_name", "QR_LIMIT_STR_SCENE");
            scene = Lang.map("scene_str", sceneId.toString());
        }
        data.put("action_info", Lang.map("scene", scene));
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, Json.toJson(data, JsonFormat.compact())));
            if (ar.isSuccess()) {
                return Json.fromJson(QRTicket.class, Json.toJson(ar.getContent()));
            }

//            //LOGGER.error("Create mp[%s] scene[%s] qrcode failed. There try %d items.",
//                      mpAct.getMpId(),
//                      data.get("action_name"),
//                      i);
        }

        return null;
    }

    @Override
    public Object getQR(String ticket) {
        String url = url(show_qrcode + ticket);
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            Object tmp = HttpTool.get(url);
            if (tmp instanceof File) {
                return (File) tmp;
            }
            return tmp;
            //LOGGER.error("Download mp[%s] qrcode image failed. There try %d items.",mpAct.getMpId(),i);
        }

        return null;
    }

    @Override
    public boolean updateRemark(String openId, String remark) {
        String url = mergeCgiBinUrl(user_remark + getAccessToken());
        ApiResult ar = null;
        String data = "{\"openid\":\"" + openId + "\",\"remark\":\"" + remark + "\"}";
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return true;
            }

            //LOGGER.error("Update mp[%s] user[%s] remark[%s] failed. There try %d items.",
            //           mpAct.getMpId(),
            //           openId,
            //           remark,
            //           i);
        }

        return false;
    }

    @Override
    public FollowList getFollowerList(String nextOpenId) {
        String url = mergeCgiBinUrl(user_list, getAccessToken(), Strings.sNull(nextOpenId, ""));
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.get(url));
            if (ar.isSuccess()) {
                FollowList fl = Json.fromJson(FollowList.class, ar.getJson());
                Map<String, Object> openid = (Map<String, Object>) ar.get("data");
                fl.setOpenIds(Json.fromJson(List.class, Json.toJson(openid.get("openid"))));
                return fl;
            }

            //LOGGER.error("Get mp[%s] follow list failed. There try %d items.", mpAct.getMpId(), i);
        }

        return null;
    }

    @Override
    public Follower getFollower(String openId, String lang) {
        String url = mergeCgiBinUrl(user_info, getAccessToken(), openId, Strings.sNull(lang, "zh_CN"));
        ApiResult ar = null;
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.get(url));
            if (ar.isSuccess()) {
                return Json.fromJson(Follower.class, ar.getJson());
            }

            //LOGGER.error("Get mp[%s] follower[%s] information failed. There try %d items.",
            //           mpAct.getMpId(),
            //           openId,
            //           i);
        }

        return null;
    }

    @Override
    public List<Follower> getFollowers(Collection<Follower2> users) {
        String url = mergeCgiBinUrl(batch_user_info + getAccessToken());
        ApiResult ar = null;
        String data = Json.toJson(Lang.map("user_list", users), JsonFormat.compact());
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return Json.fromJsonAsList(Follower.class, Json.toJson(ar.get("user_info_list")));
            }

            //LOGGER.error("Get mp[%s] followers information failed. There try %d items.",
            //           mpAct.getMpId(),
            //           i);
        }

        return null;
    }

    @Override
    public boolean setIndustry(int id1, int id2) {
        String url = mergeCgiBinUrl(set_industry + getAccessToken());
        ApiResult ar = null;
        String data = "{\"industry_id1\":\"" + id1 + "\",\"industry_id2\":\"" + id2 + "\"}";
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return true;
            }

            //LOGGER.error("Set mp[%s] template industry failed. There try %d items.",
            //           mpAct.getMpId(),
            //           i);
        }

        return false;
    }

    @Override
    public String getTemplateId(String tmlShortId) {
        String url = mergeCgiBinUrl(add_template + getAccessToken());
        ApiResult ar = null;
        String data = "{\"template_id_short\":\"" + tmlShortId + "\"}";
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(url, data));
            if (ar.isSuccess()) {
                return String.valueOf(ar.get("template_id"));
            }

            //LOGGER.error("Get mp[%s] template id failed. There try %d items.", mpAct.getMpId(), i);
        }

        return null;
    }

    @Override
    public long sendTemplateMsg(String openId,
                                String tmlId,
                                String topColor,
                                String url,
                                Template... tmls) {
        String apiurl = mergeCgiBinUrl(send_template + getAccessToken());
        ApiResult ar = null;
        String data = JsonMsgBuilder.create().template(openId, tmlId, topColor, url, tmls).build();
        for (int i = 0; i < RETRY_COUNT; i++) {
            ar = ApiResult.create(HttpTool.post(apiurl, data));
            if (ar.isSuccess()) {
                return Long.parseLong(ar.get("msgid").toString());
            }

            //LOGGER.error("Send mp[%s] template message failed. There try %d items.",
            //           mpAct.getMpId(),
            //           i);
        }

        return 0;
    }

	@Override
	public boolean sendRedPackets(RedPackets redPackets) {
		if(redPackets!=null){
			ApiResult ar = null;
	        String data = null;
			try {
				data = XmlMsgBuilder.create().build(redPackets);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	        for (int i = 0; i < RETRY_COUNT; i++) {
	            ar = ApiResult.create(HttpTool.post(sendRedPackets, data));
	            if (ar.isSuccess()) {
	                return true;
	            }

	            //LOGGER.error("Send mp[%s] template message failed. There try %d items.",
	            //           mpAct.getMpId(),
	            //           i);
	        }
		}
		return false;
	}

	@Override
	public boolean sendGroupRedPackets(GroupRedPackets groupRedPackets) {
		if(groupRedPackets!=null){
			ApiResult ar = null;
	        String data = null;
			try {
				data = XmlMsgBuilder.create().build(groupRedPackets);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	        for (int i = 0; i < RETRY_COUNT; i++) {
	            ar = ApiResult.create(HttpTool.post(sendGroupRedPackets, data));
	            if (ar.isSuccess()) {
	                return true;
	            }

	            //LOGGER.error("Send mp[%s] template message failed. There try %d items.",
	            //           mpAct.getMpId(),
	            //           i);
	        }
		}
		return false;
	}

	@Override
	public PaymentResult unifiedorder(UnifiedOrder unifiedOrder) {
		if(unifiedOrder!=null){
			PaymentResult pr = new PaymentResult();
	        String data = null;
			try {
				data = XmlMsgBuilder.create().build(unifiedOrder);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	        for (int i = 0; i < RETRY_COUNT; i++) {
	        	
	        	try {
					XmlParseUtil.getObject(HttpTool.post(unifiedorder, data), pr);
				} catch (Exception e) {
					e.printStackTrace();
				}
	            if (pr.getResult_code().equals("SUCCESS")&&pr.getReturn_code().equals("SUCCESS")) {
	                return pr;
	            }

	            //LOGGER.error("Send mp[%s] template message failed. There try %d items.",
	            //           mpAct.getMpId(),
	            //           i);
	        }
		}
		return null;
	}

	@Override
	public PaymentResult orderquery(SearchOrder searchOrder) {
		if(searchOrder!=null){
			PaymentResult pr = new PaymentResult();
	        String data = null;
			try {
				data = XmlMsgBuilder.create().build(searchOrder);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	        for (int i = 0; i < RETRY_COUNT; i++) {
	        	
	        	try {
					XmlParseUtil.getObject(HttpTool.post(orderquery, data), pr);
				} catch (Exception e) {
					e.printStackTrace();
				}
	            if (pr.getResult_code().equals("SUCCESS")&&pr.getReturn_code().equals("SUCCESS")) {
	                return pr;
	            }

	            //LOGGER.error("Send mp[%s] template message failed. There try %d items.",
	            //           mpAct.getMpId(),
	            //           i);
	        }
		}
		return null;
	}

	@Override
	public ShortURLResult shorturl(ShortURL shortURL) {
		if(shortURL!=null){
			ShortURLResult pr = new ShortURLResult();
	        String data = null;
			try {
				data = XmlMsgBuilder.create().build(shortURL);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	        for (int i = 0; i < RETRY_COUNT; i++) {
	        	
	        	try {
					XmlParseUtil.getObject(HttpTool.post(shorturl, data), pr);
				} catch (Exception e) {
					e.printStackTrace();
				}
	            if (pr.getReturn_code().equals("SUCCESS")) {
	                return pr;
	            }

	            //LOGGER.error("Send mp[%s] template message failed. There try %d items.",
	             //          mpAct.getMpId(),
	             //          i);
	        }
		}
		return null;
	}

	@Override
	public String getCodeAt(String code) {
		   String url = url(get_code_at ,this.mpAct.getAppId(),this.mpAct.getAppSecret(),code);
	        ApiResult ar = null;
	        for (int i = 0; i < RETRY_COUNT; i++) {
	            String tmp = HttpTool.get(url);
	           ar = new ApiResult(tmp);
	           if(ar.isSuccess()){
	        	   return (String) ar.get("access_token");
	           }
	        }
	        return null;
	}

	@Override
	public String getCodeOpenId(String code) {
		 String url = url(get_code_at ,this.mpAct.getAppId(),this.mpAct.getAppSecret(),code);
	        ApiResult ar = null;
	        for (int i = 0; i < RETRY_COUNT; i++) {
	            String tmp = HttpTool.get(url);
	           ar = new ApiResult(tmp);
	           if(ar.isSuccess()){
	        	   return (String) ar.get("openid");
	           }
	        }
	        return null;
	}

	public WXAccount getMpAct() {
		return mpAct;
	}



}
