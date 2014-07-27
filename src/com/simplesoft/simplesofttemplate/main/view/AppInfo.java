/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.google.code.microlog4android.appender.FileAppender;
import com.simplesoft.simplesofttemplate.function.DTO.ListAppItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.Operator;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;

/**
 * Aplication of SimpleSoft App
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 18:43:32 19 Jul 2014
 */
public class AppInfo extends Application {
	
	private static AppInfo instance = null;
	Activity activityContext = null;
	// khong doi
	public static final String DEV_ID = "107535046";
	// doi
	public static final String APP_ID = "207236345";
	
	boolean isForeground = false;
	String lastActivityCall = "";
	public ListAppItemInfo listData;
	public Operator opSystemApp = Operator.NOT;
	public String strQueryApp = "";
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		
		//lấy thông in debug mode
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			LogUtil.isDebugMode = ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
			/*FileAppender fileApp = new FileAppender();
			fileApp.setFileName("simplesoft.txt");
			LogUtil.fileLogger.addAppender(fileApp);*/
		} catch (Exception ex) {
			LogUtil.isDebugMode = false;
		}
	}
	
	public static AppInfo getInstance() {
		return instance;
	}
	
	/**
	 * @return the activityContext
	 */
	public Activity getActivityContext() {
		return activityContext;
	}
	
	/**
	 * @param activityContext the activityContext to set
	 */
	public void setActivityContext(Activity activityContext) {
		this.activityContext = activityContext;
	}
	
	
	/**
	 * call it when stop activity
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 16:56:39 7 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param ac
	 */
	public void callActivityStop(Activity ac){
		if(ac == null || lastActivityCall.equals(ac.getClass().getName())){
			isForeground = false;
		}
	}
	
	/**
	 * call it when resume Activity
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 16:56:22 7 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param ac
	 */
	public void callActivityResume(Activity ac){
		lastActivityCall = (ac != null ? ac.getClass().getName() : "");
		isForeground = true;
	}
	
	/**
	 * Check app is foreground
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 16:56:00 7 Jul 2014
	 * @return: boolean
	 * @throws:  
	 * @return
	 */
	public boolean isAppForeground(){
		return isForeground;
	}
}
