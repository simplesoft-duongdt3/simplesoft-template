package com.simplesoft.simplesofttemplate.main.view;

import com.simplesoft.simplesofttemplate.R;
import com.simplesoft.simplesofttemplate.function.view.MainActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

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
		new CountDownTimer(1500, 1000) {

		     public void onTick(long millisUntilFinished) {
		     }

		     public void onFinish() {
		    	 switchActivity(MainActivity.class);
		    	 SplashScreenActivity.this.finish();
		     }
		  }.start();
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
	
}
