package com.simplesoft.simplesofttemplate.function.view;

import android.os.Bundle;

import com.simplesoft.simplesofttemplate.main.view.BaseActivityFragment;

public class MainFragmentActivity extends BaseActivityFragment {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//switchActivity(MainActivity.class);
	}
	
	@Override
	protected void onDrawMenuChange(int position) {
		
	}
	
	@Override
	protected boolean isShowAdsWhenStop() {
		return true;
	}


	@Override
	protected boolean isShowDrawMenu() {
		return false;
	}

	@Override
	protected boolean isShowAdsWhenStart() {
		return true;
	}
	
}
