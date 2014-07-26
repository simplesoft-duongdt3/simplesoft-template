/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.view;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.function.DTO.ListAppItemInfo;
import com.simplesoft.simplesofttemplate.function.viewholder.ViewHolderAppList;
import com.simplesoft.simplesofttemplate.main.controller.ResponseData;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.MultiComparator;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.MultiCondition;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.Operator;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.OrderBy;
import com.simplesoft.simplesofttemplate.main.view.BaseFragment;
import com.simplesoft.simplesofttemplate.main.view.BundleKey;
import com.simplesoft.simplesofttemplate.main.view.control.BaseListAdapter;
import com.simplesoft.simplesofttemplate.main.view.control.ListViewEventData;
import com.simplesoft.simplesofttemplate.main.view.control.ListViewEventReceiver;

/**
 * AppPaidFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 13:14:19 19 Jul 2014
 */
public class AppPaidFragment extends BaseFragment  implements ListViewEventReceiver<AppItemInfo>, OnCheckedChangeListener {
	ListView appList;
	private CheckBox cbIsGetSysApp;
	private TextView tvNumApps;

	private ListAppItemInfo listAppDto;
	private List<AppItemInfo> arrFilter;
	private ListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup vgroup = (ViewGroup) inflater.inflate(R.layout.frag_app_list, container, false);
		appList = (ListView) vgroup.findViewById(R.id.appList);
		cbIsGetSysApp = (CheckBox) vgroup.findViewById(R.id.cbIsGetSysApp);
		cbIsGetSysApp.setOnCheckedChangeListener(this);
		tvNumApps = (TextView) vgroup.findViewById(R.id.tvNumApps);

		if (getArguments().containsKey(BundleKey.DATA_APP_LIST.getName())) {
			listAppDto = (ListAppItemInfo) getArguments().getParcelable(BundleKey.DATA_APP_LIST.getName());
		} else {
			if (savedInstanceState.containsKey(BundleKey.DATA_APP_LIST.getName())) {
				listAppDto = (ListAppItemInfo) savedInstanceState.getParcelable(BundleKey.DATA_APP_LIST.getName());
			}
		}
		
		if (listAppDto != null) {
			filterAndSort(Operator.NOT);
			tvNumApps.setText(StringUtil.getString(R.string.text_num_app) + " " + arrFilter.size());

			//hiển thị danh sách
			ListAdapter adapter = new BaseListAdapter<AppItemInfo>(arrFilter, new ViewHolderAppList(), this);
			appList.setAdapter(adapter);
		}
		return super.onCreateView(inflater, vgroup, savedInstanceState);
	}

	@Override
	public void handleViewDataResponseSuccess(ResponseData rspData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onListViewSendEvent(ListViewEventData<AppItemInfo> data) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (buttonView == cbIsGetSysApp) {
			if (isChecked) {
				filterAndSort(Operator.IS);
			} else {
				filterAndSort(Operator.NOT);
			}
			tvNumApps.setText(StringUtil.getString(R.string.text_num_app) + " " + arrFilter.size());
			adapter = new BaseListAdapter<AppItemInfo>(arrFilter, new ViewHolderAppList(), this);
			appList.setAdapter(adapter);
		}
	}
	
	/**
	 * mo ta ham
	 * @author: Nguyen Xuan Dung
	 * @param op
	 * @return void
	 */
	private void filterAndSort(Operator... op) {
		arrFilter = CollectionUtil.filter(listAppDto.listApp
				,new MultiCondition<AppItemInfo>()
				.addCondition(new AppItemInfo.IsSystemAppCondition().setOperator(op[0]))
				.addCondition(new AppItemInfo.IsCostMoneyApp().setOperator(Operator.IS))
				, new AppItemInfo.ActionSetDrawable());
		MultiComparator<AppItemInfo> comparetor = new MultiComparator<AppItemInfo>()
				.addComparator(new AppItemInfo.PerComparator().setWay(OrderBy.DESC))
				.addComparator(new AppItemInfo.NameComparator().setWay(OrderBy.ASC));
		CollectionUtil.sort(arrFilter, comparetor);
	}
}
