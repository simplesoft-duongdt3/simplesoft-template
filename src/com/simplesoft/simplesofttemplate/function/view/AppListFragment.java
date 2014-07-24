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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.function.viewholder.ViewHolderAppList;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.MultiComparator;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.MultiCondition;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.Operator;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.OrderBy;
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
		
		if (AppInfo.getInstance().listAppInfo != null) {
			//lọc danh sách những apps có quyền > 0 + không phải ứng dụng hệ thống
			List<AppItemInfo> arrFilter = CollectionUtil.filter(AppInfo.getInstance().listAppInfo
					, new MultiCondition<AppItemInfo>()
					.addCondition(new AppItemInfo.HavePerCondition().setOperator(Operator.IS))
					.addCondition(new AppItemInfo.IsSystemAppCondition().setOperator(Operator.NOT)));
			
			//sắp xếp theo số lượng Per giảm dần + theo tên tăng dần
			MultiComparator<AppItemInfo> comparetor = new MultiComparator<AppItemInfo>()
					.addComparator(new AppItemInfo.PerComparator().setWay(OrderBy.DESC))
					.addComparator(new AppItemInfo.NameComparator().setWay(OrderBy.ASC));
			CollectionUtil.sort(arrFilter, comparetor);
			
			//hiển thị danh sách
			ListAdapter adapter = new BaseListAdapter<AppItemInfo>(arrFilter, new ViewHolderAppList(), this);
			appList.setAdapter(adapter);
		}
		return super.onCreateView(inflater, vgroup, savedInstanceState);
	}

	@Override
	public void onListViewSendEvent(ListViewEventData<AppItemInfo> data) {
		Toast.makeText(parent, data.action.toString() + " " + data.dto.name, Toast.LENGTH_SHORT).show();
	}

}
