package com.simplesoft.simplesofttemplate.function.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;
import com.simplesoft.simplesofttemplate.main.view.BaseActivityViewPager;

public class MainActivity extends BaseActivityViewPager {

	private String[] mPlanetTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ViewPagerInfo vPagerInfo = new ViewPagerInfo();
        vPagerInfo.tabTitle = new String[] {"All", "Paid"};
        vPagerInfo.vPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		setViewPagerInfo(vPagerInfo);
		mPlanetTitles = getResources().getStringArray(R.array.operating_systems);
		setDrawMenuAdapter(new ArrayAdapter<String>(this, R.layout.drawer_item, R.id.content, mPlanetTitles));
	}
	
	@Override
	protected void onViewPagerChange(int pos) {
		LogUtil.log("onViewPagerChange " + pos + "");
	}

	@Override
	protected void onDrawMenuChange(int position) {
		actionBar.setTitle(mPlanetTitles[position]);
	}
	
	@Override
	protected boolean isShowDrawMenu() {
		return true;
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
