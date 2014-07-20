/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.main.view.BaseFragment;

/**
 * AppPaidFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 13:14:19 19 Jul 2014
 */
public class AppPaidFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup vgroup = (ViewGroup) inflater.inflate(R.layout.frag_app_paid, null, false);
		return super.onCreateView(inflater, vgroup, savedInstanceState);
	}
}
