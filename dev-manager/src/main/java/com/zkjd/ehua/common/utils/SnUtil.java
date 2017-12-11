package com.zkjd.ehua.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import cn.tomsnail.util.configfile.ConfigHelp;

public class SnUtil {
	
	/** 商品订单 */
	public static final String OrderCode_GOODS = "DA";
	/** 采购订单*/
	public static final String OrderCode_PURCHASE = "DS";
	/** 物流订单*/
	public static final String OrderCode_LOGISTICS = "DL";
	/** 团购订单*/
	public static final String OrderCode_GROUPS = "DB";
	/** 报价单*/
	public static final String PriceSheetCode = "Q";
	/** 商品*/
	public static final String GoodsCode = "X";
	/** 委托洽谈*/
	public static final String ACode = "A";
	/** 采购*/
	public static final String PurchaseCode = "P";
	/** 供货*/
	public static final String PurchaseSupplyCode = "S";
	/** 团购*/
	public static final String GroupCode = "T";
	/** 团购报名*/
	public static final String GroupSupplyCode = "G";
	/** 物流*/
	public static final String LogisticCode = "L";
	/** */
	private static final String URL = ConfigHelp.getInstance("config").getLocalConfig("system.sn.url", "http://192.168.169.124:8282");

	
	  
	   /**
		*        通用序列号，多个以,分隔
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年3月17日 上午9:14:02
		* @see 
		* @param         type     SnUtil.OrderCode_GOODS
		* @param 		 length   长度  
		* @return               
		* @status 正常
		* @exception no
		*/
	public static final String getCommonSn(String type,int length){
		return httpget(URL+"/sn/common/"+type+"/"+length);
	}
	
	  
	   /**
		*        商品序列号，多个以,分隔
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年3月17日 上午9:14:09
		* @see 
		* @param    length  批量生成的个数             
		* @return               
		* @status 正常
		* @exception no
		*/
	public static final String getGoodsSn(int length){
		return httpget(URL+"/sn/xgoods/"+length);
	}
	
	private static final String httpget(String url){
		 BufferedReader in = null;  
		  
	        String content = null;  
	        try {  
	            // 定义HttpClient  
	        	DefaultHttpClient client = new DefaultHttpClient();  
	            // 实例化HTTP方法  
	            HttpGet request = new HttpGet();  
	            request.setURI(new URI(url));  
	            HttpResponse response = client.execute(request);  
	  
	            in = new BufferedReader(new InputStreamReader(response.getEntity()  
	                    .getContent()));  
	            StringBuffer sb = new StringBuffer("");  
	            String line = "";  
	            while ((line = in.readLine()) != null) {  
	                sb.append(line);  
	            }  
	            in.close();  
	            content = sb.toString(); 
	            return content;
	        } catch(Exception e){
	        	e.printStackTrace();
	        	return null;
	        }finally{
	        	if (in != null) {  
	                try {  
	                    in.close();// 最后要关闭BufferedReader  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }
	}
	
}
