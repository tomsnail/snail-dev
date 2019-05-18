package cn.tomsnail.core.util.validator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.tomsnail.core.util.validator.exception.ParamValidatorException;
import cn.tomsnail.core.util.validator.security.EscapeSecurityManager;


/**
 *        验证规则
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:09:01
 * @see 
 */
public class ValidatorRule<T> {

	private Map<RuleType, Object> ruleTypeMap = new HashMap<RuleType, Object>();

	public ValidatorRule() {

	}

	public ValidatorRule add(RuleType ruleType) {
		ruleTypeMap.put(ruleType, "");
		return this;
	}

	public ValidatorRule add(RuleType ruleType, Object value) {
		ruleTypeMap.put(ruleType, value);
		return this;
	}
	
	private String getValidFaildMsg(String desc,String t){
		if(StringUtils.isBlank(desc)){
			return t+"";
		}
		return desc;
	}

	public String validator(String key, T value,String desc) throws ParamValidatorException {
		
		if(ruleTypeMap.size()==0){
			return null;
		}

		if (ruleTypeMap.containsKey(RuleType.NotNull)) {
			if (value == null) {
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是空"));
			}
		}
		
		if (ruleTypeMap.containsKey(RuleType.NotNullValue)) {
			if (value == null) {
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是空"));
			}
			if (StringUtils.isBlank((value+"").trim())) {
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是空"));
			}
			if ("null".equals(value+"")) {
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是空"));
			}
			if ("undefined".equals(value+"")) {
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是空"));
			}
		}

		if (value == null) {
			return null;
		}
		
		if(ruleTypeMap.containsKey(RuleType.MustNumber)){
			if (value.getClass().getName().contains("String")) {
				if(!NumberUtils.isCreatable(value+"")){
					throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
							+ ",不是数值类型"));
				}
			}
		}
		
		if (ruleTypeMap.containsKey(RuleType.LIMIT)) {
			String v = value + "";
			if (Integer.parseInt(ruleTypeMap.get(RuleType.LIMIT) + "") == v.trim().length()) {

			} else {
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
						+ ",位数不等于:" + ruleTypeMap.get(RuleType.LIMIT)));
			}

		}
		if (ruleTypeMap.containsKey(RuleType.LIMITMAX)) {
			String v = value + "";
			if (v.trim().length() > Integer.parseInt(String.valueOf(ruleTypeMap.get(RuleType.LIMITMAX)))) {
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value + ",位数大于:"
						+ ruleTypeMap.get(RuleType.LIMITMAX)));
			}
		}
		if (ruleTypeMap.containsKey(RuleType.LIMITMIN)) {
			String v = value + "";
			if (v.trim().length() < Integer.parseInt(String.valueOf(ruleTypeMap.get(RuleType.LIMITMIN)))) {
				throw new ParamValidatorException(getValidFaildMsg(desc,key + key + "的值是" + value + ",位数小于:"
						+ ruleTypeMap.get(RuleType.LIMITMIN)));
			}
		}
		if (ruleTypeMap.containsKey(RuleType.VALUEMAX)) {
			if (value.getClass().getName().contains("String")) {

			} else {
				Double d = 0d;
				try {
					d = Double.valueOf(value + "");
				} catch (NumberFormatException e) {
					throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
							+ ",但需要的是数字类型"));
				}
				if (d > Double.valueOf(ruleTypeMap.get(RuleType.VALUEMAX) + "")) {
					throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
							+ ",值大于:" + ruleTypeMap.get(RuleType.VALUEMAX)));
				}
			}
		}
		if (ruleTypeMap.containsKey(RuleType.VALUEMIN)) {
			if (value.getClass().getName().contains("String")) {

			} else {
				Double d = 0d;
				try {
					d = Double.valueOf(value + "");
				} catch (NumberFormatException e) {
					throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
							+ ",但需要的是数字类型"));
				}
				if (d < Double.valueOf(ruleTypeMap.get(RuleType.VALUEMIN) + "")) {
					throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
							+ ",值小于:" + ruleTypeMap.get(RuleType.VALUEMIN)));
				}
			}
		}
		
		if (ruleTypeMap.containsKey(RuleType.VALUE)) {
			
			String v = value+"";
			if(v.equals(ruleTypeMap.get(RuleType.VALUE))){
				
			}else{
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
						+ ",不等于要求值:" + ruleTypeMap.get(RuleType.VALUE)));
			}
			

		}
		
		if(ruleTypeMap.containsKey(RuleType.EMAIL)){
			String v = value+"";
			if(ValidateUtil.isEmail(v)){
				
			}else{
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
						+ ",不是合法的电子邮箱格式:xxx@xxx.x"));
			}
		}
		
		if(ruleTypeMap.containsKey(RuleType.MOBILE)){
			String v = value+"";
			if(ValidateUtil.isMobile(v)){
				
			}else{
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
						+ ",不是合法的手机号码"));
			}
		}
		
		if(ruleTypeMap.containsKey(RuleType.TELEPHONE)){
			String v = value+"";
			if(ValidateUtil.isTelephone(v)){
				
			}else{
				throw new ParamValidatorException(getValidFaildMsg(desc,key + "的值是" + value
						+ ",不是合法的固定电话号码"));
			}
		}
		if(ruleTypeMap.containsKey(RuleType.ESCAPE)){
			String v = String.valueOf(value);
			return EscapeSecurityManager.replaceSpecialChars(v);
		}
		return null;
		
	}
	
	
