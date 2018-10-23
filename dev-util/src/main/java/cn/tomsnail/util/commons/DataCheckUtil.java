package cn.tomsnail.util.commons;

import java.util.regex.Pattern;

import cn.tomsnail.framwork.validator.ValidateUtil;
import cn.tomsnail.util.string.StringUtils;

public class DataCheckUtil {

	public static boolean checkUrl(String url) {
        if (StringUtils.isBlank(url)){
           return false;
        }
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }
	
	public static boolean checkPhone(String phone) {
       if(StringUtils.isBlank(phone)){
    	   return false;
       }
       
       return ValidateUtil.isMobile(phone);
    }
	
	
	public static void main(String[] args) {
		String url = "https://www.baidu.coom";
		System.out.println(checkUrl(url));
	}
}
