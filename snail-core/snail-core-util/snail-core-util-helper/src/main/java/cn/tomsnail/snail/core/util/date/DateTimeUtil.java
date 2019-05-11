package cn.tomsnail.snail.core.util.date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;


  
   /**
	*        JDK8 日期时间函数
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年3月4日 上午10:48:29
	* @see 
	*/      
public class DateTimeUtil {
	
	
	
	static {
		try {
			Class.forName("java.time.LocalDateTime");
		} catch (ClassNotFoundException e) {
			
		}
	}
	
	   /**
		*        标准日期格式：yyyy-MM-dd HH:mm:ss
		* @author yangsong
		* @version 0.0.1
		* @status 正常
		* @date 2017年3月4日 上午11:16:22
		* @see 
		*/      
	public static final DateTimeFormatter STANTD_FORMART= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	   /**
		*         标准日期格式：yyyy-MM-dd
		* @author yangsong
		* @version 0.0.1
		* @status 正常
		* @date 2017年3月4日 上午11:16:27
		* @see 
		*/      
	public static final DateTimeFormatter YMD_FORMART= DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	   /**
		*         标准日期格式：HH:mm:ss
		* @author yangsong
		* @version 0.0.1
		* @status 正常
		* @date 2017年3月4日 上午11:16:33
		* @see 
		*/      
	public static final DateTimeFormatter HMS_FORMART= DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	   /**
		*        东八区
		* @author yangsong
		* @version 0.0.1
		* @status 正常
		* @date 2017年3月4日 上午11:16:37
		* @see 
		*/      
	private static final ZoneId defaultZoneId = ZoneId.of("Asia/Shanghai");
	
	  
	   /**
		*        当前时间的标准格式字符串：yyyy-MM-dd HH:mm:ss
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年3月4日 上午11:16:42
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static String getNowDateTimeStr(){
		return LocalDateTime.now().format(STANTD_FORMART);
	}
	
	 /**
	*        当前时间
	* @methodauthor yangsong
	* @methodversion 0.0.1
	* @date 2017年3月4日 上午11:16:42
	* @see 
	* @param                   
	* @return               
	* @status 正常
	* @exception no
	*/
	public static LocalDateTime getNow(){
		return LocalDateTime.now();
	}
	
	 /**
	*        当前时间戳的秒
	* @methodauthor yangsong
	* @methodversion 0.0.1
	* @date 2017年3月4日 上午11:16:42
	* @see 
	* @param                   
	* @return               
	* @status 正常
	* @exception no
	*/
	public static long getNowTimestmpe(){
		return Instant.now().toEpochMilli();
	}
	
	 /**
	*        自定义的当前时间格式
	* @methodauthor yangsong
	* @methodversion 0.0.1
	* @date 2017年3月4日 上午11:16:42
	* @see 
	* @param                   
	* @return               
	* @status 正常
	* @exception no
	*/
	public static String getNowDateTimeStr(DateTimeFormatter formart){
		return LocalDateTime.now().format(formart);
	}
	
	 /**
	*        对原java.util.Date的自定义格式字符串
	* @methodauthor yangsong
	* @methodversion 0.0.1
	* @date 2017年3月4日 上午11:16:42
	* @see 
	* @param                   
	* @return               
	* @status 正常
	* @exception no
	*/
	public static String getDateTimeStr(Date d,DateTimeFormatter formart){
		return d.toInstant().atZone(defaultZoneId).toLocalDateTime().format(formart);
	}
	
	 /**
	*        对时间的自定义格式
	* @methodauthor yangsong
	* @methodversion 0.0.1
	* @date 2017年3月4日 上午11:16:42
	* @see 
	* @param                   
	* @return               
	* @status 正常
	* @exception no
	*/
	public static String getDateTimeStr(LocalDateTime d,DateTimeFormatter formart){
		return d.format(formart);
	}
	
	 /**
	*        原java.util.Date的标准时间格式：yyyy-MM-dd HH:mm:ss
	* @methodauthor yangsong
	* @methodversion 0.0.1
	* @date 2017年3月4日 上午11:16:42
	* @see 
	* @param                   
	* @return               
	* @status 正常
	* @exception no
	*/
	public static String getDateTimeStr(Date d){
		return d.toInstant().atZone(defaultZoneId).toLocalDateTime().format(STANTD_FORMART);
	}
	
	 /**
	*         原java.util.Date的自定义时间格式字符串
	* @methodauthor yangsong
	* @methodversion 0.0.1
	* @date 2017年3月4日 上午11:16:42
	* @see 
	* @param                   
	* @return               
	* @status 正常
	* @exception no
	*/
	public static String getDateTimeStr(Date d,String formart){
		return d.toInstant().atZone(defaultZoneId).toLocalDateTime().format(DateTimeFormatter.ofPattern(formart));
	}
	
	 /**
	*        将制定格式时间字符串转为时间对象
	* @methodauthor yangsong
	* @methodversion 0.0.1
	* @date 2017年3月4日 上午11:16:42
	* @see 
	* @param                   
	* @return               
	* @status 正常
	* @exception no
	*/
	public static LocalDateTime getDateTime(String dtStr,DateTimeFormatter formart){
		return LocalDateTime.parse(dtStr, formart);
	}
	
	  
	   /**
		*        时间字符串转为原java.util.Date
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年3月4日 上午11:31:31
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static Date getDate(String dtStr,DateTimeFormatter formart){
		
		if(YMD_FORMART==formart){
			LocalDate localDate = LocalDate.parse(dtStr, formart);
			return new Date(localDate.atTime(0, 0, 0).toInstant(ZoneOffset.UTC.ofHours(8)).toEpochMilli());
		}
		
		LocalDateTime localDateTime = LocalDateTime.parse(dtStr, formart);
		return new Date(localDateTime.toInstant(ZoneOffset.UTC.ofHours(8)).toEpochMilli());
		
	}
	
	public static Date getDate(String dtStr,String formart){
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern(formart);
		
		if(formart.contains("H")||formart.contains("m")||formart.contains("s")||formart.contains("h")){
			LocalDateTime localDateTime = LocalDateTime.parse(dtStr, f);
			return new Date(localDateTime.toInstant(ZoneOffset.UTC.ofHours(8)).toEpochMilli());
		}else{
			LocalDate localDate = LocalDate.parse(dtStr, f);
			return new Date(localDate.atTime(0, 0, 0).toInstant(ZoneOffset.UTC.ofHours(8)).toEpochMilli());
		}

		
	}

	public static void main(String[] args) {
		System.out.println(ZoneOffset.UTC.ofHours(8));
		System.out.println(getDate("2017-03-04 23:00","yyyy-MM-dd HH:mm"));
	}
	
}
