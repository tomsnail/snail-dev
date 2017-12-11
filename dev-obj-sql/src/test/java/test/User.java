package test;
import cn.tomsnail.objsql.annotation.ObjectSQL;


  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 上午11:09:28
	* @see 
	*/     
@ObjectSQL 
public class User {

	private String id;
	
	private String name;
	
	private String nickname;
	
	private int age;
	
	private int sex;
	

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


	
	public static void main(String[] args) {
		User user = new User();
		System.out.println(user.getClass().getName());
	}
}
