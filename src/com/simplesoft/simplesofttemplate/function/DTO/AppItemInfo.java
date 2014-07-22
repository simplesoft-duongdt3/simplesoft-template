package com.simplesoft.simplesofttemplate.function.DTO;

import java.util.Comparator;

import android.content.pm.ActivityInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;

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
	
//	public Drawable icon;
	
	/**
	 * Comparator for AppItemInfo, order by permissions.length desc, nếu = per thì theo tên
	 * @author: duongdt3
	 * @version: 1.0 
	 * @since:  1.0
	 * @time: 15:38:57 22 Jul 2014
	 */
	public static final class PerDescComparator implements
			Comparator<AppItemInfo> {
		@Override
		public int compare(AppItemInfo lhs, AppItemInfo rhs) {
			int result = 0;
			if (lhs.permissions.length > rhs.permissions.length) {
				result = -1;
			} else if (lhs.permissions.length < rhs.permissions.length) {
				result = 1;	
			} else {
				String lStr = lhs.name != null ? lhs.name : "";
				String rStr = rhs.name != null ? rhs.name : "";
				result = lStr.compareTo(rStr);
			}
			
			return result;
		}
	}
}
