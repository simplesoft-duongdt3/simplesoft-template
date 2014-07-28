/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view.control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;

/**
 * CheckBoxActionProvider.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 10:57:34 28 Jul 2014
 */
public class CheckBoxActionProvider extends ActionProvider implements OnCheckedChangeListener {

	IViewActionSender iViewActionSender;
	
	public CheckBoxActionProvider(Context context) {
		super(context);
	}
 
	@SuppressLint("InflateParams")
	@Override
	public View onCreateActionView() {
		// Inflate the action view to be shown on the action bar.
		LayoutInflater layoutInflater = LayoutInflater.from(AppInfo.getInstance());
		View view = layoutInflater.inflate(R.layout.checkbox_action_provider, null);
 
		CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);
		checkbox.setOnCheckedChangeListener(this);
		return view;
	}
 
 
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.checkbox:			
			if (this.iViewActionSender != null) {
				this.iViewActionSender.sendViewAction(isChecked);
			}
		}
 
	}

	/**
	 * set IViewActionSender if need send data
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 11:09:03 28 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param iViewActionSender
	 */
	public void setViewActionSender(IViewActionSender pViewActionSender) {
		this.iViewActionSender = pViewActionSender;
	}
	
}
