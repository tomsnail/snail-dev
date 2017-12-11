package cn.tomsnail.weixin.util.core;

import cn.tomsnail.weixin.util.access.CredentialOper;
import cn.tomsnail.weixin.util.accout.WXAccount;
import cn.tomsnail.weixin.util.menu.MenuOper;
import cn.tomsnail.weixin.util.payment.PaymentOper;
import cn.tomsnail.weixin.util.qrc.QRCodeOper;
import cn.tomsnail.weixin.util.redpackets.RedPacketsOper;
import cn.tomsnail.weixin.util.sucai.MediaOper;
import cn.tomsnail.weixin.util.templatemsg.MessageOper;
import cn.tomsnail.weixin.util.templatemsg.TemplateOper;
import cn.tomsnail.weixin.util.user.GroupsOper;
import cn.tomsnail.weixin.util.user.UserOper;


public interface WechatOper extends CredentialOper, MenuOper, MediaOper, GroupsOper, QRCodeOper, UserOper,
        TemplateOper, MessageOper,RedPacketsOper,PaymentOper {

    /**
     * 微信公众平台API入口
     */
    String wechatAPI = "https://api.weixin.qq.com";

    String cgi_bin = wechatAPI + "/cgi-bin";
    
    public WXAccount getMpAct() ;
}
