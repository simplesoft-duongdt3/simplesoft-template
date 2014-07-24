package com.simplesoft.simplesofttemplate.function.DTO;

import java.io.Serializable;

import android.content.pm.ActivityInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;

import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.IComparator;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.ICondition;

/**
 * Mo ta muc dich cua class
 * @author: DungNX
 * @version: 1.0
 * @since: 1.0
 */
public class AppItemInfo implements Serializable{
	private static final long serialVersionUID = 6902304495394631528L;
	
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
	public boolean isSystemApp;

	public static final class PerComparator extends IComparator<AppItemInfo> {

		@Override
		public int doCompare(AppItemInfo lhs, AppItemInfo rhs) {
			int result = 0;
			if (lhs.permissions.length > rhs.permissions.length) {
				result = 1;
			} else if (lhs.permissions.length < rhs.permissions.length) {
				result = -1;	
			}
			return result;
		}
	}
	
	public static final class NameComparator extends IComparator<AppItemInfo> {
		
		@Override
		public int doCompare(AppItemInfo lhs, AppItemInfo rhs) {
			int result = 0;
			String lStr = lhs.name != null ? lhs.name : "";
			String rStr = rhs.name != null ? rhs.name : "";
			result = lStr.compareToIgnoreCase(rStr);
			return result;
		}
	}
	
	/**
	 * Condition for AppItemInfo, check AppItemInfo have permission
	 * AppItemInfo.java
	 * @author: duongdt3
	 * @version: 1.0 
	 * @since:  1.0
	 * @time: 23:02:36 23 Jul 2014
	 */
	public static final class HavePerCondition extends ICondition<AppItemInfo>{
		@Override
		protected boolean doCondition(AppItemInfo object) {
			return object.permissions.length > 0;
		}
	}
	
	/**
	 * Condition for AppItemInfo, check AppItemInfo have permission
	 * AppItemInfo.java
	 * @author: duongdt3
	 * @version: 1.0 
	 * @since:  1.0
	 * @time: 23:02:36 23 Jul 2014
	 */
	public static final class IsSystemAppCondition extends ICondition<AppItemInfo>{
		@Override
		protected boolean doCondition(AppItemInfo object) {
			return object.isSystemApp;
		}
	}
}
