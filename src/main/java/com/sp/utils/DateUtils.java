package com.sp.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.ibatis.annotations.Case;

public class DateUtils {

	public final static String DATE_YYYYMMDD = "yyyyMMdd";

	public final static String DATE_YYYY_MM_DD = "yyyy-MM-dd";
	
	public final static String DATE_YYYY_MM = "yyyy-MM";

	public final static String DATE_HHMISS = "HHmmss";

	public final static String DATE_HHMISS_F = "HH:mm:ss";

	public final static String DATE_YYYYMMDDHHMISS = "yyyyMMddHHmmss";
	public final static String DATE_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmsssss";

	public final static String DATE_YYYYMMDDHHMISS_F = "yyyy-MM-dd HH:mm:ss";
	
	public final static String DATE_CST = "EEE MMM dd HH:mm:ss 'CST' yyyy";
	
	public final static String DATE_UTC = "EEE MMM dd HH:mm:ss 'UTC 0800' yyyy";
	
	public final static String DATE_MILLI1="yyyy-MM-dd HH:mm:ss.s";
	
	public final static String DATE_MILLI2="yyyy-MM-dd HH:mm:ss.ss";
	
	public final static String DATE_MILLI3="yyyy-MM-dd HH:mm:ss.sss";
	
	/**
	 * 一天有多少毫秒
	 */
	public final static long MILLISECONDS_DAY=24*3600*1000;

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            日期对象
	 * @param format
	 *            格式，为空时默认为yyyy-MM-dd格式
	 * @return 格式化后的日期字符串
	 */
	public static String format(Date date, String format) {
		String res = "";
		if (date == null) {
			date = new Date();
		}
		if (format==null) {
			format = DateUtils.DATE_YYYY_MM_DD;
		}
		try {
			res = (new SimpleDateFormat(format)).format(date);
		} catch (Exception e) {
			res = format(null, null);
		}
		return res;
	}

	/**
	 * 格式化日期字符串yyyyMMdd
	 * 
	 * @param source
	 *            日期字符串对象
	 * @return 格式化后的日期字符串
	 */
	public static String formatToYmd(String source) {
		String res = "";
		if (source!=null&&source.trim().length()>0) {
			try {
				Date date = parseToDate(source);
				if (date != null) {
					res = (new SimpleDateFormat(DateUtils.DATE_YYYYMMDD)).format(date);
				}
			} catch (Exception e) {
				res = source;
				System.err.println("格式化日期字符串(yyyyMMdd)失败：" + e.getMessage());
			}
		}
		return res;
	}

	/**
	 * 格式化时间字符串HHmmss
	 * 
	 * @param source
	 *            时间字符串对象
	 * @return 格式化后的日期字符串
	 */
	public static String formatToHms(String source) {
		String res = "";
		if (source!=null&&source.trim().length()>0) {
			try {
				Date date = parseToDate(source);
				if (date != null) {
					res = (new SimpleDateFormat(DateUtils.DATE_HHMISS)).format(date);
				}
			} catch (Exception e) {
				res = source;
				System.err.println("格式化时间字符串(HHmmss)失败：" + e.getMessage());
			}
		}
		return res;
	}

	/**
	 * 格式化日期字符串yyyy-MM-dd
	 * 
	 * @param source
	 *            日期字符串对象
	 * @return 格式化后的日期字符串
	 */
	public static String formatToY_m_d(String source) {
		String res = "";
		if (source!=null&&source.trim().length()>0) {
			try {
				Date date = parseToDate(source);
				if (date != null) {
					res = (new SimpleDateFormat(DateUtils.DATE_YYYY_MM_DD)).format(date);
				}
			} catch (Exception e) {
				res = source;
				System.err.println("格式化日期字符串(yyyy-MM-dd)失败：" + e.getMessage());
			}
		}
		return res;
	}

	/**
	 * 格式化时间字符串HH:mm:ss
	 * 
	 * @param source
	 *            时间字符串对象
	 * @return 格式化后的日期字符串
	 */
	public static String formatToH_m_s(String source) {
		String res = "";
		if (source!=null&&source.trim().length()>0) {
			try {
				Date date = parseToDate(source);
				if (date != null) {
					res = (new SimpleDateFormat(DateUtils.DATE_HHMISS_F)).format(date);
				}
			} catch (Exception e) {
				res = source;
				System.err.println("格式化时间字符串(HH:mm:ss)失败：" + e.getMessage());
			}
		}
		return res;
	}

