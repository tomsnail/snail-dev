package cn.tomsnail.security.limit.bucket;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月27日 下午2:32:56
 * @see 
 */
public interface IRequestLimit {

	public  boolean canPass();
	
}
