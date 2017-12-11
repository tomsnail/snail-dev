package cn.tomsnail.framwork.http;


/**
 *        通用消息
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:02:40
 * @see 
 */
public class CommonMessage
{
  public static final String FAILED = "-1";
	/**成功*/
  public static final String SUCCESS = "0";
  /**未授权*/
  public static final String UNAUTHORIZED = "1";
  /**参数错误*/
  public static final String PARAMETER_ERROR = "2";
  /**数据库连接超时或者异常*/
  public static final String DB_OR_ERROR = "3"; 
  /**获取对象为空*/
  public static final String OBJECT_IS_NULL = "4"; 
  /**查询结果为空*/
  public static final String RESULTSET_EMPTY = "5";
  /**业务失败*/
  public static final String BUSINESS_FAILED = "-2";
  /**业务繁忙*/
  public static final String BUSY_BUSINESS = "901";
  /**业务繁忙*/
  public static final String BUSY_BUSINESS_902 = "902";

}