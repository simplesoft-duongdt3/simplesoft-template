package com.simplesoft.simplesofttemplate.main.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.simplesoft.simpleappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.ListAppItemInfo;
import com.simplesoft.simplesofttemplate.function.view.MainActivity;
import com.simplesoft.simplesofttemplate.main.controller.IRequestView;
import com.simplesoft.simplesofttemplate.main.controller.RequestAction;
import com.simplesoft.simplesofttemplate.main.controller.RequestData;
import com.simplesoft.simplesofttemplate.main.controller.ResponseData;
import com.simplesoft.simplesofttemplate.main.controller.SimpleController;

public class SplashScreenActivity extends Activity implements IRequestView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
/*		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen_layout);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sendViewRequest(RequestAction.GET_LIST_APP);
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
		Toast.makeText(this, rspData.responseMessage, Toast.LENGTH_SHORT).show();
	}

	@Override
	public Activity getActivityContext() {
		return this;
	}
	
	public void switchActivity(Class<?> activityClass) {
		Intent intent = new Intent(this, activityClass);
		switchActivity(intent);
	}
	
	public void switchActivity(Intent intent) {
		startActivity(intent);
	}
}
