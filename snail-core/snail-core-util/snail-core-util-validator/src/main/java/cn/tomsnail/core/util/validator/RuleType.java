package cn.tomsnail.core.util.validator;

/**
 *        规则类型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:08:20
 * @see 
 */
public enum RuleType {
	
	/**
	 * 必填项
	 */
	NotNull,
	/**
	 * 不能为空值
	 */
	NotNullValue,
	/**
	 * 必须是数值类型
	 */
	MustNumber,
	/**
	 * 必须是数值类型
	 */
	MustInt,
	/**
	 * 必须是数值类型
	 */
	MustFloat,
	/**
	 * 值等于
	 */
	VALUE,
	/**
	 * 值上限
	 */
	VALUEMAX,
	/**
	 * 值下限
	 */
	VALUEMIN,
	/**
	 * 位数限制
	 */
	LIMIT,
	/**
	 * 位数上限
	 */
	LIMITMAX,
	/**
	 * 位数下限
	 */
	LIMITMIN,
	/**
	 * XSS
	 */
	XSS,
	/**
	 * SQL
	 */
	SQL,
	/**
	 * EMAIL
	 */
	EMAIL,
	/**
	 * MOBILE
	 */
	MOBILE,
	/**
	 * TELEPHONE
	 */
	TELEPHONE,
	/**
	 * escape 
	 */
	ESCAPE;
	
}