	/**
	 * 将字符串解析为日期对象
	 * 
	 * @param source
	 * @return
	 */
	public static Date parseToDate(String source) {
		Date date = null;
		if (source!=null&&source.trim().length()>0) {
			try {
				String format = DateUtils.DATE_YYYY_MM_DD;
				if (source.matches("^\\d{4}\\d{2}\\d{2}$")) {
					format = DateUtils.DATE_YYYYMMDD;
				} else if (source.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
					format = DateUtils.DATE_YYYY_MM_DD;
				} else if (source.matches("^\\d{2}\\d{2}\\d{2}$")) {
					format = DateUtils.DATE_HHMISS;
				} else if (source.matches("^\\d{2}:\\d{2}:\\d{2}$")) {
					format = DateUtils.DATE_HHMISS_F;
				} else if (source.matches("^\\d{4}\\d{2}\\d{2}\\d{2}\\d{2}\\d{2}$")) {
					format = DateUtils.DATE_YYYYMMDDHHMISS;
				} else if (source.matches("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$")) {
					format = DateUtils.DATE_YYYYMMDDHHMISS_F;
				} else if(source.matches("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{1}$")){
					format = DateUtils.DATE_MILLI1;
				}else if(source.matches("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{2}$")){
					format = DateUtils.DATE_MILLI2;
				}else if(source.matches("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{3}$")){
					format = DateUtils.DATE_MILLI3;
				}else if(source.indexOf("CST")!=-1){
					format = DateUtils.DATE_CST;
					date = (new SimpleDateFormat(format,Locale.ENGLISH)).parse(source);
					return date;
				}else if(source.indexOf("UTC")!=-1){
					format = DateUtils.DATE_UTC;
					date = (new SimpleDateFormat(format,Locale.ENGLISH)).parse(source);
					return date;
				}else {
					format = DateUtils.DATE_UTC;
				}
				date = (new SimpleDateFormat(format)).parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	public static boolean isYMDDate(String str){
		return str.matches("^\\d{4}-\\d{2}-\\d{2}$");
	}
	
	public static boolean isYMDHHDate(String str){
		return str.matches("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$");
	}

	/**
	 * 对日期时间进行增删操作
	 * 
	 * @param obj
	 *            日期对象(Date/String) 为null时,为当天日期
	 * @param tp
	 *            操作类型 'y' : 年; 'M' : 月; 'd' : 日; 'H' : 小时; 'm' : 分钟; 's' : 秒;
	 * @param amount
	 *            增加/减少 的数量
	 * @param format
	 *            返回的日期字符格式 可为null
	 * @return
	 */
	public static String operateDateTime(Object obj, char tp, int amount, String format) {
		Date date = new Date();
		if (obj != null) {
			if (obj instanceof Date) {
				date = (Date) obj;
			}
			if (obj instanceof String) {
				date = parseToDate(obj.toString());
			}
		}

		int field = Calendar.DAY_OF_MONTH;

		// 操作类型 'y' : 年; 'M' : 月; 'd' : 日; 'H' : 小时; 'm' : 分钟; 's' : 秒;
		switch (tp) {
		case 'y':
			field = Calendar.YEAR;
			break;
		case 'M':
			field = Calendar.MONTH;
			break;
		case 'd':
			field = Calendar.DAY_OF_MONTH;
			break;
		case 'H':
			field = Calendar.HOUR_OF_DAY;
			break;
		case 'm':
			field = Calendar.MINUTE;
			break;
		case 's':
			field = Calendar.SECOND;
			break;
		default:
			break;
		}

		Calendar riqi = new GregorianCalendar();
		riqi.setTime(date);
		riqi.add(field, amount);
		if (format == null || format.equals("")) {
			format = DateUtils.DATE_YYYYMMDDHHMISS_F;
		}
		return (new SimpleDateFormat(format)).format(riqi.getTime());
	}

	/**
	 * 判断给定日期是否是周末（六、日）
	 * 
	 * @param theDate
	 * @return
	 */
	public static boolean isWeekend(Date theDate) {
		java.util.Calendar c = new GregorianCalendar(TimeZone.getTimeZone("EST"), Locale.US);
		c.setTime(theDate);
		if (c.get(java.util.Calendar.DAY_OF_WEEK) < 7 && c.get(java.util.Calendar.DAY_OF_WEEK) > 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取当前的完整时间
	 * 
	 * @return 时间字符串
	 */
	public static String getCurTime() {
		return format(null, DateUtils.DATE_YYYYMMDDHHMISS_F);
	}
	/**
	 * 在给定的日期上加上给定的天数返回结算后的日期
	 * @param date 默认为当前日期
	 * @param days天数
	 * @return
	 */
	public static Date addDay(Date date,int days){
		if (date==null) {
			date=new Date();
		}
		if (days==0) {
			return date;
		}
		long time=date.getTime()+MILLISECONDS_DAY*days;
		return new Date(time);
	}
	/**
	 * 今天日期 除去时分秒
	 * @return
	 */
	public static Date today(){
		return DateUtils.parseToDate(format(new Date(),DATE_YYYY_MM_DD));
	}
	public static String todayYYYY_MM_DD(){
		return new SimpleDateFormat(DateUtils.DATE_YYYY_MM_DD).format(new Date());
	}
	public static String todayYYYY_MM(){
		return new SimpleDateFormat(DateUtils.DATE_YYYY_MM).format(new Date());
	}
	public static String todayYYYYMMDDHHMMSS(){
		return new SimpleDateFormat(DATE_YYYYMMDDHHMISS_F).format(new Date());
	}
	
	public static int getdaysnum(String year,String month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,Integer.parseInt(year));
		cal.set(Calendar.MONTH,Integer.parseInt(month)-1);//Java月份才0开始算
		return cal.getActualMaximum(Calendar.DATE); 
	}
	
	public static String getdayofweek(String doday){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		 Calendar c = Calendar.getInstance();  
		 String week="";
		 int dayForWeek = 0;  
		 try {
			c.setTime(format.parse(doday));
			 if(c.get(Calendar.DAY_OF_WEEK) == 1){  
			  dayForWeek = 7;  
			 }else{  
			  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
			 }  
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		switch (dayForWeek){
			case 1:week="一";break;
			case 2:week="二";break;
			case 3:week="三";break;
			case 4:week="四";break;
			case 5:week="五";break;
			case 6:week="六";break;
			case 7:week="日";break;
		}
		 return week;  
	}
	
	
	/**
	 * 获取一周的日期
	 * @param year 年
	 * @param week  当前周 数  这周是 50 
	 * @param ymd  日期
	 * @return
	 */
	@SuppressWarnings("finally")
	public static Map<String, Object> getWeedDay(int year,int week){
		Map<String, Object> map = new HashMap<String,Object>();
		Calendar calendar = Calendar.getInstance();
		if(year==0){
			year = calendar.get(calendar.YEAR);
			week = calendar.get(calendar.WEEK_OF_YEAR);
		}
		try {
			String sundayDate = getYearWeekEndDay(year,week);//获取周到最后一天 也就是周日的具体日期
			Date date = DateUtils.parseToDate(sundayDate);//getWeek
			map.put("monday", DateUtils.format(DateUtils.addDay(date, -6), DateUtils.DATE_YYYY_MM_DD));
			map.put("tuesday", DateUtils.format(DateUtils.addDay(date, -5), DateUtils.DATE_YYYY_MM_DD));
			map.put("wednesday", DateUtils.format(DateUtils.addDay(date, -4), DateUtils.DATE_YYYY_MM_DD));
			map.put("thursday", DateUtils.format(DateUtils.addDay(date, -3), DateUtils.DATE_YYYY_MM_DD));
			map.put("friday", DateUtils.format(DateUtils.addDay(date, -2), DateUtils.DATE_YYYY_MM_DD));
			map.put("saturday", DateUtils.format(DateUtils.addDay(date, -1), DateUtils.DATE_YYYY_MM_DD));
			map.put("sunday", sundayDate);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return map;
		}
	}
	
	/**
	 * 获取一月的日期 包含星期
	 * @param year 年
	 * @param month  当前月  
	 * @param ymd  日期
	 * @return
	 */
	@SuppressWarnings("finally")
	public static List<Map<String, String>> getMonthDay(int year,int month){
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; 
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		try {
			if(year>0 && year<9999){
				if(month>0 && month<13){
					//合法数据
					String dateStr = "";
					if(month>9){
						dateStr = year+"-"+month+"-01";
					}else{
						dateStr = year+"-0"+month+"-01";
					}
					int monthDaysCount = getDaysByYearMonth(year,month);//获取某一月的天数
					String weekCode = getWeekOfDate(parseToDate(dateStr));
					Date date = parseToDate(dateStr);
					if(monthDaysCount>0){
						for(int i=0;i<monthDaysCount;i++){
							Map<String, String> map = new HashMap<String, String>();
							if(i==0){
								map.put("date",dateStr);
								map.put("week",weekDaysName[Integer.parseInt(weekCode)]);
								map.put("year", getWeek(dateStr).get("year").toString());
								map.put("belongWeek", getWeek(dateStr).get("week").toString());
							}else{
								map.put("date",DateUtils.format(DateUtils.addDay(date, i), DateUtils.DATE_YYYY_MM_DD));
								map.put("week",weekDaysName[Integer.parseInt(getWeekOfDate(DateUtils.addDay(date, i)))]);
								map.put("year", getWeek(DateUtils.format(DateUtils.addDay(date, i), DateUtils.DATE_YYYY_MM_DD)).get("year").toString());
								map.put("belongWeek", getWeek(DateUtils.format(DateUtils.addDay(date, i), DateUtils.DATE_YYYY_MM_DD)).get("week").toString());
							}
							list.add(map);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return list;
		}
	}
	/**
	 * 获取一周的日期
	 * @param year 年
	 * @param week  当前周 数  这周是 50 
	 * @param ymd  日期
	 * @return
	 * @throws ParseException 
	 */
	public static Map<String, Object> getWeedDay(String ymd) throws ParseException{
		Map<String, Object> map = new HashMap<String,Object>();
		map = getWeek(ymd);
		return getWeedDay(Integer.parseInt(map.get("year").toString()),Integer.parseInt(map.get("week").toString()));
	}
 
	/**
	  * 计算某年某周的结束日期 (以周一为周的第一天 周日为周的最后一天)
	  * 
	  * @param yearNum 格式 yyyy
	  * @param weekNum  1到52或者53
	  * @return 日期，格式为yyyy-MM-dd
	  */
	 public static String getYearWeekEndDay(int yearNo, int weekNo) {
		  SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar cal = Calendar.getInstance();
		  cal.setFirstDayOfWeek(Calendar.SUNDAY); // 设置每周的第一天为星期一
		  cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 每周从周一开始
		  // 上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。
		  cal.setMinimalDaysInFirstWeek(7); // 设置每周最少为7天
		  if(yearNo==0){
			  yearNo =cal.get(cal.get(cal.YEAR));
		  }
		  if(weekNo==0){
			  weekNo = cal.get(cal.WEEK_OF_YEAR);
		  }
		  cal.set(Calendar.YEAR, yearNo);
		  cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		  // 分别取得当前日期的年、月、日
		  return FORMAT_DATE.format(cal.getTime());
	 }
	 /**
	  * 根据日期获取本年日期所在的周数 年份
	  * @param ymd (2013-12-16)
	  * @return map.get("year") map.get("week")
 	  * @throws ParseException
	  */
	 public static Map<String, Object> getWeek(String ymd) throws ParseException{
		  String today = ymd;
		  if("".equals(today)){
			  today = DateUtils.format(new Date(), null);
		  }
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Date date = format.parse(today);
		  Calendar calendar = Calendar.getInstance();
		  calendar.setFirstDayOfWeek(Calendar.MONDAY);
		  calendar.setTime(date);
		  Map<String, Object> map = new HashMap<String,Object>();
		  map.put("year", calendar.get(calendar.YEAR));
		  map.put("week", calendar.get(Calendar.WEEK_OF_YEAR));
		  return map;
	 }
	 
	 
	 /** 
     * 根据年 月 获取对应的月份 天数 
     * */  
    public static int getDaysByYearMonth(int year, int month) {  
          
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
    
    
    /** 
     * 根据日期获得星期 
     * @param date 
     * @return  0 星期日  1 星期一  2 星期二  3 星期三  4 星期四 5 星期五 6 星期六
     */ 
	public static String getWeekOfDate(Date date) { 
	  String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; 
	  String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
	  Calendar calendar = Calendar.getInstance(); 
	  calendar.setTime(date); 
	  int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
	  return weekDaysCode[intWeek]; 
	} 
	
	/** 
	  * 判断两个日期是否在同一周 
	  * 
	  * @param date1 
	  * @param date2 
	  * @return 
	  */ 
	public static boolean isSameWeekDates(Date date1, Date date2) { 
	  Calendar cal1 = Calendar.getInstance(); 
	  Calendar cal2 = Calendar.getInstance(); 
	  cal1.setTime(date1); 
	  cal2.setTime(date2); 
	  int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR); 
	  if (0 == subYear) { 
	   if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2 
	     .get(Calendar.WEEK_OF_YEAR)) 
	    return true; 
	  } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) { 
	   // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周 
	   if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2 
	     .get(Calendar.WEEK_OF_YEAR)) 
	    return true; 
	  } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) { 
	   if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2 
	     .get(Calendar.WEEK_OF_YEAR)) 
	    return true; 
	  } 
	  return false; 
	}
	public static void main(String[]args){
		/*System.out.println(parseToDate("2013-12-13"));
		System.out.println("----"+getdayofweek("2013-12-12"));*/
		System.out.println(">>>>>>>>>>>>>>>>>>>>"+DateUtils.format(new Date(), DateUtils.DATE_YYYYMMDDHHMISSSSS));
	}
	
	
	
	
}