public String validator(String key, T value,Map<RuleType,String> descMap) throws ParamValidatorException {
		
		if(ruleTypeMap.size()==0){
			return null;
		}

		if (ruleTypeMap.containsKey(RuleType.NotNull)) {
			if (value == null) {
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.NotNull),key + "的值是空"));
			}
		}
		
		if (ruleTypeMap.containsKey(RuleType.NotNullValue)) {
			if (value == null) {
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.NotNullValue),key + "的值是空"));
			}
			if (StringUtils.isBlank((value+"").trim())) {
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.NotNullValue),key + "的值是空"));
			}
			if ("null".equals(value+"")) {
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.NotNullValue),key + "的值是空"));
			}
			if ("undefined".equals(value+"")) {
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.NotNullValue),key + "的值是空"));
			}
		}

		if (value == null) {
			return null;
		}
		
		if(ruleTypeMap.containsKey(RuleType.MustNumber)){
			if (value.getClass().getName().contains("String")) {
				if(!NumberUtils.isCreatable(value+"")){
					throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.MustNumber),key + "的值是" + value
							+ ",不是数值类型"));
				}
			}
		}
		
		if (ruleTypeMap.containsKey(RuleType.LIMIT)) {
			String v = value + "";
			if (Integer.parseInt(ruleTypeMap.get(RuleType.LIMIT) + "") == v.trim().length()) {

			} else {
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.LIMIT),key + "的值是" + value
						+ ",位数不等于:" + ruleTypeMap.get(RuleType.LIMIT)));
			}

		}
		if (ruleTypeMap.containsKey(RuleType.LIMITMAX)) {
			String v = value + "";
			if (v.trim().length() > Integer.parseInt(ruleTypeMap.get(RuleType.LIMITMAX)+"")) {
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.LIMITMAX),key + "的值是" + value + ",位数大于:"
						+ ruleTypeMap.get(RuleType.LIMITMAX)));
			}
		}
		if (ruleTypeMap.containsKey(RuleType.LIMITMIN)) {
			String v = value + "";
			if (v.trim().length() < Integer.parseInt(ruleTypeMap.get(RuleType.LIMITMIN)
					+ "")) {
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.LIMITMIN),key + key + "的值是" + value + ",位数小于:"
						+ ruleTypeMap.get(RuleType.LIMITMIN)));
			}
		}
		if (ruleTypeMap.containsKey(RuleType.VALUEMAX)) {
			if (value.getClass().getName().contains("String")) {

			} else {
				Double d = 0d;
				try {
					d = Double.valueOf(value + "");
				} catch (NumberFormatException e) {
					throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.VALUEMAX),key + "的值是" + value
							+ ",但需要的是数字类型"));
				}
				if (d > Double.valueOf(ruleTypeMap.get(RuleType.VALUEMAX) + "")) {
					throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.VALUEMAX),key + "的值是" + value
							+ ",值大于:" + ruleTypeMap.get(RuleType.VALUEMAX)));
				}
			}
		}
		if (ruleTypeMap.containsKey(RuleType.VALUEMIN)) {
			if (value.getClass().getName().contains("String")) {

			} else {
				Double d = 0d;
				try {
					d = Double.valueOf(value + "");
				} catch (NumberFormatException e) {
					throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.VALUEMIN),key + "的值是" + value
							+ ",但需要的是数字类型"));
				}
				if (d < Double.valueOf(ruleTypeMap.get(RuleType.VALUEMIN) + "")) {
					throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.VALUEMIN),key + "的值是" + value
							+ ",值小于:" + ruleTypeMap.get(RuleType.VALUEMIN)));
				}
			}
		}
		
		if (ruleTypeMap.containsKey(RuleType.VALUE)) {
			
			String v = value+"";
			if(v.equals(ruleTypeMap.get(RuleType.VALUE))){
				
			}else{
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.VALUE),key + "的值是" + value
						+ ",不等于要求值:" + ruleTypeMap.get(RuleType.VALUE)));
			}
			

		}
		
		if(ruleTypeMap.containsKey(RuleType.EMAIL)){
			String v = value+"";
			if(ValidateUtil.isEmail(v)){
				
			}else{
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.EMAIL),key + "的值是" + value
						+ ",不是合法的电子邮箱格式:xxx@xxx.x"));
			}
		}
		
		if(ruleTypeMap.containsKey(RuleType.MOBILE)){
			String v = value+"";
			if(ValidateUtil.isMobile(v)){
				
			}else{
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.MOBILE),key + "的值是" + value
						+ ",不是合法的手机号码"));
			}
		}
		
		if(ruleTypeMap.containsKey(RuleType.TELEPHONE)){
			String v = value+"";
			if(ValidateUtil.isTelephone(v)){
				
			}else{
				throw new ParamValidatorException(getValidFaildMsg(descMap.get(RuleType.TELEPHONE),key + "的值是" + value
						+ ",不是合法的固定电话号码"));
			}
		}
		if(ruleTypeMap.containsKey(RuleType.ESCAPE)){
			String v = value+"";
			return EscapeSecurityManager.replaceSpecialChars(v);
		}
		return null;
		
	}

	public static void main(String[] args) {
		String t = "_12324";
		System.out.println(t.contains("_"));
	}
	
}
