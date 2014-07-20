package com.simplesoft.simplesofttemplate.function.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.simplesoft.simplesofttemplate.main.controller.BaseController.ResponseData;
import com.simplesoft.simplesofttemplate.main.view.BaseActivity;

public class MainFragmentActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ViewPagerInfo vPagerInfo = new ViewPagerInfo();
        vPagerInfo.tabTitle = new String[] {"All", "Paid"};
        vPagerInfo.vPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		setViewPagerInfo(vPagerInfo);

	}

	@Override
	protected void onDrawMenuChange(int position) {

	}

	@Override
	protected boolean isShowAdsWhenStop() {
		return false;
	}

	@Override
	protected boolean isShowDrawMenu() {
		return true;
	}

	@Override
	protected boolean isShowAdsWhenStart() {
		return false;
	}

	class TabsPagerAdapter extends FragmentPagerAdapter {

		public TabsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int pos) {
			Fragment frag = null;
			switch (pos) {
			case 0:
				frag = new AppListFragment();
				break;
			case 1:
				break;
			default:
				break;
			}
			return frag;
		}

		@Override
		public int getCount() {
			return 1;
		}

	}

	@Override
	public void handleViewDataResponse(ResponseData rspData) {
		
	}

	@Override
	protected void onViewPagerChange(int pos) {
		
	}
}
