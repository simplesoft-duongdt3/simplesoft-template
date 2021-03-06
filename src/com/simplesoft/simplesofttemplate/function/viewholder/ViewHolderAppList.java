/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.viewholder;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplesoft.simplesappstemplate.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.control.BaseViewHolder;
import com.simplesoft.simplesofttemplate.main.view.control.ListViewEventAction;

/**
 * ViewHolderAppList.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 11:47:41 21 Jul 2014
 */
public class ViewHolderAppList extends BaseViewHolder<AppItemInfo> {

	PackageManager pm = AppInfo.getInstance().getPackageManager();
	LayoutInflater inflater = (LayoutInflater) AppInfo.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	ImageView ivAppIcon;
	TextView tvAppName;
	TextView tvVersion;
	TextView tvNumPermission;
	
	@Override
	public View initView() {
		View rowView = inflater.inflate(R.layout.app_list_item, null, false);
		this.ivAppIcon = (ImageView) rowView.findViewById(R.id.ivAppIcon);
		this.tvAppName = (TextView) rowView.findViewById(R.id.tvAppName);
		this.tvVersion = (TextView) rowView.findViewById(R.id.tvVersion);
		this.tvNumPermission = (TextView) rowView.findViewById(R.id.tvNumPermission);
		rowView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendViewHolderEvent(ListViewEventAction.VIEW_APP_DETAIL_IN_APP_LIST);
			}
		});
		return rowView;
	}
	
	@Override
	public void resetView() {
		
	}
	
	@Override
	public void renderView(AppItemInfo dto) {
		Drawable icon = null;
		try {
			icon = pm.getApplicationIcon(dto.packageName);
			this.ivAppIcon.setImageDrawable(icon);
		} catch (NameNotFoundException e) {
			LogUtil.log(e);
		}
		this.tvAppName.setText(dto.name);
		this.tvVersion.setText(StringUtil.getString(R.string.text_version) + ": " + dto.versionName);
		this.tvNumPermission.setText(String.valueOf(dto.permissions.length));
	}

	@Override
	public BaseViewHolder<AppItemInfo> clone() {
		return new ViewHolderAppList();
	}

}
