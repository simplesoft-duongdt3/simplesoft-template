package com.simplesoft.simplesofttemplate.function.DTO;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.simplesoft.simplesofttemplate.constance.PermissionGroup;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.IComparator;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.ICondition;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.IDoAction;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
//import com.simplesoft.simplesofttemplate.constance.PermissionGroup;

/**
 * Mo ta muc dich cua class
 * @author: DungNX
 * @version: 1.0
 * @since: 1.0
 */
public class AppItemInfo implements Parcelable {
	public int uid;
	public String name;
	public String packageName;
	public String processName;
	public int versionCode;
	public String versionName;
	public String lastUpdateDate;
	public boolean isSystemApp;
	public Drawable drawable;
	
	public List<ItemInfo> userPermissions;
	public List<ItemInfo> permissions;
	public List<ItemInfo> providers;
	public List<ItemInfo> receivers;
	public List<ItemInfo> services;
	
	public int numPermissions;

	public static class ItemInfo implements Parcelable{
		public ItemInfo(String pName, String pGroup) {
			this.name = pName;
			this.group = pGroup;
		}

		public ItemInfo(Parcel source) {
			name = source.readString();
			group = source.readString();
		}
		
		public ItemInfo() {
			
		}
		
		public String name;
		public String group;
		
		@Override
		public int describeContents() {
			return 0;
		}
		
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(name);
			dest.writeString(group);
		}
		
		public static final Creator<ItemInfo> CREATOR = new Creator<ItemInfo>() {
			 
		    @Override
		    public ItemInfo createFromParcel(Parcel source) {
		       return new ItemInfo(source);
		    }
		 
		   @Override
		   public ItemInfo[] newArray(int size) {
		      return new ItemInfo[size];
		   }
		};
	}
	
	public static final class PerComparator extends IComparator<AppItemInfo> {

		@Override
		public int doCompare(AppItemInfo lhs, AppItemInfo rhs) {
			int result = 0;
			if (lhs.numPermissions > rhs.numPermissions) {
				result = 1;
			} else if (lhs.numPermissions < rhs.numPermissions) {
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
			return object.numPermissions > 0;
		}
	}
	
	public static class ActionSetDrawable implements IDoAction<AppItemInfo>{
		@Override
		public void doAction(AppItemInfo object) {
			if (object.drawable == null) {
				try {
					object.drawable = AppInfo.getInstance().getPackageManager().getApplicationIcon(object.packageName);
				} catch (NameNotFoundException e) {
					LogUtil.log(e);
				}
			}
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

	/**
	 * @author xdung
	 *
	 */
	public static final class IsCostMoneyApp extends ICondition<AppItemInfo>{
		@Override
		protected boolean doCondition(AppItemInfo object) {
			boolean isCostMoneyApp = false;
			for (ItemInfo item : object.permissions) {
				if(item.group.contains(PermissionGroup.COST_MONEY.getName())){
					isCostMoneyApp = true;
					break;
				}
			}
			if (!isCostMoneyApp) {
				for (ItemInfo item : object.userPermissions) {
					if (item.group.contains(PermissionGroup.COST_MONEY.getName())) {
						isCostMoneyApp = true;
						break;
					}
				}
			}
			return isCostMoneyApp;
		}
	}

	/**
	 * check group of app
	 * AppItemInfo.java
	 * @author: duongdt3
	 * @version: 1.0 
	 * @since:  1.0
	 * @time: 13:46:03 27 Jul 2014
	 */
	@SuppressLint("DefaultLocale")
	public static final class CheckGroupCondition extends ICondition<AppItemInfo> {
		
		private String groupCheck;
		public CheckGroupCondition(String pGroupCheck) {
			this.groupCheck = pGroupCheck;
			if (!StringUtil.isEmptyStr(this.groupCheck)) {
				this.groupCheck = this.groupCheck.toLowerCase();
			}
		}
		
		@Override
		protected boolean doCondition(AppItemInfo object) {
			boolean res = true;
			if (StringUtil.isEmptyStr(this.groupCheck)) {
				res = true;
			} else{
				for (ItemInfo per : object.permissions) {
					res = this.groupCheck.equals(per.group.toLowerCase());
					if (res) {
						break;
					}
				}
			}
			return res;
		}
	}
	
	/**
	 * Check name apps
	 * AppItemInfo.java
	 * @author: duongdt3
	 * @version: 1.0 
	 * @since:  1.0
	 * @time: 11:54:17 27 Jul 2014
	 */
	@SuppressLint("DefaultLocale")
	public static final class CheckNameCondition  extends ICondition<AppItemInfo>{

		private String nameCheck;
		public CheckNameCondition(String pNameCheck) {
			this.nameCheck = pNameCheck;
			if (!StringUtil.isEmptyStr(this.nameCheck)) {
				this.nameCheck = this.nameCheck.toLowerCase();
			}
		}
		
		@Override
		protected boolean doCondition(AppItemInfo object) {
			boolean res = true;
			if (StringUtil.isEmptyStr(this.nameCheck)) {
				res = true;
			} else{
				res = object.name.toLowerCase().contains(this.nameCheck);
			}
			return res;
		}

	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(uid);
		dest.writeString(name);
		dest.writeString(packageName);
		dest.writeString(processName);
		dest.writeInt(versionCode);
		dest.writeString(versionName);
		dest.writeString(lastUpdateDate);
		dest.writeInt(isSystemApp ? 1 : 0);
		dest.writeList(userPermissions);
		dest.writeList(permissions);
		dest.writeList(providers);
		dest.writeList(receivers);
		dest.writeList(services);
		//Bitmap bitmap = (Bitmap)((BitmapDrawable) drawable).getBitmap();
		//dest.writeParcelable(bitmap, flags);
	}
	
	public AppItemInfo() {
		userPermissions = new ArrayList<AppItemInfo.ItemInfo>();
		permissions = new ArrayList<AppItemInfo.ItemInfo>();
		providers = new ArrayList<AppItemInfo.ItemInfo>();
		receivers = new ArrayList<AppItemInfo.ItemInfo>();
		services = new ArrayList<AppItemInfo.ItemInfo>();
	}
	
	public AppItemInfo(Parcel parcel) {
		this();
		uid = parcel.readInt();
		name = parcel.readString();
		packageName = parcel.readString();
		processName = parcel.readString();
		versionCode = parcel.readInt();
		versionName = parcel.readString();
		lastUpdateDate = parcel.readString();
		isSystemApp = parcel.readInt() == 1;
		parcel.readList(userPermissions, ItemInfo.class.getClassLoader());
		parcel.readList(permissions, ItemInfo.class.getClassLoader());
		parcel.readList(providers, ItemInfo.class.getClassLoader());
		parcel.readList(receivers, ItemInfo.class.getClassLoader());
		parcel.readList(services , ItemInfo.class.getClassLoader());
		//Bitmap bitmap = (Bitmap)parcel.readParcelable(getClass().getClassLoader());
		//drawable = new BitmapDrawable(AppInfo.getInstance().getResources(), bitmap);
	}
	
	public static final Creator<AppItemInfo> CREATOR = new Creator<AppItemInfo>() {
		 
	    @Override
	    public AppItemInfo createFromParcel(Parcel source) {
	       return new AppItemInfo(source);
	    }
	 
	   @Override
	   public AppItemInfo[] newArray(int size) {
	      return new AppItemInfo[size];
	   }
	};
	
	
}
