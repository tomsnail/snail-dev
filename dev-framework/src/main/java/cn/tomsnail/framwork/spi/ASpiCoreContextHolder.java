package cn.tomsnail.framwork.spi;



/**
 *        spi核心内容抽象类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月5日 下午2:54:16
 * @see 
 */
public abstract class ASpiCoreContextHolder implements SpiCoreContextHolder{
		
	@Override
	public  String getSpiClass(String spi,String defaultObj){
		String obj = null;
		if(!isDefaultSPI()){
			obj = getSpiClass(spi);
		}
		if(obj!=null){
			return obj;
		}
		return defaultObj;
	}
	
	
	
}
