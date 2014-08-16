/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.utils;

import com.simplesoft.simplesofttemplate.main.view.AppInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * ActivityUtil.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 09:51:16 16 Aug 2014
 */
public class ActivityUtil {
	
	public static void switchActivity(Intent data) {
		ActivityUtil.switchActivity(AppInfo.getInstance().getActivityContext(), data);
	}
	
	public static void switchActivity(Class<?> activityClass) {
		ActivityUtil.switchActivity(AppInfo.getInstance().getActivityContext(), activityClass);
	}
	
	public static void switchActivity(Class<?> activityClass, Bundle data) {
		ActivityUtil.switchActivity(AppInfo.getInstance().getActivityContext(), activityClass, data);
	}
	
	
	public static void switchActivity(Context con, Class<?> activityClass) {
		Intent intent = new Intent(con, activityClass);
		ActivityUtil.switchActivity(con, intent);
	}
	
	public static void switchActivity(Context con, Intent intent) {
		con.startActivity(intent);
	}
	
	public static void switchActivity(Context con, Class<?> activityClass, Bundle data) {
		Intent intent = new Intent(con, activityClass);
		intent.putExtras(data);
		ActivityUtil.switchActivity(intent);
	}
}
