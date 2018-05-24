package cn.tomsnail.framwork.validator.annotation;

import java.util.Date;

import cn.tomsnail.framwork.validator.RuleType;

@BeanValidator(isAllValidator=false)
public class TestBean {

	@FieldValidator(onlyToBean=false,rules={RuleType.NotNullValue},values={""})
	private String id;
	
	@FieldValidator(onlyToBean=true)
	private String email;
	
	@FieldValidator(onlyToBean=true)
	private String phone;
	
	@FieldValidator(onlyToBean=true)
	private long money;
	
	private Date date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
