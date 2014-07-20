/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.adapter.AppListAdapter;
import com.simplesoft.simplesofttemplate.main.controller.BaseController.ResponseData;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.BaseFragment;

/**
 * AppListFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 10:10:44 19 Jul 2014
 */
public class AppListFragment extends BaseFragment {
	
	ListView appList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup vgroup = (ViewGroup) inflater.inflate(R.layout.frag_app_list, null, false);
		appList = (ListView) vgroup.findViewById(R.id.appList);
		AppListAdapter adapter = new AppListAdapter(parent, AppInfo.getInstance().listAppInfo);
		appList.setAdapter(adapter);
		return super.onCreateView(inflater, vgroup, savedInstanceState);
	}

	@Override
	public void handleViewDataResponse(ResponseData rspData) {
		// TODO Auto-generated method stub
		
	}

}
