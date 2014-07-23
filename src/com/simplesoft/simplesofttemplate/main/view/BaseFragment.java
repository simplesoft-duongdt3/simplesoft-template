/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.main.controller.IRequestView;
import com.simplesoft.simplesofttemplate.main.controller.RequestAction;
import com.simplesoft.simplesofttemplate.main.controller.RequestData;
import com.simplesoft.simplesofttemplate.main.controller.ResponseData;
import com.simplesoft.simplesofttemplate.main.controller.SimpleController;

/**
 * BaseFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 09:39:33 19 Jul 2014
 */
public class BaseFragment extends Fragment implements IRequestView{
	
	protected BaseActivity parent;
	protected LinearLayout viewRoot;
	
	public BaseFragment() {
		super();
	}
	
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
	
	public void sendViewRequest(RequestAction rqAction) {
		sendViewRequest(rqAction, new Bundle());
	}
	
	public void sendViewRequest(RequestAction rqAction, Bundle data) {
		RequestData rData = new RequestData();
		rData.action = rqAction;
		rData.sender = this;
		rData.viewData = data;
		SimpleController.getInstance().handleViewRequest(rData);
	}
	
	@Override
	public void handleViewDataResponseError(ResponseData rspData) {
		Toast.makeText(parent, rspData.errorMessage, Toast.LENGTH_LONG).show();
	}
		
	@Override
	public void handleViewDataResponseSuccess(ResponseData rspData) {
		Toast.makeText(parent, rspData.rqData.action.getRquestName() + " ", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public Activity getActivityContext() {
		return parent;
	}

}
