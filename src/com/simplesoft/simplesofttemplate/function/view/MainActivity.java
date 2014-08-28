/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.view;

import android.os.Bundle;

import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.BaseActivity;
import com.simplesoft.simplesofttemplate.main.view.BaseFragment;
import com.simplesoft.simplesofttemplate.main.view.BroadCastAction;
import com.simplesoft.simplesofttemplate.main.view.ViewPagerInfo;
import com.simplesoft.simplesysteminfo.R;

/**
 * MainActivity.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 22:36:05 28 Aug 2014
 */
public class MainActivity extends BaseActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String [] tabTitle = new String[] {StringUtil.getString(R.string.hardware_info), StringUtil.getString(R.string.software_info) };
		BaseFragment [] arrFragment = new BaseFragment[] {new HardwareInfoFragment(), new SoftwareInfoFragment()}; 
		ViewPagerInfo vPagerInfo = new ViewPagerInfo(tabTitle, arrFragment, getSupportFragmentManager());
		setViewPagerInfo(vPagerInfo );
	}
	
	
	@Override
	public void doActionBroadCast(BroadCastAction action, Bundle data) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isShowAdSlider() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isShowAdsWhenStop() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isShowAdsWhenStart() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isShowDrawMenu() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onDrawMenuChange(int position) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onViewPagerChange(int pos) {
		// TODO Auto-generated method stub

	}

}
