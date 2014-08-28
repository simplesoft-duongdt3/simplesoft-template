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

import com.simplesoft.simplesofttemplate.function.DTO.ItemInfo;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.control.BaseViewHolder;
import com.simplesoft.simplesysteminfo.R;

/**
 * ViewHolderInfo.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 00:36:43 29 Aug 2014
 */
public class ViewHolderInfo extends BaseViewHolder<ItemInfo> {

	LayoutInflater inflater = (LayoutInflater) AppInfo.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	TextView tvTitle;
	TextView tvInfo;
	
	@Override
	public View initView(ViewGroup parent) {
		View rowView = inflater.inflate(R.layout.item_info, parent, false);
		this.tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
		this.tvInfo = (TextView) rowView.findViewById(R.id.tvInfo);
		return rowView;
	}

	@Override
	protected void resetView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void renderView(ItemInfo dto, int pos) {
		this.tvTitle.setText(dto.title);
		this.tvInfo.setText(dto.info);
	}

	@Override
	public BaseViewHolder<ItemInfo> clone() {
		return new ViewHolderInfo();
	}

}
