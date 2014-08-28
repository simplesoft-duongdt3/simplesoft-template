package com.simplesoft.simplesofttemplate.function.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplesoft.simplesofttemplate.function.DTO.ListViewItemInfo;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.control.BaseViewHolder;
import com.simplesoft.simplesysteminfo.R;

/**
 * ViewHolderListViewItemInfo.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 11:35:23 8 Aug 2014
 */
public class ViewHolderListViewItemInfo extends BaseViewHolder<ListViewItemInfo> {

	LayoutInflater inflater = (LayoutInflater) AppInfo.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	ImageView ivIcon;
	TextView tvName;
	
	@Override
	public View initView(ViewGroup parent) {
		View rowView = inflater.inflate(R.layout.list_view_item, parent, false);
		this.ivIcon = (ImageView) rowView.findViewById(R.id.ivIcon);
		this.tvName = (TextView) rowView.findViewById(R.id.tvName);
		rowView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendViewHolderEvent(dto.action);
			}
		});
		return rowView;
	}

	@Override
	protected void resetView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void renderView(ListViewItemInfo dto, int pos) {
		//load info
		this.tvName.setText(dto.name);
		this.ivIcon.setImageResource(dto.icon);
	}

	@Override
	public BaseViewHolder<ListViewItemInfo> clone() {
		return new ViewHolderListViewItemInfo();
	}

}
