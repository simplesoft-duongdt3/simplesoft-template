package com.simplesoft.simplesofttemplate.main.view;

import android.os.Bundle;
import android.view.View;

import com.simplesoft.simplesofttemplate.R;
import com.simplesoft.simplesofttemplate.function.view.MainActivity;
import com.simplesoft.simplesofttemplate.main.controller.BaseController.RequestAction;
import com.simplesoft.simplesofttemplate.main.controller.BaseController.ResponseData;

public class SplashScreenActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
/*		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		super.onCreate(savedInstanceState);
        llAds.setVisibility(View.GONE);
        getLayoutInflater().inflate(R.layout.splash_screen_layout, fragHolder, true);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		sendViewRequest(RequestAction.GET_LIST_APP);
	}

	@Override
	protected void onDrawMenuChange(int position) {
		
	}
	
	@Override
	protected boolean isShowDrawMenu() {
		return false;
	}

	@Override
	protected boolean isShowAdsWhenStop() {
		return false;
	}

	@Override
	protected boolean isShowAdsWhenStart() {
		return false;
	}

	@Override
	public void handleViewDataResponse(ResponseData rspData) {
		switchActivity(MainActivity.class);
   	 	SplashScreenActivity.this.finish();
	}
}
