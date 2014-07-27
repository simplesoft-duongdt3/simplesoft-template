/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.model;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo.ItemInfo;
import com.simplesoft.simplesofttemplate.function.DTO.ListAppItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
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
	 * @return: ListAppItemInfo
	 * @throws:
	*/
	public static ListAppItemInfo getAllAppInfo() throws Exception{
		ListAppItemInfo result = new ListAppItemInfo();
		PackageManager pm = AppInfo.getInstance().getPackageManager();
		List<ApplicationInfo> listApp = pm.getInstalledApplications(PackageManager.GET_META_DATA);
		for(ApplicationInfo item : listApp){
			AppItemInfo itemInfo = new AppItemInfo();
			itemInfo.packageName = item.packageName;
			//Resources res = pm.getResourcesForApplication(item.packageName);
			itemInfo.uid = item.uid;
			itemInfo.processName = item.processName;
			PackageInfo pkInfo = pm.getPackageInfo(itemInfo.packageName
					,PackageManager.GET_ACTIVITIES|PackageManager.GET_PERMISSIONS|PackageManager.GET_PROVIDERS|PackageManager.GET_RECEIVERS|PackageManager.GET_SERVICES);
			
			itemInfo.name = item.loadLabel(pm).toString();
			itemInfo.versionCode = pkInfo.versionCode;
			itemInfo.versionName = pkInfo.versionName;
			itemInfo.permissions = new ArrayList<AppItemInfo.ItemInfo>();
			if (pkInfo.permissions != null) {
				for (int i = 0; i < pkInfo.permissions.length; i++) {
					PermissionInfo element = pkInfo.permissions[i];
					if(StringUtil.isEmptyStr(element.group)){
						element.group = StringUtil.getString(R.string.text_NA);
					}
					LogUtil.log(itemInfo.packageName + ": " + element.name + " " + element.group);
					itemInfo.permissions.add(new ItemInfo(element.name, element.group));
				}
			}
			
			itemInfo.userPermissions = new ArrayList<AppItemInfo.ItemInfo>();
			if (pkInfo.requestedPermissions != null) {
				for (int i = 0; i < pkInfo.requestedPermissions.length; i++) {
					PermissionInfo element = null;
					try {
						element = pm.getPermissionInfo(
								pkInfo.requestedPermissions[i],
								PackageManager.GET_META_DATA);
						if(StringUtil.isEmptyStr(element.group)){
							element.group = StringUtil.getString(R.string.text_NA);
						}
						itemInfo.userPermissions.add(new ItemInfo(element.name, element.group));
						LogUtil.log(itemInfo.packageName + ": " + element.name + " " + element.group);
					} catch (Exception e) {
						itemInfo.userPermissions.add(new ItemInfo(pkInfo.requestedPermissions[i], StringUtil.getString(R.string.text_NA)));
						LogUtil.log(itemInfo.name + ": " + "NA");
					}
					
				}
			}
			
			itemInfo.providers = new ArrayList<AppItemInfo.ItemInfo>();
			if (pkInfo.providers != null) {
				for (int i = 0; i < pkInfo.providers.length; i++) {
					ProviderInfo element = pkInfo.providers[i];
					itemInfo.providers.add(new ItemInfo(element.name, "PROVIDER"));
				}
			}
			
			itemInfo.receivers = new ArrayList<AppItemInfo.ItemInfo>();
			if (pkInfo.receivers != null) {
				for (int i = 0; i < pkInfo.receivers.length; i++) {
					ActivityInfo element = pkInfo.receivers[i];
					itemInfo.receivers.add(new ItemInfo(element.name, "RECEIVER"));
				}
			}
			
			itemInfo.services = new ArrayList<AppItemInfo.ItemInfo>();
			if (pkInfo.services != null) {
				for (int i = 0; i < pkInfo.services.length; i++) {
					ServiceInfo element = pkInfo.services[i];
					itemInfo.services.add(new ItemInfo(element.name, "SERVICE"));
				}
			}
			
			itemInfo.numPermissions = itemInfo.permissions.size() + itemInfo.userPermissions.size();
			
			itemInfo.isSystemApp = ((item.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
			result.listApp.add(itemInfo);
		}
		
		return result;
	}
}
