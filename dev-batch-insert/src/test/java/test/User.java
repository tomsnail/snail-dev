package test;

import cn.tomsnail.batch.insert.obj.annotation.BatchModel;


  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 上午11:09:28
	* @see 
	*/     
@BatchModel
public class User {

	private String id = "3";
	
	private String name= System.currentTimeMillis()+"name";
	
	private String nickname= System.currentTimeMillis()+"nickname";
	
	private int age = 1;
	
	private int sex = 1;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

}
