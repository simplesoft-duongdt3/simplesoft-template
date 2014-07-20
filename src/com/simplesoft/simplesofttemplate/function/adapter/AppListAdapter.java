package com.simplesoft.simplesofttemplate.function.adapter;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Mo ta muc dich cua class
 * @author: DungNX
 * @version: 1.0
 * @since: 1.0
 */

public class AppListAdapter extends ArrayAdapter<AppItemInfo> {

	/**
	 * @param context
	 * @param resource
	 * @param objects
	 */
	public AppListAdapter(Context context, AppItemInfo[] objects) {
		super(context, R.layout.app_list_item, objects);
		// TODO Auto-generated constructor stub
		
	}

}
