/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.utils;

import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.appender.FileAppender;
import com.google.code.microlog4android.appender.LogCatAppender;

/**
 * LogUtil.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 18:43:01 19 Jul 2014
 */
public class LogUtil {

	public static final com.google.code.microlog4android.Logger fileLogger = LoggerFactory.getLogger();
	public static boolean isDebugMode = false;
	public static final String LOG_TAG = "simplesoft";
	
	/**
	 * set debug mode
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 10:07:24 28 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param isDebug
	 */
	public static void setIsDebugMode(boolean isDebug) {
		initLogger(isDebug, isDebug);
	}
	
	private static void initLogger(boolean logcat, boolean logFile){
		if (logcat) {
			LogCatAppender logcatApp = new LogCatAppender();
			LogUtil.fileLogger.addAppender(logcatApp);
		}
		
		if (logFile) {
			FileAppender fileApp = new FileAppender();
			fileApp.setFileName("simplesoft.log.txt");
			LogUtil.fileLogger.addAppender(fileApp);
		}
	}
	
	public static void log(Throwable e){
		log(LOG_TAG, getExceptionMessage(e));
	}
	
	public static void log(Object msg){
		if (isDebugMode) {
			log(LOG_TAG, String.valueOf(msg));
		}
	}
	
	private static void log(String tag, String msg){
		if (isDebugMode) {
			fileLogger.error(tag + ": " + msg + "\r\n");
		}
	}
	
	/**
	 * Trích xuất thông tin cần thiết từ 1 Exception
	 * @author: duongdt3
	 * @since: 09:17:16 20 Feb 2014
	 * @return: String
	 * @throws:  
	 * @param ex
	 * @return
	 */
	public static String getExceptionMessage(Throwable ex) {
		String report = "";
		if (ex != null) {
			report += ex.toString() + "\r\n";
			StackTraceElement[] arr = ex.getStackTrace();
			if (arr != null) {
				report += "\r\n--------- Stack trace ---------";
				for (int i = 0; i < arr.length; i++) {
					report += "\r\n\t" + arr[i].toString();
				}
			}
			Throwable cause = ex.getCause();
			if (cause != null) {
				report += "\r\n--------- Cause ---------";
				report += "\r\n\t" + cause.toString();
				arr = cause.getStackTrace();
				for (int i = 0; i < arr.length; i++) {
					report += "\r\n\t" + arr[i].toString();
				}
			}
		}
		return report;
	}

}
