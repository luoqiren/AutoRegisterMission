package com.lqr.util.date;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtil {

	public static long daysDiff(Date firstDay, Date lastDay) {
		if (firstDay == null || lastDay == null) {
			return 0;
		}
		long allDays = (lastDay.getTime() - (firstDay.getTime()))
				/ (1000 * 24 * 60 * 60);

		return allDays;
	}

	public static Long yearDiff(Date firstDay, Date lastDay) {
		if (firstDay == null || lastDay == null)
			return null;
		Calendar first = Calendar.getInstance();
		first.setTime(firstDay);
		Calendar last = Calendar.getInstance();
		last.setTime(lastDay);
		long years = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
		return years;
	}

	public static Date addMinute(Date src, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTime(src);
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}

	public static String getTodayTextYMD() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());

	}
	
	public static String getTodayTextYMDHMS() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		return df.format(new Date());
	}
	
	/**
	 * 获取当前时分
	 * @return HH24:mm =>22:00
	 */
	public static String getTodayTextH24M() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(new Date());
	}
	
	public static String getCurrentSeconds() {
		DateFormat df = new SimpleDateFormat("ss");
		return df.format(new Date());
	}

	public static String getYesterdayTextYMD() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(c.getTime());

	}

	public static Date getYesterday() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.getTime();
	}

	public static Date parseDateYMD(String ymd) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			return df.parse(ymd);
		} catch (ParseException e) {
			return getYesterday();
		}
	}
	
	public static Date parseDate(String strDate,String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * 
	 * @param format
	 * @param dt
	 * @return
	 */
	public static String formatDate(String format, Date dt) {
		if(StringUtils.isEmpty(format)){
			return "";
		}
		
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(dt);
	}
	
	public static String getToday(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}
	
	
	public static Date getLastDayOfMonth(Date date) throws ParseException{
		if(date!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH,1);
			calendar.add(Calendar.DAY_OF_MONTH,-1);
			return calendar.getTime();
		}else{
			throw new IllegalArgumentException("The parameter dateStr not be specified!");
		}
	}
	
}
