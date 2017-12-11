/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.tomsnail.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	public static final SimpleDateFormat longSdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat logSdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static final SimpleDateFormat yyyymmddSdf = new SimpleDateFormat("yyyyMMdd"); 
	public static final SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd"); 
	public static final  DateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	public static final  DateFormat formatTimeShort = new SimpleDateFormat("yyyy-MM-dd HH:mm");  

	/***
	 * @Description: 获取当前时间  格式：yyyyMMddHHmmssSSS
	 * @author zhanggeliang
	 * @param @return
	 * @data 2015-10-11 下午20:34:05
	 */
	public static String getLogDate() {
		return logSdf.format(new Date());
	}
	/**
     * @discription 字符串转date
     * @author zhanghl       
     * @date 2015年9月18日 上午11:06:30     
     * @param str
     * @return
	 */
	public static Date stringToDate(String str,SimpleDateFormat format) {  
        Date date = null;  
        try {  
            date = format.parse(str);   
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        date = java.sql.Date.valueOf(str);  
        return date;  
    } 
	/**
     * @discription 字符串转date
     * @author zhanghl       
     * @date 2015年9月18日 上午11:06:30     
     * @param str
     * @return
	 */
	public static Date stringToDate(String str) {  
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = null;  
        try {  
            date = format.parse(str);   
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        date = java.sql.Date.valueOf(str);  
        return date;  
    } 
	   
	   /**
		*        日期转字符串
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月12日 下午4:01:35
		* @see 
		* @param    date
		* @param    type     0:SHORT 1:MEDIUM 2:FULL 其他:yyyy-MM-dd         
		* @return               
		* @status 正常
		* @exception no
		*/
	public static String dateToString(Date date, int type) {  
	        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	        switch(type){
	        	case 0:
	        		format = DateFormat.getDateInstance(DateFormat.SHORT);  
	        		break;
	        	case 1:
	        		format = DateFormat.getDateInstance(DateFormat.MEDIUM);  
	        		break;
	        	case 2:
	        		format = DateFormat.getDateInstance(DateFormat.FULL);  
	        		break;
	        	default:
	        		format = new SimpleDateFormat("yyyy-MM-dd");  
	        		break;
	        }
	        return  format.format(date);
	    }  
      
       /**
    	*        获取当天日期
    	* @methodauthor yangsong
    	* @methodversion 0.0.1
    	* @date 2017年9月12日 下午4:01:44
    	* @see 
    	* @param                   
    	* @return               
    	* @status 正常
    	* @exception no
    	*/
    public static String getCurrentDay(SimpleDateFormat sdf){
    	 Date dt=new Date();
    	 return sdf.format(dt);
    }
      
       /**
    	*        获取上个月日期
    	* @methodauthor yangsong
    	* @methodversion 0.0.1
    	* @date 2017年9月12日 下午4:01:52
    	* @see 
    	* @param                   
    	* @return               
    	* @status 正常
    	* @exception no
    	*/  	
    public static String getLast1Month(SimpleDateFormat sdf){
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.MONTH, -1);
    	String preMonth = sdf.format(c.getTime());
    	return preMonth;
    }
      
       /**
    	*        获取上上个月日期
    	* @methodauthor yangsong
    	* @methodversion 0.0.1
    	* @date 2017年9月12日 下午4:01:59
    	* @see 
    	* @param                   
    	* @return               
    	* @status 正常
    	* @exception no
    	*/
    public static String getLast2Month(SimpleDateFormat sdf){
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.MONTH, -2);
    	String preMonth = sdf.format(c.getTime());
    	return preMonth;
    }
    
      
       /**
    	*        时间戳(秒)转时间
    	* @methodauthor yangsong
    	* @methodversion 0.0.1
    	* @date 2017年9月12日 下午4:02:06
    	* @see 
    	* @param                   
    	* @return               
    	* @status 正常
    	* @exception no
    	*/
    public static String dfLongToString(Long time) {  
    	if(time == null || time == 0){
    		return "";
    	}
    	Date date = new Date(time * 1000l);  
		return formatTime.format(date);
		
    } 
      
       /**
    	*        时间戳(秒)转时间字符串
    	* @methodauthor yangsong
    	* @methodversion 0.0.1
    	* @date 2017年9月12日 下午4:02:14
    	* @see 
    	* @param                   
    	* @return               
    	* @status 正常
    	* @exception no
    	*/
    public static String timeToDateFormat(String str) { 
    	if(StringUtils.isBlank(str) || "0".equals(str)){
    		return "";
    	}
    	Date date = null;
        try {
			date = new Date(Long.valueOf(str)*1000l);  
			return formatTime.format(date);
		} catch (Exception e) {
			return "";
		}  
    } 
      
       /**
    	*         时间戳(秒)转指定格式时间字符串
    	* @methodauthor yangsong
    	* @methodversion 0.0.1
    	* @date 2017年9月12日 下午4:02:21
    	* @see 
    	* @param                   
    	* @return               
    	* @status 正常
    	* @exception no
    	*/
    public static String timeToDateFormat(String str,DateFormat df) { 
    	if(StringUtils.isBlank(str) || "0".equals(str)){
    		return "";
    	}
    	Date date = null;
        try {
			date = new Date(Long.valueOf(str)*1000l);  
			return df.format(date);
		} catch (Exception e) {
			return "";
		}  
    } 
    
    
       /**
    	*      得到几天前的时间  
    	* @methodauthor yangsong
    	* @methodversion 0.0.1
    	* @date 2017年9月12日 下午4:02:29
    	* @see 
    	* @param                   
    	* @return               
    	* @status 正常
    	* @exception no
    	*/
    public static Date getDateBefore(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
        return now.getTime();  
    }  
  
       /**
    	*        得到几天后的时间 
    	* @methodauthor yangsong
    	* @methodversion 0.0.1
    	* @date 2017年9月12日 下午4:02:36
    	* @see 
    	* @param                   
    	* @return               
    	* @status 正常
    	* @exception no
    	*/
    public static Date getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return now.getTime();  
    } 
}
