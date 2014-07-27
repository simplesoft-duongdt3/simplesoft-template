package com.simplesoft.simplesofttemplate.main.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.ListAppItemInfo;
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

	@Override
	public void handleViewDataResponseSuccess(ResponseData rspData) {
		switch (rspData.rqData.action) {
			case GET_LIST_APP:
				AppInfo.getInstance().listData = (ListAppItemInfo) rspData.data;
				//final Bundle b = new Bundle();
				//b.putParcelable(BundleKey.DATA_APP_LIST.getName(), data);
				
				//nếu thời gian thực thi trong khoảng cho phép 
				//thì hiện thêm Splash Screen 1s nữa
				if (rspData.rqData.timeExecute <= 2500) {
					new CountDownTimer(1000, 1000) {
						
						public void onTick(long millisUntilFinished) {
							
						}
						
						public void onFinish() {
							switchActivity(MainActivity.class);
							SplashScreenActivity.this.finish();
						}
					}.start();
				} else {
					//quá thời gian thì tiến hành chuyển Activity luôn
					switchActivity(MainActivity.class);
					SplashScreenActivity.this.finish();
				}
				break;
	
			default:
				break;
		}
	}

	@Override
	protected void onViewPagerChange(int pos) {
		
	}

	@Override
	public void doActionBroadCast(BroadCastAction action, Bundle data) {
		
	}
}
