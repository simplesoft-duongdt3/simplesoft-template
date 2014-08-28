package com.simplesoft.simplesofttemplate.main.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.simplesoft.simplesofttemplate.function.view.MainActivity;
import com.simplesoft.simplesofttemplate.main.controller.IRequestView;
import com.simplesoft.simplesofttemplate.main.controller.RequestAction;
import com.simplesoft.simplesofttemplate.main.controller.RequestData;
import com.simplesoft.simplesofttemplate.main.controller.ResponseData;
import com.simplesoft.simplesofttemplate.main.controller.SimpleController;
import com.simplesoft.simplesofttemplate.main.utils.ActivityUtil;
import com.simplesoft.simpletool.R;

public class SplashScreenActivity extends Activity implements IRequestView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
/*		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen_layout);
		
		new CountDownTimer(1200, 1000) {
			
			public void onTick(long millisUntilFinished) {
				
			}
			
			public void onFinish() {
				ActivityUtil.switchActivity(SplashScreenActivity.this, MainActivity.class);
				SplashScreenActivity.this.finish();
			}
		}.start();
		
		
	}

	@Override
	public void handleViewDataResponseSuccess(ResponseData rspData) {
		switch (rspData.rqData.action) {
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
}
