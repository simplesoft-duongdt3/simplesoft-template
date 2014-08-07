/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplesoft.simpleappspermissions.R;
import com.simplesoft.simplesofttemplate.constance.PermissionGroup;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo.ItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.control.BaseViewHolder;

/**
 * ViewHolderItemInfo.java
 * 
 * @author: duongdt3
 * @version: 1.0
 * @since: 1.0
 * @time: 15:54:39 29 Jul 2014
 */
public class ViewHolderItemInfo extends BaseViewHolder<ItemInfo> {

	LayoutInflater inflater = (LayoutInflater) AppInfo.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	private TextView tvName;
	private TextView tvGroup;
	private PermissionGroup group;

	public ViewHolderItemInfo(PermissionGroup group){
		this.group = group;
	}
	
	@Override
	public View initView(ViewGroup parent) {
		View rowView = inflater.inflate(R.layout.item_permission, parent, false);
		tvName = (TextView)rowView.findViewById(R.id.tvName);
		tvGroup = (TextView)rowView.findViewById(R.id.tvGroup);
		return rowView;
	}

	@Override
	protected void resetView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void renderView(ItemInfo dto, int pos) {
		tvName.setText(dto.name);
		String detailApp = StringUtil.getStringResourceByName(dto.name, StringUtil.getString(R.string.text_group) + ": " + dto.group);
		tvGroup.setText(detailApp);
		boolean isInGroup = group.contain(dto);
		if(isInGroup){
			tvName.setTextColor(AppInfo.getInstance().getResources().getColor(android.R.color.holo_blue_dark));
		} else {
			tvName.setTextColor(AppInfo.getInstance().getResources().getColor(android.R.color.white));
		}
		
	}

	@Override
	public BaseViewHolder<ItemInfo> clone() {
		return new ViewHolderItemInfo(group);
	}

}
