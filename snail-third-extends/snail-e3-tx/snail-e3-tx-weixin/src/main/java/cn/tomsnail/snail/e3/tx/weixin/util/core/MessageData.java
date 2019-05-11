package cn.tomsnail.snail.e3.tx.weixin.util.core;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;




public class MessageData {
	
	
	private String sessionId;
	
	
	
	private Map<String, String[]> params;
	

    private Map<String, String> _vals = new HashMap<String, String>();

    private StringBuffer _sb = new StringBuffer();
	
	
	
	public  MessageData(String content) {
		try {
			if (content == null|| content.length() <= 0  )
				return;
			 
			// 将字符串转化为XML文档对象
			Document document = DocumentHelper.parseText(content);
			// 获得文档的根节点
			Element root = document.getRootElement();
			// 遍历根节点下所有子节点
			Iterator<?> iter = root.elementIterator();
			
			//利用反射机制，调用set方法
			//获取该实体的元类型
			while(iter.hasNext()){
				Element ele = (Element)iter.next();
				add(ele.getText(),ele.getName());
			}
		} catch (Exception e) {
			System.out.println("xml 格式异常: "+ content);
			e.printStackTrace();
		}
	}

  

    public Map<String, String> getValues() {
        return _vals;
    }

 

   

    
    public void add(String attrVal, String qName)  {

        // 暂存为map集合
        if ("ToUserName".equals(qName)) {
            _vals.put("toUserName", attrVal);
            return;
        }
        if ("FromUserName".equals(qName)) {
            _vals.put("fromUserName", attrVal);
            return;
        }
        if ("CreateTime".equals(qName)) {
            _vals.put("createTime", attrVal);
            return;
        }
        if ("MsgType".equals(qName)) {
            _vals.put("msgType", attrVal);
            return;
        }
        if ("Content".equals(qName)) {
            _vals.put("content", attrVal);
            return;
        }
        if ("PicUrl".equals(qName)) {
            _vals.put("picUrl", attrVal);
            return;
        }
        if ("MediaId".equals(qName)) {
            _vals.put("mediaId", attrVal);
            return;
        }
        if ("Format".equals(qName)) {
            _vals.put("format", attrVal);
            return;
        }
        if ("Recognition".equals(qName)) {
            _vals.put("recognition", attrVal);
            return;
        }
        if ("ThumbMediaId".equals(qName)) {
            _vals.put("thumbMediaId", attrVal);
            return;
        }
        if ("Location_X".equals(qName)) {
            _vals.put("locationX", attrVal);
            return;
        }
        if ("Location_Y".equals(qName)) {
            _vals.put("locationY", attrVal);
            return;
        }
        if ("Scale".equals(qName)) {
            _vals.put("scale", attrVal);
            return;
        }
        if ("Label".equals(qName)) {
            _vals.put("label", attrVal);
            return;
        }
        if ("Title".equals(qName)) {
            _vals.put("title", attrVal);
            return;
        }
        if ("Description".equals(qName)) {
            _vals.put("description", attrVal);
            return;
        }
        if ("Url".equals(qName)) {
            _vals.put("url", attrVal);
            return;
        }
        if ("MsgId".equals(qName) || "MsgID".equals(qName)) {
            _vals.put("msgId", attrVal);
            return;
        }
        if ("Event".equals(qName)) {
            _vals.put("event", attrVal);
            return;
        }
        if ("EventKey".equals(qName)) {
            _vals.put("eventKey", attrVal);
            return;
        }
        if ("ScanType".equals(qName)) {
            _vals.put("scanType", attrVal);
            return;
        }
        if ("ScanResult".equals(qName)) {
            _vals.put("scanResult", attrVal);
            return;
        }
        if ("Poiname".equals(qName)) {
            _vals.put("poiname", attrVal);
            return;
        }
        if ("Count".equals(qName)) {
            _vals.put("count", attrVal);
            return;
        }
        if ("PicMd5Sum".equals(qName)) {
            _sb.append("{\"picMd5Sum\":\"").append(attrVal).append("\"},");
            return;
        }
        if ("PicList".equals(qName)) {
            _sb.deleteCharAt(_sb.lastIndexOf(","));
            _sb.append("]");
            _vals.put("picList", _sb.toString());
            return;
        }
        if ("Status".equals(qName)) {
            _vals.put("status", attrVal);
            return;
        }
        if ("TotalCount".equals(qName)) {
            _vals.put("totalCount", attrVal);
            return;
        }
        if ("FilterCount".equals(qName)) {
            _vals.put("filterCount", attrVal);
            return;
        }
        if ("SentCount".equals(qName)) {
            _vals.put("sentCount", attrVal);
            return;
        }
        if ("ErrorCount".equals(qName)) {
            _vals.put("errorCount", attrVal);
            return;
        }
        if("KfAccount".equals(qName)){
            _vals.put("kfAccount", attrVal);
            return;
        }
        if("FromKfAccount".equals(qName)) {
            _vals.put("fromKfAccount", attrVal);
            return;
        }
        if("ToKfAccount".equals(qName)) {
            _vals.put("toKfAccount", attrVal);
            return;
        }
    }




	public Map<String, String[]> getParams() {
		return params;
	}



	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}
    
    public static void main(String[] args) {
		String xml = "<xml><ToUserName><![CDATA[gh_ec5152700bff]]></ToUserName><FromUserName><![CDATA[oFO4At_HZ40jq2odrEjV5eZ1N1JA]]></FromUserName><CreateTime>1483514967</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[gdd]]></Content><MsgId>6371648266803409255</MsgId></xml>";
		MessageData data = new MessageData(xml);
		System.out.println(data.getValues().get("toUserName"));
    }



	public String getSessionId() {
		return sessionId;
	}



	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
    
    

}
