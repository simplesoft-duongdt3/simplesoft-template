package com.simplesoft.simplesofttemplate.function.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.BaseActivity;
import com.simplesoft.simplesofttemplate.main.view.BundleKey;
import com.simplesoft.simplesofttemplate.main.view.ViewPagerInfo;

public class MainActivity extends BaseActivity {
	Bundle bundleData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null && savedInstanceState.containsKey(BundleKey.DATA_APP_LIST.getName())) {
			bundleData = savedInstanceState;
		} else if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(BundleKey.DATA_APP_LIST.getName())) {
			bundleData = getIntent().getExtras();
		} else if(AppInfo.getInstance().listData != null){
			bundleData = new Bundle();
			bundleData.putParcelable(BundleKey.DATA_APP_LIST.getName(), AppInfo.getInstance().listData);
		}
		
		if (bundleData != null) {
			String[] tabTitle = new String[] {StringUtil.getString(R.string.cat_all),StringUtil.getString(R.string.cat_paid),StringUtil.getString(R.string.cat_all),StringUtil.getString(R.string.cat_all)};
			TabsPagerAdapter vPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), bundleData);
			
			ViewPagerInfo vPagerInfo = new ViewPagerInfo(tabTitle, vPagerAdapter);
			setViewPagerInfo(vPagerInfo);
		}
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

		Bundle data;
		ArrayList<Fragment> listFrag;
		public TabsPagerAdapter(FragmentManager fm, Bundle pData) {
			super(fm);
			this.data = pData;
			this.listFrag = new ArrayList<Fragment>();
		}

		@Override
		public Fragment getItem(int pos) {
			Fragment frag = null;
			switch (pos) {
			case 0:
				frag = new AppListFragment();
				break;
			case 1:
				frag = new AppPaidFragment();
				break;
			case 2:
				frag = new AppListFragment();
				break;
			case 3:
				frag = new AppPaidFragment();
				break;
			default:
				break;
			}
			frag.setArguments(data);
			if (!listFrag.contains(frag)) {
				listFrag.add(frag);
			}
			return frag;
		}

		@Override
		public int getCount() {
			return 4;
		}
	}

	@Override
	protected void onViewPagerChange(int pos) {
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putAll(bundleData);
		super.onSaveInstanceState(outState);
	}
}
