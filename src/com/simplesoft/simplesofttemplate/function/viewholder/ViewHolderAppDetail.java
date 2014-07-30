/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.viewholder;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.simplesoft.simpleappspermissions.R;
import com.simplesoft.simplesofttemplate.constance.PermissionGroup;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo.ItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.control.BaseListAdapter;
import com.simplesoft.simplesofttemplate.main.view.control.BaseViewHolder;

/**
 * ViewHolderAppDetail.java
 * 
 * @author: duongdt3
 * @version: 1.0
 * @since: 1.0
 * @time: 13:31:33 29 Jul 2014
 */
public class ViewHolderAppDetail extends BaseViewHolder<AppItemInfo> implements OnClickListener {

	ImageView ivAppIcon;
	TextView tvAppName;
	private TextView tvVersion;
	private Button btAbout;
	private Button btUninstall;
	private ListView lvPermissions;
	private BaseListAdapter<ItemInfo> adapter;
	private List<ItemInfo> arrFilter;
	private AppItemInfo dto;
	private PermissionGroup group;

	public void setGroup(PermissionGroup group) {
		this.group = group;
	}

	LayoutInflater inflater = (LayoutInflater) AppInfo.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	@Override
	public View initView(ViewGroup parent) {
		View rowView = inflater.inflate(R.layout.item_appitem, parent, false);
		ivAppIcon = (ImageView) rowView.findViewById(R.id.ivAppIcon);
		tvAppName = (TextView) rowView.findViewById(R.id.tvAppName);
		tvVersion = (TextView) rowView.findViewById(R.id.tvVersion);
		btAbout = (Button) rowView.findViewById(R.id.btAbout);
		btUninstall = (Button) rowView.findViewById(R.id.btUninstall);
		lvPermissions = (ListView) rowView.findViewById(R.id.lvPermissions);
		
		btAbout.setOnClickListener(this);
		btUninstall.setOnClickListener(this);
		return rowView;
	}

	@Override
	protected void resetView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void renderView(AppItemInfo dto) {
		this.dto = dto;
		ivAppIcon.setImageDrawable(dto.drawable);
		tvAppName.setText(dto.name);
		tvVersion.setText(StringUtil.getString(R.string.text_version) + ": " + dto.versionName);
		
		List<ItemInfo> arrTemp = new ArrayList<AppItemInfo.ItemInfo>();
		arrTemp.addAll(dto.permissions);
		arrTemp.addAll(dto.userPermissions);
		arrTemp.addAll(dto.services);
		arrTemp.addAll(dto.providers);
		arrTemp.addAll(dto.receivers);
		
		if (adapter == null) {
			arrFilter = arrTemp ;
			adapter = new BaseListAdapter<ItemInfo>(arrFilter, new ViewHolderItemInfo(group), null);
			lvPermissions.setAdapter(adapter);
		} else{
			arrFilter.clear();
			arrFilter.addAll(arrTemp);
			lvPermissions.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public BaseViewHolder<AppItemInfo> clone() {
		return new ViewHolderAppDetail();	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == btAbout){
			showAbout(dto.packageName);
		} else if(v == btUninstall){
			showUninstall(dto.packageName);
		}
	}
	
	/**
	 * Mo ta muc dich cua ham
	 * @author: DungNX
	 * @param packageName
	 * @return: void
	 * @throws:
	*/
	private void showAbout(String packageName){
        Uri packageUri = Uri.parse("package:" + packageName);
        Intent appInfoIntent =
          new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageUri);
        AppInfo.getInstance().getActivityContext().startActivity(appInfoIntent);
	}

	/**
	 * Mo ta muc dich cua ham
	 * @author: DungNX
	 * @param packageName
	 * @return: void
	 * @throws:
	*/
	private void showUninstall(String packageName){
        Uri packageUri = Uri.parse("package:" + packageName);
        Intent uninstallIntent =
          new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
        AppInfo.getInstance().getActivityContext().startActivity(uninstallIntent);
	}
}
