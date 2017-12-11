package cn.tomsnail.security.core.url;

import java.util.List;

/**
 *        本地过滤
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月9日 下午3:25:46
 * @see 
 */
public class LocalUrlFilter implements IUrlFilter{

	private List<String> includes;
	
	private List<String> excludes;
	
	@Override
	public boolean isPass(String url) {
		if(excludes==null&&includes==null){
			return true;
		}else if(excludes==null){
			if(UrlMatch.isMatch(includes,url)){
				return false;
			}else{
				return true;
			}
		}else if(includes==null){
			if(UrlMatch.isMatch(excludes,url)){
				return true;
			}else{
				return false;
			}
		}else{//both not is null
			if(UrlMatch.isMatch(includes,url)){
				return false;
			}else{
				if(UrlMatch.isMatch(excludes,url)){
					return true;
				}
			}
		}
		return false;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}
	
	

}
