package cn.tomsnail.dubbo.restful.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年9月27日 下午1:54:25
	* @see 
	*/     
public interface RestfulFilter {

	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月27日 下午1:54:31
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public boolean filter( HttpServletRequest request,HttpServletResponse response,Object[] args) throws RestfulFilterException;
	
}
