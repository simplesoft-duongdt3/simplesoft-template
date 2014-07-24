package com.simplesoft.simplesofttemplate.function.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.BaseActivity;
import com.simplesoft.simplesofttemplate.main.view.ViewPagerInfo;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] tabTitle = new String[] {StringUtil.getString(R.string.cat_all)};
		TabsPagerAdapter vPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		
		ViewPagerInfo vPagerInfo = new ViewPagerInfo(tabTitle, vPagerAdapter);
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
	protected void onViewPagerChange(int pos) {
		
	}
}
