package cn.tomsnail.snail.ext.security.authority.filter.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年9月27日 下午1:53:53
	* @see 
	*/     
public interface Authority {
	
	  
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月27日 下午1:53:58
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public void doAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response,String info,int expire);
	
	  
	public void doAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response,String info,int expire,int authoritySignatureTypePolicy);
	   /**
		*        
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月27日 下午1:54:07
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public void clearAuthority(HttpServletRequest httpServletRequest,HttpServletResponse response,String info);

}
