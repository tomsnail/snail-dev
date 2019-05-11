package cn.tomsnail.snail.e3.tx.weixin.util.common;

import java.lang.reflect.Field;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import cn.tomsnail.snail.e3.tx.weixin.util.msg.Article;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.BasicMsg;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.CustomerServiceMsg;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.ImageMsg;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.MusicMsg;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.NewsMsg;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.TextMsg;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.VideoMsg;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.VoiceMsg;
import cn.tomsnail.snail.e3.tx.weixin.util.redpackets.RedPackets;




public class XmlMsgBuilder {

    private static final Log log = Logs.get();

    private final StringBuffer msgBuf = new StringBuffer("<xml>\n");;

    /**
     * 创建
     */
    public static XmlMsgBuilder create() {
        return new XmlMsgBuilder();
    }

    /**
     * 创建消息体前缀
     *
     * @param msg
     *            输出消息实体
     */
    void msgPrefix(BasicMsg msg) {
        msgBuf.append("<ToUserName><![CDATA[")
              .append(msg.getToUserName())
              .append("]]></ToUserName>\n");
        msgBuf.append("<FromUserName><![CDATA[")
              .append(msg.getFromUserName())
              .append("]]></FromUserName>\n");
        msgBuf.append("<CreateTime>").append(msg.getCreateTime()).append("</CreateTime>\n");
        msgBuf.append("<MsgType><![CDATA[").append(msg.getMsgType()).append("]]></MsgType>\n");
    }

    /**
     * 被动文本消息
     * 
     * @param msg
     *            文本消息
     */
    public XmlMsgBuilder text(TextMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Content><![CDATA[").append(msg.getContent()).append("]]></Content>\n");
        return this;
    }

    /**
     * 被动图像消息
     *
     * @param msg
     *            图像消息
     */
    public XmlMsgBuilder image(ImageMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Image>");
        msgBuf.append("<MediaId><![CDATA[").append(msg.getMediaId()).append("]]></MediaId>\n");
        msgBuf.append("</Image>");
        return this;
    }

    /**
     * 被动语音消息
     *
     * @param msg
     *            音频消息
     */
    public XmlMsgBuilder voice(VoiceMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Voice>");
        msgBuf.append("<MediaId><![CDATA[").append(msg.getMediaId()).append("]]></MediaId>\n");
        msgBuf.append("</Voice>\n");
        return this;
    }

    /**
     * 被动视频消息
     *
     * @param msg
     *            视频消息
     */
    public XmlMsgBuilder video(VideoMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Video>");
        msgBuf.append("<MediaId><![CDATA[").append(msg.getMediaId()).append("]]></MediaId>\n");
        msgBuf.append("<Title><![CDATA[").append(msg.getTitle()).append("]]></Title>\n");
        msgBuf.append("<Description><![CDATA[")
              .append(msg.getDescription())
              .append("]]></Description>\n");
        msgBuf.append("</Video>\n");
        return this;
    }

    /**
     * 被动音乐消息
     * 
     * @param msg
     *            音乐消息
     */

    public XmlMsgBuilder music(MusicMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Music>");
        msgBuf.append("<Title><![CDATA[").append(msg.getTitle()).append("]]></Title>\n");
        msgBuf.append("<Description><![CDATA[")
              .append(msg.getDescription())
              .append("]]></Description>\n");
        msgBuf.append("<MusicUrl><![CDATA[").append(msg.getMusicUrl()).append("]]></MusicUrl>\n");
        msgBuf.append("<HQMusicUrl><![CDATA[")
              .append(msg.getHQMusicUrl())
              .append("]]></HQMusicUrl>\n");
        msgBuf.append("<ThumbMediaId><![CDATA[")
              .append(msg.getThumbMediaId())
              .append("]]></ThumbMediaId>\n");
        msgBuf.append("</Music>\n");
        return this;
    }

    /**
     * 被动多图文消息
     *
     * @param msg
     *            图文消息
     */
    public XmlMsgBuilder news(NewsMsg msg) {
        msgPrefix(msg);
        StringBuffer arts_buf = new StringBuffer("<Articles>\n");
        StringBuffer item_buf = new StringBuffer();
        for (Article art : msg.getArticles()) {
            item_buf.setLength(0);
            item_buf.append("<item>\n");
            item_buf.append("<Title><![CDATA[").append(art.getTitle()).append("]]></Title>\n");
            item_buf.append("<Description><![CDATA[")
                    .append(art.getDescription())
                    .append("]]></Description>\n");
            item_buf.append("<PicUrl><![CDATA[").append(art.getPicUrl()).append("]]></PicUrl>\n");
            item_buf.append("<Url><![CDATA[").append(art.getUrl()).append("]]></Url>\n");
            item_buf.append("</item>\n");
            arts_buf.append(item_buf);
        }
        arts_buf.append("</Articles>\n");
        msgBuf.append("<ArticleCount>").append(msg.getCount()).append("</ArticleCount>\n");
        msgBuf.append(arts_buf);
        return this;
    }

    /**
     * 转发客服消息
     *
     * @param msg   客服消息
     */
    public XmlMsgBuilder transferCustomerService(CustomerServiceMsg msg) {
        msgPrefix(msg);
        return this;
    }

    /**
     * AES加密信息
     *
     * @param xml
     *            密文消息
     * @param msgSignature
     *            消息签名
     * @param timeStamp
     *            时间戳
     * @param nonce
     *            随机字符
     */
    public String encrypt(String xml, String msgSignature, String timeStamp, String nonce) {
        msgBuf.setLength(0);
        msgBuf.append("<xml>\n");
        msgBuf.append("<Encrypt><![CDATA[").append(xml).append("]]></Encrypt>\n");
        msgBuf.append("<MsgSignature><![CDATA[")
              .append(msgSignature)
              .append("]]></MsgSignature>\n");
        msgBuf.append("<TimeStamp>").append(timeStamp).append("</TimeStamp>\n");
        msgBuf.append("<Nonce><![CDATA[").append(nonce).append("]]></Nonce>\n");
        msgBuf.append("</xml>");
        return msgBuf.toString();
    }

    /**
     * 输出回复消息
     *
     * @return 回复消息
     */
    public String build() {
        msgBuf.append("</xml>");
        if (log.isDebugEnabled()) {
            log.debugf("Xml message content: \n%s", msgBuf);
        }
        return new String(msgBuf);
    }
    
    public String build(Object o) throws Exception{
    	
    	if(o==null){
    		return null;
    	}
    	
    	Class clazz = o.getClass();
    	
    	Field[] fs = clazz.getDeclaredFields();
    	
    	for(Field f:fs){
    		f.setAccessible(true);
    		String name = f.getName();
    		Object v = f.get(o);
    		if(v==null) continue;
    		//<mch_id><![CDATA[888]]></mch_id>
    		msgBuf.append("<").append(name).append("><![CDATA[").append(v).append("]]></").append(name).append(">");
    	}
    	
    	msgBuf.append("</xml>");
    	return new String(msgBuf);
    }
    
    public static void main(String[] args) {
		try {
			System.out.println(XmlMsgBuilder.create().build(new RedPackets()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
