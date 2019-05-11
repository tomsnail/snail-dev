package cn.tomsnail.snail.e3.tx.weixin.util.core;

import cn.tomsnail.snail.e3.tx.weixin.util.access.CredentialOper;
import cn.tomsnail.snail.e3.tx.weixin.util.accout.WXAccount;
import cn.tomsnail.snail.e3.tx.weixin.util.menu.MenuOper;
import cn.tomsnail.snail.e3.tx.weixin.util.payment.PaymentOper;
import cn.tomsnail.snail.e3.tx.weixin.util.qrc.QRCodeOper;
import cn.tomsnail.snail.e3.tx.weixin.util.redpackets.RedPacketsOper;
import cn.tomsnail.snail.e3.tx.weixin.util.sucai.MediaOper;
import cn.tomsnail.snail.e3.tx.weixin.util.templatemsg.MessageOper;
import cn.tomsnail.snail.e3.tx.weixin.util.templatemsg.TemplateOper;
import cn.tomsnail.snail.e3.tx.weixin.util.user.GroupsOper;
import cn.tomsnail.snail.e3.tx.weixin.util.user.UserOper;


public interface WechatOper extends CredentialOper, MenuOper, MediaOper, GroupsOper, QRCodeOper, UserOper,
        TemplateOper, MessageOper,RedPacketsOper,PaymentOper {

    /**
     * 微信公众平台API入口
     */
    String wechatAPI = "https://api.weixin.qq.com";

    String cgi_bin = wechatAPI + "/cgi-bin";
    
    public WXAccount getMpAct() ;
}
