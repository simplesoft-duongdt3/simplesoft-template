package com.simplesoft.simplesofttemplate.main.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

import com.simplesoft.simplesysteminfo.R;

/**
 * DateUtil.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 12:42:25 20 Jul 2014
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {

	public static final String DATE_US = "MM/dd/yyyy";
	public static final String DATE_VN = "dd/MM/yyyy";
	
	/**
	 * Get String current date with format 
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:47:31 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param format
	 * @return
	 */
	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		return formatDate(cal.getTime(), format);
	}
	
	/**
	 * Format Date 2 String
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:46:55 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		String dateStr = "";
		SimpleDateFormat sfs = new SimpleDateFormat(format);
		dateStr = sfs.format(date);
		return dateStr;
	}
	
	/**
	 * Parse DateString 2 Date
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:47:13 20 Jul 2014
	 * @return: Date
	 * @throws:  
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		SimpleDateFormat sfs = new SimpleDateFormat(format);
		try {
			date = sfs.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}
	
		
	public static String formatMilisecond(long mili) {	
		String res = "";
		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;
 
		long elapsedDays = mili / daysInMilli;
		mili = mili % daysInMilli;
		
		if (elapsedDays > 0) {
			if (res.length() > 0) {
				res += ", ";
			}
			res += elapsedDays + " " + StringUtil.getString(R.string.days);
		}
 
		long elapsedHours = mili / hoursInMilli;
		mili = mili % hoursInMilli;
		if (elapsedHours > 0) {
			if (res.length() > 0) {
				res += ", ";
			}
			res += elapsedHours + " " + StringUtil.getString(R.string.hours);
		}
 
		long elapsedMinutes = mili / minutesInMilli;
		mili = mili % minutesInMilli;
		if (elapsedMinutes > 0) {
			if (res.length() > 0) {
				res += ", ";
			}
			res += elapsedMinutes + " " + StringUtil.getString(R.string.minutes);
		}
 
		long elapsedSeconds = mili / secondsInMilli;
		if (elapsedSeconds > 0) {
			if (res.length() > 0) {
				res += ", ";
			}
			res += elapsedSeconds + " " + StringUtil.getString(R.string.seconds);
		}
		return res;
	}
}
