package cn.tomsnail.http.client.cluster.fail;

/**
 *        失败策略类型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月28日 下午2:52:26
 * @see 
 */
public class FailType {

	/**
	 * 失败重试
	 */
	public static final String Retry = "Retry";

	/**
	 * 快速失败
	 */
	public static final String FastFail = "FastFail";
	
}
