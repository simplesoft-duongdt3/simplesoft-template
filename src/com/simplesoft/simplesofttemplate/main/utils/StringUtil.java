/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.utils;

import java.text.DecimalFormat;

import com.simplesoft.simplesofttemplate.main.view.AppInfo;

/**
 * StringUtil.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 12:42:40 20 Jul 2014
 */
public class StringUtil {

	public static final String DOUBLE_FORMAT = "#,###.##";
	public static final String INT_FORMAT = "#,###";
	
	/** The codau. */
	static char codau[] = { 'à', 'á', 'ả', 'ã', 'ạ', 'ă', 'ằ', 'ắ', 'ẳ', 'ẵ', 'ặ', 'â', 'ầ', 'ấ', 'ẩ', 'ẫ', 'ậ', 'À',
			'Á', 'Ả', 'Ã', 'Ạ', 'Ă', 'Ằ', 'Ắ', 'Ẳ', 'Ẵ', 'Ặ', 'Â', 'Ầ', 'Ấ', 'Ẩ', 'Ẫ', 'Ậ', 'è', 'é', 'ẻ', 'ẽ', 'ẹ',
			'ê', 'ề', 'ế', 'ể', 'ễ', 'ệ', 'È', 'É', 'Ẻ', 'Ẽ', 'Ẹ', 'Ê', 'Ề', 'Ế', 'Ể', 'Ễ', 'Ệ', 'ì', 'í', 'ỉ', 'ĩ',
			'ị', 'Ì', 'Í', 'Ỉ', 'Ĩ', 'Ị', 'ò', 'ó', 'ỏ', 'õ', 'ọ', 'ô', 'ồ', 'ố', 'ổ', 'ỗ', 'ộ', 'ơ', 'ờ', 'ớ', 'ở',
			'ỡ', 'ợ', 'Ò', 'Ó', 'Ỏ', 'Õ', 'Ọ', 'Ô', 'Ồ', 'Ố', 'Ổ', 'Ỗ', 'Ộ', 'Ơ', 'Ờ', 'Ớ', 'Ở', 'Ỡ', 'Ợ', 'ù', 'ú',
			'ủ', 'ũ', 'ụ', 'ư', 'ừ', 'ứ', 'ử', 'ữ', 'ự', 'Ù', 'Ú', 'Ủ', 'Ũ', 'Ụ', 'ỳ', 'ý', 'ỷ', 'ỹ', 'ỵ', 'Ỳ', 'Ý',
			'Ỷ', 'Ỹ', 'Ỵ', 'đ', 'Đ', 'Ư', 'Ừ', 'Ử', 'Ữ', 'Ứ', 'Ự' };
	/** The khongdau. */
	static char khongdau[] = { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
			'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'e', 'e', 'e', 'e',
			'e', 'e', 'e', 'e', 'e', 'e', 'e', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'i', 'i', 'i',
			'i', 'i', 'I', 'I', 'I', 'I', 'I', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',
			'o', 'o', 'o', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'u',
			'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'U', 'U', 'U', 'U', 'U', 'y', 'y', 'y', 'y', 'y', 'Y',
			'Y', 'Y', 'Y', 'Y', 'd', 'D', 'U', 'U', 'U', 'U', 'U', 'U' };

	
	/**
	 * Loại bỏ dấu trong chuỗi
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 13:43:59 3 Aug 2014
	 * @return: String
	 * @throws:  
	 * @param input
	 * @return
	 */
	public static String getEngString(String input) {
		if (isEmptyStr(input)) {
			return "";
		}
		input = input.trim();

		for (int i = 0; i < codau.length; i++) {
			input = input.replace(codau[i], khongdau[i]);
		}
		return input;
	}
	
	/**
	 * Check String null or empty
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:49:51 20 Jul 2014
	 * @return: boolean
	 * @throws:  
	 * @param str
	 * @return
	 */
	public static boolean isEmptyStr(String str){
		if (str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Format int with default format
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:58:53 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param number
	 * @return
	 */
	public static String formatNumber(int number){
		return formatNumber(number, INT_FORMAT);
	}
	
	/**
	 * Format long with default format
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:59:10 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param number
	 * @return
	 */
	public static String formatNumber(long number){
		return formatNumber(number, INT_FORMAT);
	}
	
	/**
	 * Format float with default format
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:59:19 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param number
	 * @return
	 */
	public static String formatNumber(float number){
		return formatNumber(number, DOUBLE_FORMAT);
	}
	
	/**
	 * Format double with default format
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:59:30 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param number
	 * @return
	 */
	public static String formatNumber(double number){
		return formatNumber(number, DOUBLE_FORMAT);
	}
	
	/**
	 * Format int with format
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:59:42 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param number
	 * @param format
	 * @return
	 */
	public static String formatNumber(int number, String format){
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}
	
	/**
	 * Format long with format
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:59:49 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param number
	 * @param format
	 * @return
	 */
	public static String formatNumber(long number, String format){
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}
	
	/**
	 * Format float with format
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:59:58 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param number
	 * @param format
	 * @return
	 */
	public static String formatNumber(float number, String format){
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}
	
	/**
	 * Format double with format
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 13:00:06 20 Jul 2014
	 * @return: String
	 * @throws:  
	 * @param number
	 * @param format
	 * @return
	 */
	public static String formatNumber(double number, String format){
		DecimalFormat df = new DecimalFormat(format);
		return df.format(number);
	}
	
	/**
	 * return string from resource id
	 * @author: DungNX
	 * @param id
	 * @return
	 * @return: String
	 * @throws:
	*/
	public static String getString(int id){
		return AppInfo.getInstance().getString(id);
	}
	

	/**
	 * Mo ta muc dich cua ham
	 * @author: DungNX
	 * @param aString
	 * @return
	 * @return: String
	 * @throws:
	*/
	public static String getStringResourceByName(String aString, String defaultStr) {
		String packageName = AppInfo.getInstance().getPackageName();
		int resId = AppInfo.getInstance().getResources().getIdentifier(aString, "string", packageName);
		if (resId == 0) {
			return defaultStr;
		} else {
			return AppInfo.getInstance().getString(resId);
		}
	}
}
