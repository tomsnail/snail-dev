package cn.tomsnail.snail.e3.tx.weixin.util.common;

import java.lang.reflect.Field;
import java.util.Iterator;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.tomsnail.snail.e3.tx.weixin.util.payment.PaymentResult;



public class XmlParseUtil {

	
	
	
	
	public static <T> T getObject(String content,T t) throws Exception{
		try {
			if (content == null|| content.length() <= 0  )
				return null;
			 
			// 将字符串转化为XML文档对象
			Document document = DocumentHelper.parseText(content);
			// 获得文档的根节点
			Element root = document.getRootElement();
			// 遍历根节点下所有子节点
			Iterator<?> iter = root.elementIterator();
			
			//利用反射机制，调用set方法
			//获取该实体的元类型
			Class c = t.getClass();
			while(iter.hasNext()){
				Element ele = (Element)iter.next();
				//获取set方法中的参数字段（实体类的属性）
				Field field = c.getDeclaredField(ele.getName());
				field.setAccessible(true);
				field.set(t, ele.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("xml 格式异常: "+ content);
			e.printStackTrace();
		}
		return t;
	}
	
	public static void main(String[] args) {
		PaymentResult paymentResult = new PaymentResult();
		try {
			System.out.println(getObject("<xml><appid>wx2421b1c4370ec43b</appid></xml>",paymentResult).getAppid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
