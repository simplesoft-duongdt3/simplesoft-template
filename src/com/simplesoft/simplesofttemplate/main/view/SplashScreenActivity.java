package com.simplesoft.simplesofttemplate.main.view;

import java.util.List;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.function.view.MainActivity;
import com.simplesoft.simplesofttemplate.main.controller.RequestAction;
import com.simplesoft.simplesofttemplate.main.controller.ResponseData;

public class SplashScreenActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
/*		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		super.onCreate(savedInstanceState);
        llAds.setVisibility(View.GONE);
        getLayoutInflater().inflate(R.layout.splash_screen_layout, fragHolder, true);
        
        sendViewRequest(RequestAction.GET_LIST_APP);
	}

	@Override
	protected void onResume() {
		super.onResume();
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

	@SuppressWarnings("unchecked")
	@Override
	public void handleViewDataResponse(ResponseData rspData) {
		switch (rspData.rqData.action) {
			case GET_LIST_APP:
				List<AppItemInfo> data = (List<AppItemInfo>) rspData.data;
				AppInfo.getInstance().listAppInfo = data;
				
				new CountDownTimer(1000, 1000) {
					
					public void onTick(long millisUntilFinished) {
					}
					
					public void onFinish() {
						switchActivity(MainActivity.class);
						SplashScreenActivity.this.finish();
					}
				}.start();
				break;
	
			default:
				break;
		}
	}

	@Override
	protected void onViewPagerChange(int pos) {
		
	}
}
