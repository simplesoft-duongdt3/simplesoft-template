/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.DTO;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public final class ListAppItemInfo implements Parcelable{
	public List<AppItemInfo> listApp;
	
	public ListAppItemInfo() {
		listApp = new ArrayList<AppItemInfo>();
	}
	
	public ListAppItemInfo(Parcel parcel){
		this();
		parcel.readList(listApp, AppItemInfo.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(listApp);
	}
	
	public static final Creator<ListAppItemInfo> CREATOR = new Creator<ListAppItemInfo>() {
		 
	    @Override
	    public ListAppItemInfo createFromParcel(Parcel source) {
	       return new ListAppItemInfo(source);
	    }
	 
	   @Override
	   public ListAppItemInfo[] newArray(int size) {
	      return new ListAppItemInfo[size];
	   }
	};
}