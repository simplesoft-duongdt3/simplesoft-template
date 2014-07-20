package com.simplesoft.simplesofttemplate.function.DTO;

import android.content.pm.ActivityInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;

/**
 * Mo ta muc dich cua class
 * @author: DungNX
 * @version: 1.0
 * @since: 1.0
 */
public class AppItemInfo {
	public int uid;
	public String name;
	public String packageName;
	public String processName;
	public int versionCode;
	public String versionName;
	public String lastUpdateDate;
	
	public PermissionInfo[] permissions;
	public ProviderInfo[] providers;
	public ActivityInfo[] receivers;
	public ServiceInfo[] services;
	
	public Drawable icon;
}
