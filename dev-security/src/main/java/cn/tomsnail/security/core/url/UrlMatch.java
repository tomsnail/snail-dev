package cn.tomsnail.security.core.url;

import java.util.ArrayList;
import java.util.List;

/**
 *        url匹配
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月9日 下午3:33:18
 * @see 
 */
public class UrlMatch {

	public static boolean isMatch(List<String> urls,String url){
		
		if(urls==null||url==null){
			return false;
		}
		for(String _url:urls){
			if(_url.contains("*")){
				if(_url.startsWith("*")){
					if(url.endsWith(_url.replace("*", ""))){
						return true;
					}
				}
				if(_url.endsWith("*")){
					if(url.startsWith(_url.replace("*", ""))){
						return true;
					}
				}
			}else{
				if(_url.equals(url)){
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		urls.add("*.do");
		urls.add("*.js");
		urls.add("*.css");
		urls.add("*.html");
		urls.add("/system/*");
		urls.add("/system1/get");
		String url = "/system1/get1";
		System.out.println(UrlMatch.isMatch(urls, url));
	}
	
}
