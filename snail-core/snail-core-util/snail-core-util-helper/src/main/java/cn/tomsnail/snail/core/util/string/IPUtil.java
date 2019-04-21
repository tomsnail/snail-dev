package cn.tomsnail.snail.core.util.string;


public class IPUtil {
	
	public static String getIPorDomain(String url) {
       return url.split("//")[1].split("/")[0].split(":")[0];
    }
	
	public static String getPort(String url) {
		String ts[] = url.split("//")[1].split("/")[0].split(":");
		if(ts.length==1){
			if(url.startsWith("https:")){
				return "443";
			}else if(url.startsWith("http:")){
				return "80";
			}else{
				return "";
			}
		}else{
			return ts[1];

		}
    }
	
	public static void main(String[] args) {
		System.out.println(getPort("http://www.baidu.com/zkr/special"));
	}

}
