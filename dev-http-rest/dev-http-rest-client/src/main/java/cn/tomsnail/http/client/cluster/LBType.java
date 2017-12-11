package cn.tomsnail.http.client.cluster;

/**
 *        负载策略
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月28日 下午3:13:15
 * @see 
 */
public class LBType {

	/**
	 * 轮询
	 */
	public static final String Polling = "Polling";
	/**
	 * 随机，权重计算
	 */
	public static final String Random = "Random";
	/**
	 * Hash计算
	 */
	public static final String Hash = "Hash";
	/**
	 * 最近最少访问
	 */
	public static final String LRU = "LRU";
	
}
