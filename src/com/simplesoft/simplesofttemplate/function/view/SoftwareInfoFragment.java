/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.simplesoft.simplesofttemplate.function.DTO.ItemInfo;
import com.simplesoft.simplesofttemplate.function.viewholder.ViewHolderInfo;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.BaseFragment;
import com.simplesoft.simplesofttemplate.main.view.BroadCastAction;
import com.simplesoft.simplesofttemplate.main.view.control.BaseListAdapter;
import com.simplesoft.simplesysteminfo.R;

/**
 * SoftwareInfoFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 00:18:44 29 Aug 2014
 */
public class SoftwareInfoFragment extends BaseFragment {

	@Override
	@SuppressLint("InflateParams")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup vgroup = (ViewGroup) inflater.inflate(R.layout.frag_list_view, container, false);
		ListView lvInfo = (ListView) vgroup.findViewById(R.id.lvInfo);
		
		List<ItemInfo> arrFilter = new ArrayList<ItemInfo>();
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.codename), Build.VERSION.CODENAME));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.release), Build.VERSION.RELEASE));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.sdk), Build.VERSION.SDK_INT + ""));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.incremental), Build.VERSION.INCREMENTAL));
		
		BaseListAdapter<ItemInfo> adapter = new BaseListAdapter<ItemInfo>(arrFilter , new ViewHolderInfo(), null);
		lvInfo.setAdapter(adapter);
		return super.onCreateView(inflater, vgroup, savedInstanceState);
	}
	
	@Override
	public void doActionBroadCast(BroadCastAction action, Bundle data) {
		// TODO Auto-generated method stub

	}

}
