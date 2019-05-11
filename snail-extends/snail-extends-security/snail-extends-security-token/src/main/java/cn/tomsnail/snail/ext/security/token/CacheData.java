package cn.tomsnail.snail.ext.security.token;

import java.util.Date;


  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年9月23日 下午4:41:53
	* @see 
	*/     
public class CacheData {
	
	private String id;
	
	private String info;
	
	private String token;
	
	private String sign;
	
	private String ticket;
	
	private int expire = 7200;
	
	private Date date = new Date();
	
	public String getToken() {
		return token;
	}

	public int getExpire() {
		return expire;
	}

	public Date getDate() {
		return date;
	}
	
	public String getInfo(){
		return info;
	}
	
	public CacheData(String info){
		this.info = info;
	}
	
	public CacheData(){
		
	}
	
	public CacheData(String info,String ticket){
		this.info = info;
		this.ticket = ticket;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
}
