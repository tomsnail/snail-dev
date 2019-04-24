/**
 * @author senhui.li
 */
package cn.tomsnail.snail.e3.tx.weixin.util.msg;

import java.util.Map;

import cn.tomsnail.snail.e3.tx.weixin.util.event.BasicEvent;



public class TextMsg extends BasicMsg {

    /**
     * 文本内容
     */
    private String content;

    public TextMsg() {
        super();
        this.msgType = "text";
    }

    public TextMsg(BasicEvent event) {
        super(event);
        this.msgType = "text";
    }

    public TextMsg(BasicMsg msg) {
        super(msg);
        this.msgType = "text";
    }

    public TextMsg(Map<String, String> values) {
        super(values);
        this.content = values.get("content");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMsg [toUserName="
               + toUserName
               + ", fromUserName="
               + fromUserName
               + ", createTime="
               + createTime
               + ", msgType="
               + msgType
               + ", content="
               + content
               + ", msgId="
               + msgId
               + "]";
    }

}
