package cn.tomsnail.snail.e3.tx.weixin.util.core;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;


public class MessageBody extends DefaultHandler2 {
	
	private static final SAXParserFactory factory = SAXParserFactory.newInstance();
	
	private static SAXParser xmlParser = null;
	
	static{
		try {
			xmlParser = factory.newSAXParser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MessageBody getMessageBody(InputStream is) throws Exception{
		MessageBody body = new MessageBody();
		xmlParser.parse(is, body);
		return body;
	}
	
	
	
	private Map<String, String[]> params;
	
	  // 节点属性值
    private String attrVal;

    private Map<String, String> _vals = new HashMap<String, String>();

    private StringBuffer _sb = new StringBuffer();
	
	
	
	private MessageBody() {
		
	}

  

    public Map<String, String> getValues() {
        return _vals;
    }

    @Override
    public void startDocument() throws SAXException {
        _vals.clear();
        _sb.setLength(0);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if ("PicList".equals(qName)) {
            _sb.append("[");
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

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

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.attrVal = new String(ch, start, length);
    }



	public Map<String, String[]> getParams() {
		return params;
	}



	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}
    
    

}
