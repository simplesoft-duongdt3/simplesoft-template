/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

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
	
	public static final String DEV_ID = "107535046";
	public static final String APP_ID = "207236345";
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		
		//lấy thông in debug mode
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			LogUtil.isDebugMode = ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
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
	
	boolean isForeground = false;
	String lastActivityCall = "";
	
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
