/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.function.viewholder.ViewHolderAppList;
import com.simplesoft.simplesofttemplate.main.controller.ResponseData;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.BaseFragment;
import com.simplesoft.simplesofttemplate.main.view.control.BaseListAdapter;
import com.simplesoft.simplesofttemplate.main.view.control.ListViewEventData;
import com.simplesoft.simplesofttemplate.main.view.control.ListViewEventReceiver;

/**
 * AppListFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 10:10:44 19 Jul 2014
 */
public class AppListFragment extends BaseFragment implements ListViewEventReceiver<AppItemInfo>  {
	
	ListView appList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup vgroup = (ViewGroup) inflater.inflate(R.layout.frag_app_list, null, false);
		appList = (ListView) vgroup.findViewById(R.id.appList);
		ListAdapter adapter = new BaseListAdapter<AppItemInfo>(AppInfo.getInstance().listAppInfo, new ViewHolderAppList(), this);
		appList.setAdapter(adapter);
		return super.onCreateView(inflater, vgroup, savedInstanceState);
	}

	@Override
	public void handleViewDataResponse(ResponseData rspData) {
		
	}

	@Override
	public void onListViewSendEvent(ListViewEventData<AppItemInfo> data) {
		Toast.makeText(parent, data.action.toString() + " " + data.dto.name, Toast.LENGTH_SHORT).show();
	}

}
