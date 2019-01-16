package cn.tomsnail.uuid;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import cn.tomsnail.util.configfile.ConfigHelp;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年2月22日 上午10:09:36
	* @see 
	*/     
public class UuidUtil {
	
	private static final long workId = Long.valueOf(ConfigHelp.getInstance("uuid").getLocalConfig("uuid.workId", "1"));
	private static final long dcId = Long.valueOf(ConfigHelp.getInstance("uuid").getLocalConfig("uuid.dcId", "2"));
	private static final IdWorker ID_WORKER = new IdWorker(workId, dcId);
	
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',  
        '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',  
        'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',  
        'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',  
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',  
        'Z' };  

	  
	   /**
		*        36位UUID
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年2月22日 上午10:15:23
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	
	  
	   /**
		*        去除-的标准UUID 32位
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年2月22日 上午10:17:53
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static String getNUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	  
	   /**
		*        22位UUID
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年2月22日 上午10:15:13
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static String getShortUUID(){
		return GenerateShortUUID.next();
	}
	  
	   /**
		*        返回long型18位分布式的UUID
		*        须在uuid.per*文件中配置uuid.workId和uuid.dcId
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年2月22日 上午10:39:55
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static long getDUUID(){
		return ID_WORKER.nextId();
	}
	
	private static String digits(long val, int digits) {  
	    long hi = 1L << (digits * 4);  
	    return Numbers.toString(hi | (val & (hi - 1)), Numbers.MAX_RADIX)  
	            .substring(1);  
	}  
	
	  
	   /**
		*        19位UUID
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年2月22日 上午10:47:57
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
		
	public static String uuid() {  
	    UUID uuid = UUID.randomUUID();  
	    StringBuilder sb = new StringBuilder();  
	    sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));  
	    sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));  
	    sb.append(digits(uuid.getMostSignificantBits(), 4));  
	    sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));  
	    sb.append(digits(uuid.getLeastSignificantBits(), 12));  
	    return sb.toString();  
	}  
	
	  
	   /**
		*        自定义位数UUID，建议大于12位，12位概率为1-e^(-(10^9)^2/62^12)=0.00030990773 
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年2月22日 上午10:48:13
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static String uuid(int size) {  
		ThreadLocalRandom random =  ThreadLocalRandom.current(); 
	    char[] cs = new char[size];  
	    for (int i = 0; i < cs.length; i++) {  
	        cs[i] = digits[random.nextInt(digits.length)];  
	    }  
	    return new String(cs);  
	}  
	
	
	
	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		Map<String,String> map = new HashMap<String, String>();
		int count = 0;
		System.out.println((uuid(12)+"").length());
 		for(int i=0;i<1000000;i++){
			String id =uuid(12)+"";
			if(map.containsKey(id)){
				count++;
			}else{
				map.put(id, "");
			}
		}
		System.out.println(System.currentTimeMillis()-t);
		System.out.println(count);
	}
}
