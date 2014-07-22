/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.model;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;

import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;

/**
 * SimpleModel.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 14:06:17 20 Jul 2014
 */
public class SimpleModel {

	/**
	 * Mo ta muc dich cua ham
	 * @author: DungNX
	 * @param context
	 * @return
	 * @throws Exception
	 * @return: List<AppItemInfo>
	 * @throws:
	*/
	public static  List<AppItemInfo> getAllAppInfo() throws Exception{
		PackageManager pm = AppInfo.getInstance().getPackageManager();
		List<ApplicationInfo> listApp = pm.getInstalledApplications(PackageManager.GET_META_DATA);
		// ds thong tin app se dua vao global
		List<AppItemInfo> listAppGlobal = new ArrayList<AppItemInfo>();
		for(ApplicationInfo item : listApp){
			AppItemInfo itemInfo = new AppItemInfo();
			itemInfo.packageName = item.packageName;
			itemInfo.uid = item.uid;
			itemInfo.processName = item.processName;
			PackageInfo pkInfo = pm.getPackageInfo(itemInfo.packageName, PackageManager.GET_ACTIVITIES);
			itemInfo.name = item.loadLabel(pm).toString();
			itemInfo.versionCode = pkInfo.versionCode;
			itemInfo.versionName = pkInfo.versionName;
			pkInfo = pm.getPackageInfo(itemInfo.packageName, PackageManager.GET_PERMISSIONS);
			itemInfo.permissions = pkInfo.permissions != null ? pkInfo.permissions : new PermissionInfo[] {};
			pkInfo = pm.getPackageInfo(itemInfo.packageName, PackageManager.GET_PROVIDERS);
			itemInfo.providers = pkInfo.providers;
			pkInfo = pm.getPackageInfo(itemInfo.packageName, PackageManager.GET_RECEIVERS);
			itemInfo.receivers = pkInfo.receivers;
			pkInfo = pm.getPackageInfo(itemInfo.packageName, PackageManager.GET_SERVICES);
			itemInfo.services = pkInfo.services;
			
			listAppGlobal.add(itemInfo);
		}
		
		return listAppGlobal;
	}
}
