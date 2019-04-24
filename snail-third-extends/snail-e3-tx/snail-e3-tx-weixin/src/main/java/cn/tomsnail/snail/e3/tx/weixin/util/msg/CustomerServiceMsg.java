package cn.tomsnail.snail.e3.tx.weixin.util.msg;

import cn.tomsnail.snail.e3.tx.weixin.util.core.MessageType;

/**
 * 客服消息转发
 *
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.b.2
 */
public class CustomerServiceMsg extends BasicMsg {


    public CustomerServiceMsg() {
        this.msgType = MessageType.transfer_customer_service.name();
    }

    @Override
    public String toString() {
        return "CustomerServiceMsg [toUserName="
               + toUserName
               + ", fromUserName="
               + fromUserName
               + ", createTime="
               + createTime
               + ", msgType="
               + msgType
               + "]";
    }

}
