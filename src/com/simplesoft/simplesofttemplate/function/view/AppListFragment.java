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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.function.DTO.ListAppItemInfo;
import com.simplesoft.simplesofttemplate.function.viewholder.ViewHolderAppList;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil;
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
 * AppListFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 10:10:44 19 Jul 2014
 */
public class AppListFragment extends BaseFragment implements ListViewEventReceiver<AppItemInfo>, OnCheckedChangeListener  {

	private ListView appList;
	private CheckBox cbIsGetSysApp;
	
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
		
		if (getArguments().containsKey(BundleKey.DATA_APP_LIST.getName())) {
			listAppDto = (ListAppItemInfo) getArguments().getParcelable(BundleKey.DATA_APP_LIST.getName());
		} else {
			if (savedInstanceState.containsKey(BundleKey.DATA_APP_LIST.getName())) {
				listAppDto = (ListAppItemInfo) savedInstanceState.getParcelable(BundleKey.DATA_APP_LIST.getName());
			}
		}
		
		if (listAppDto != null) {
			filterAndSort(Operator.NOT);
			
			//hiá»ƒn thá»‹ danh sÃ¡ch
			adapter = new BaseListAdapter<AppItemInfo>(arrFilter, new ViewHolderAppList(), this);
			appList.setAdapter(adapter);
		}
		return super.onCreateView(inflater, vgroup, savedInstanceState);
	}

	@Override
	public void onListViewSendEvent(ListViewEventData<AppItemInfo> data) {
		Toast.makeText(parent, data.action.toString() + " " + data.dto.name, Toast.LENGTH_SHORT).show();
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
		//lá»�c danh sÃ¡ch nhá»¯ng apps cÃ³ quyá»�n > 0 + khÃ´ng pháº£i á»©ng dá»¥ng há»‡ thá»‘ng
		arrFilter = CollectionUtil.filter(listAppDto.listApp
				,new MultiCondition<AppItemInfo>()
				.addCondition(new AppItemInfo.IsSystemAppCondition().setOperator(op[0]))
				, new AppItemInfo.ActionSetDrawable());
		
		//sáº¯p xáº¿p theo sá»‘ lÆ°á»£ng Per giáº£m dáº§n + theo tÃªn tÄƒng dáº§n
		MultiComparator<AppItemInfo> comparetor = new MultiComparator<AppItemInfo>()
				.addComparator(new AppItemInfo.PerComparator().setWay(OrderBy.DESC))
				.addComparator(new AppItemInfo.NameComparator().setWay(OrderBy.ASC));
		CollectionUtil.sort(arrFilter, comparetor);
	}

}
