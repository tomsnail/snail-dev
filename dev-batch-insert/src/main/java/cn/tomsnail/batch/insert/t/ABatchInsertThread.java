package cn.tomsnail.batch.insert.t;

import java.sql.Connection;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public abstract class ABatchInsertThread implements IInsertThread{

	protected String url;
	
	protected String username;
	
	protected String password;
	
	protected PreparedStatement pst = null;
	
	protected Statement st = null;
	
	protected Connection con = null;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ABatchInsertThread(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	
	
}
