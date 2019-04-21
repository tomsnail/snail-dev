package cn.tomsnail.snail.core.util.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    //获取当前时间之前或之后几小时 hour
    public static String getTimeByHour(int hour) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    //获取当前时间之前或之后几分钟 minute
    public static String getTimeByMinute(int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, minute);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    //获取当前时间前30天
    public static String getTimeByMonth(int day) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, day);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }
    
    public static String getYear(Calendar calendar){
    	 String year = calendar.get(Calendar.YEAR)+"";
    	 return year;
         
    }
    public static String getMonth(Calendar calendar){
    	 String month = calendar.get(Calendar.MONTH)+1+"";
         if(month.length()==1){
        	 month="0"+month;
         }
         return month;
    }
    public static String getDay(Calendar calendar){
    	  String day = calendar.get(Calendar.DATE)+"";
    	  if(day.length()==1){
         	 day="0"+day;
          }
    	  return day;
    }
    public static String getChangeDateFormat(SimpleDateFormat sdf_ori,SimpleDateFormat sdf_des,String date_ori_str){
    	Date date = null;
    	String date_change = "";
    	try {
    		date = sdf_ori.parse(date_ori_str);
    		date_change =sdf_des.format(date);
		} catch (ParseException e) {
			logger.error("",e);
		}
		return date_change;
    }
    
    public static void main(String[] args) {
    	Timestamp timestamp = Timestamp.valueOf("2018-12-23 00:00:00");
    	Timestamp now = Timestamp.valueOf("2018-09-20 00:00:00");
    	if(now.after(timestamp)) {
			System.out.println("111111111111111");
		}else {
			System.out.println("22222222222");
		}
	}
}
