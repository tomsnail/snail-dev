package cn.tomsnail.snail.e3.util.pdf.sign;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年10月20日 上午10:59:14
 * @see 
 */
public interface PdfSignService {

	/**
	 *        图片签名
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月20日 上午11:13:43
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String signDataImage(SignModel signModel);
	
	/**
	 *        数据签名
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年10月20日 上午11:13:53
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public String signData(SignModel signModel);
	
}
