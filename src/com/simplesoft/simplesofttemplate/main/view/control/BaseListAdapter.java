/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view.control;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;

/**
 * AppListAdapter.java
 * @author: duongdt3
 * @version: 1.0 
 * @param <T>
 * @since:  1.0
 * @time: 11:37:53 21 Jul 2014
 */
public class BaseListAdapter<T> extends ArrayAdapter<T> {
	
	List<T> listObject;
	BaseViewHolder<T> viewHolder;
	ListViewEventReceiver<T> eventReceiver;
	public BaseListAdapter(List<T> objects, BaseViewHolder<T> viewHolder, ListViewEventReceiver<T> eventReceiver) {
		super(AppInfo.getInstance(), R.layout.app_list_item, objects);
		this.listObject = objects;
		this.viewHolder = viewHolder; 
		this.eventReceiver = eventReceiver;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		
		if(rowView == null){
			BaseViewHolder<T> vHolder = this.viewHolder.clone();
			vHolder.setListViewEventReceiver(eventReceiver);
			rowView = vHolder.initView(parent);
			rowView.setTag(vHolder);
		}
		
		BaseViewHolder<T> vHolder = (BaseViewHolder<T>) rowView.getTag();
		T dto = listObject.get(position);
		vHolder.render(dto);
		return rowView;
	}

	/**
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 12:44:51 27 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param listApp
	 * @param condition
	 * @param actionSetDrawable
	 * @param comparetor
	 *//*
	public synchronized void filterAndSort(List<T> listObjectOrginal,
			ICondition<T> condition,
			IDoAction<T> doAction,
			MultiComparator<T> comparetor) {
		//nếu yêu cầu filter sẽ filter
		listObject.clear();
		for (T element : listObjectOrginal) {
			// nếu thoả thì add vào mảng
			if (condition == null || condition.isVailCondition(element)) {
				if (doAction != null) {
					doAction.doAction(element);
				}
				listObject.add(element);
			}
		}
		
		//nếu yêu cầu sort sẽ sort
		if (comparetor != null) {
			CollectionUtil.sort(listObject, comparetor);
		}
		
		this.notifyDataSetChanged();
	}*/
	
}
