/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view;

import com.simplesoft.simplesappspermissions.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * BaseFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 09:39:33 19 Jul 2014
 */
public class BaseFragment extends Fragment {
	
	protected BaseActivity parent;
	protected LinearLayout viewRoot;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_base, null, false);
		viewRoot = (LinearLayout) view.findViewById(R.id.llMain);
		viewRoot.addView(container);
		return view;
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		parent = (BaseActivity) activity;
	}


	/**
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 08:48:20 20 Jul 2014
	 * @return: void
	 * @throws:  
	 */
	public String getTAG() {
		return this.getClass().getName();
	}

}
