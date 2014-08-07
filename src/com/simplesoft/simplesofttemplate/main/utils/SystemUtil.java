/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.utils;

import android.content.pm.PackageManager.NameNotFoundException;

import com.simplesoft.simpleappspermissions.R;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;

/**
 * SystemUtil.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 22:03:17 7 Aug 2014
 */
public class SystemUtil {
	public static String getVersionApp(String packName){
		String version = "";
		try {
			version = AppInfo.getInstance().getPackageManager().getPackageInfo(packName, 0).versionName;
		} catch (NameNotFoundException e) {
			LogUtil.log(e);
		}
		return version;
	}
	
	public static String getSystemInfoStr(){
		String packName = getAppPackageName();
		return getAppName() + " " 
				+ StringUtil.getString(R.string.text_manufacturer) + ": "
				+ getManufacturer() + " "
				+ StringUtil.getString(R.string.text_model) + ": "
				+ getModel() + " "
				+ StringUtil.getString(R.string.text_os_version) + ": "
				+ getOsIntVersion() + " "
				+ StringUtil.getString(R.string.text_app_version) + ": "
				+ getVersionApp(packName);
	}
	
	
	public static String getAppName(){
		return  StringUtil.getString(R.string.app_name);
	}
	
	public static String getAppPackageName(){
		return  AppInfo.getInstance().getPackageName();
	}
	
	public static int getOsIntVersion(){
		return android.os.Build.VERSION.SDK_INT;  
	}
	
	public static String getManufacturer(){
		return android.os.Build.MANUFACTURER;
	}
	
	public static String getModel(){
		return android.os.Build.MODEL;
	}
	
}
