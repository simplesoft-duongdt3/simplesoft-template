package com.simplesoft.simplesofttemplate.function.view;

import java.util.ArrayList;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.widget.SearchView;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.constance.PermissionGroup;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.MultiCondition;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.Operator;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.BaseActivity;
import com.simplesoft.simplesofttemplate.main.view.BroadCastAction;
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
		return false;
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
			
			MultiCondition<AppItemInfo> multiCondition = new MultiCondition<AppItemInfo>();
			PermissionGroup group = PermissionGroup.ALL;
			Fragment frag = null;
			switch (pos) {
			case 0:
				group = PermissionGroup.ALL;
				break;
			case 1:
				group = PermissionGroup.COST_MONEY;
				break;
			case 2:
				group = PermissionGroup.SYSTEM;
				break;
			case 3:
				group = PermissionGroup.PERSONAL_INFO;
				break;
			case 4:
				group = PermissionGroup.AFFECTS_BATTERY;
				break;
			case 5:
				group = PermissionGroup.NETWORK;
				break;
			case 6:
				group = PermissionGroup.CAMERA_MICROPHONE;
				break;
			default:
				break;
			}
			//theem ddieu kien check group
			multiCondition.addCondition(new AppItemInfo.CheckGroupCondition(group).setOperator(Operator.IS));
			
			frag = new AppListFragment(group, multiCondition);
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

	@Override
	public void doActionBroadCast(BroadCastAction action, Bundle data) {
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_base, menu);
		MenuItem itemSearch = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView) itemSearch.getActionView();
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);  
        searchView.setOnQueryTextListener(searchTextChangeListener);
        
		itemSearch.setOnActionExpandListener(searchExpandListener);
		return true;
	}

    OnActionExpandListener searchExpandListener = new OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
        	LogUtil.log("onMenuItemActionCollapse");
            return true;
        }
        
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
        	LogUtil.log("onMenuItemActionExpand");
            return true;
        }
    };
    
	SearchView.OnQueryTextListener searchTextChangeListener = new SearchView.OnQueryTextListener(){
	    @Override
	    public boolean onQueryTextChange(String newText){
	    	Bundle pData = new Bundle();
	    	AppInfo.getInstance().strQueryApp = newText;
	    	pData.putSerializable(BundleKey.BC_ACTION_SEND.getName(), BroadCastAction.SEARCH);
			sendBroadCastSimpleSoft(pData);
	        return true;
	    }
	    
	    @Override
	    public boolean onQueryTextSubmit(String query){
	    	Bundle pData = new Bundle();
	    	AppInfo.getInstance().strQueryApp = query;
	    	pData.putSerializable(BundleKey.BC_ACTION_SEND.getName(), BroadCastAction.SEARCH);
			sendBroadCastSimpleSoft(pData);
	        return true;
	    }
	};

}
