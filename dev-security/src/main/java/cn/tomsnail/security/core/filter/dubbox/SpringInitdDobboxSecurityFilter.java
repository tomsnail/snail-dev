package cn.tomsnail.security.core.filter.dubbox;

import cn.tomsnail.security.core.SpringContextUtil;
import cn.tomsnail.security.core.filter.IFilter;

/**
 *        sping方式启动dubbox安全过滤
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月9日 上午10:03:10
 * @see 
 */
public class SpringInitdDobboxSecurityFilter extends DefaultDobboxSecurityFilter{

	public SpringInitdDobboxSecurityFilter(){
		init();
	}
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年8月9日 上午10:03:07
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void init(){
		initFilter();
	}
	
	/**
	 *        
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年8月9日 上午10:03:13
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public void initFilter(){
		if(SpringContextUtil.containsBean("SecurityFilter")){
			IFilter filter = (IFilter) SpringContextUtil.getBean("SecurityFilter", IFilter.class);
			this.setFilter(filter);
		}
	}
	
}

