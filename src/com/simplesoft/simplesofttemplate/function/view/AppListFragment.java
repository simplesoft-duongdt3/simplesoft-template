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
import android.widget.ListView;
import android.widget.TextView;

import com.simplesoft.simpleappspermissions.R;
import com.simplesoft.simplesofttemplate.constance.PermissionGroup;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.function.DTO.ListAppItemInfo;
import com.simplesoft.simplesofttemplate.function.viewholder.ViewHolderAppDetail;
import com.simplesoft.simplesofttemplate.function.viewholder.ViewHolderAppList;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.MultiComparator;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.MultiCondition;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.Operator;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.OrderBy;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.BaseDialogFragment;
import com.simplesoft.simplesofttemplate.main.view.BaseFragment;
import com.simplesoft.simplesofttemplate.main.view.BroadCastAction;
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
public class AppListFragment extends BaseFragment  implements ListViewEventReceiver<AppItemInfo> {
	ListView appList;
	private TextView tvNumApps;

	private ListAppItemInfo listAppDto;
	private List<AppItemInfo> arrFilter;
	private BaseListAdapter<AppItemInfo> adapter;
	private MultiCondition<AppItemInfo> condition;
	private PermissionGroup group;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup vgroup = (ViewGroup) inflater.inflate(R.layout.frag_app_list, container, false);
		appList = (ListView) vgroup.findViewById(R.id.appList);
		tvNumApps = (TextView) vgroup.findViewById(R.id.tvNumApps);

		if (getArguments().containsKey(BundleKey.DATA_APP_LIST.getName())) {
			listAppDto = (ListAppItemInfo) getArguments().getParcelable(BundleKey.DATA_APP_LIST.getName());
		} else {
			if (savedInstanceState.containsKey(BundleKey.DATA_APP_LIST.getName())) {
				listAppDto = (ListAppItemInfo) savedInstanceState.getParcelable(BundleKey.DATA_APP_LIST.getName());
			}
		}
		
		if (listAppDto != null) {
			filterAndSort(AppInfo.getInstance().opSystemApp, AppInfo.getInstance().strQueryApp);
		}
		return super.onCreateView(inflater, vgroup, savedInstanceState);
	}

	BaseDialogFragment<AppItemInfo> fragDialog = null;
	@Override
	public void handleListViewSendEvent(ListViewEventData<AppItemInfo> data) {
		if (fragDialog == null) {
			ViewHolderAppDetail viewHolder = new ViewHolderAppDetail(group);
			fragDialog = new BaseDialogFragment<AppItemInfo>(data.dto, viewHolder, null, true);
		} else{
			fragDialog.renderDialog(data.dto);
			fragDialog.dismiss();
		}
		
		showDialog(fragDialog);
	}
	
	/**
	 * mo ta ham
	 * @author: Nguyen Xuan Dung
	 * @param op
	 * @return void
	 */
	private void filterAndSort(Operator opSystemApp, String strFilter) {
		if (this.condition != null) {
			//them 2 dieu kien chung
			this.condition.addCondition(new AppItemInfo.IsSystemAppCondition().setOperator(opSystemApp))
				.addCondition(new AppItemInfo.CheckNameCondition(strFilter).setOperator(Operator.IS));
		}
		
		MultiComparator<AppItemInfo> comparetor = new MultiComparator<AppItemInfo>()
				.addComparator(new AppItemInfo.PerComparator().setWay(OrderBy.DESC))
				.addComparator(new AppItemInfo.NameComparator().setWay(OrderBy.ASC));
		
		//hiển thị danh sách
		List<AppItemInfo> arrTemp = CollectionUtil.filter(listAppDto.listApp
				, this.condition
				, null);
		CollectionUtil.sort(arrTemp, comparetor);
		
		//change title activity
		//parent.setTitleActivity(arrTemp.size() + StringUtil.getString(R.string.text_app));
		
		if (adapter == null) {
			arrFilter = arrTemp;
			adapter = new BaseListAdapter<AppItemInfo>(arrFilter, new ViewHolderAppList(), this);
			appList.setAdapter(adapter);
		} else{
			arrFilter.clear();
			arrFilter.addAll(arrTemp);
			appList.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
		
		tvNumApps.setText(StringUtil.getString(R.string.text_num_app) + " " + adapter.getCount());
	}

	@Override
	public void doActionBroadCast(BroadCastAction action, Bundle data) {
		switch (action) {
		case SEARCH:
		case FILTER_SYSTEM_APPS:
			filterAndSort(AppInfo.getInstance().opSystemApp, AppInfo.getInstance().strQueryApp);
		default:
			break;
		}
	}

	/**
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 21:50:47 7 Aug 2014
	 * @return: void
	 * @throws:  
	 * @param group2
	 */
	public void setGroup(PermissionGroup pGroup) {
		this.group = pGroup;
	}

	/**
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 21:51:09 7 Aug 2014
	 * @return: void
	 * @throws:  
	 * @param multiCondition
	 */
	public void setMultiCondition(MultiCondition<AppItemInfo> pCondition) {
		this.condition = pCondition;
	}
}
