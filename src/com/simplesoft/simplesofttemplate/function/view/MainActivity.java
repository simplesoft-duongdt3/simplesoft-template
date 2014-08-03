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

import com.simplesoft.simpleappspermissions.R;
import com.simplesoft.simplesofttemplate.constance.PermissionGroup;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.MultiCondition;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.Operator;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplesofttemplate.main.view.BaseActivity;
import com.simplesoft.simplesofttemplate.main.view.BroadCastAction;
import com.simplesoft.simplesofttemplate.main.view.BundleKey;
import com.simplesoft.simplesofttemplate.main.view.ViewPagerInfo;
import com.simplesoft.simplesofttemplate.main.view.control.CheckBoxActionProvider;
import com.simplesoft.simplesofttemplate.main.view.control.IViewActionSender;

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
		
		tabTitles = new String[PermissionGroup.values().length];
		int i = 0;
		for (PermissionGroup group : PermissionGroup.values()) {
			tabTitles[i] = group.getDisplayName();
			i++;
		}
		
		if (bundleData != null) {
			TabsPagerAdapter vPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), bundleData);
			
			ViewPagerInfo vPagerInfo = new ViewPagerInfo(tabTitles, vPagerAdapter);
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
			PermissionGroup group = PermissionGroup.values()[pos];
			MultiCondition<AppItemInfo> multiCondition = new MultiCondition<AppItemInfo>()
					.addCondition(new AppItemInfo.CheckGroupCondition(group).setOperator(Operator.IS));
			Fragment frag = new AppListFragment(group, multiCondition);
			//theem ddieu kien check group
			frag.setArguments(data);
			if (!listFrag.contains(frag)) {
				listFrag.add(frag);
			}
			return frag;
		}

		@Override
		public int getCount() {
			return PermissionGroup.values().length;
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
		
		//menu search
		MenuItem itemSearch = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView) itemSearch.getActionView();
		searchView.setQueryHint(StringUtil.getString(R.string.search_input));
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);  
        searchView.setOnQueryTextListener(searchTextChangeListener);
		itemSearch.setOnActionExpandListener(searchExpandListener);
		//menu system apps
		MenuItem itemcbSysApps = menu.findItem(R.id.action_cbSystemApps);
		CheckBoxActionProvider cbSystemProvider = (CheckBoxActionProvider) itemcbSysApps.getActionProvider();
		cbSystemProvider.setViewActionSender(cbSysAppViewActionSender);
		return true;
	}

	IViewActionSender cbSysAppViewActionSender = new IViewActionSender() {
		
		@Override
		public void sendViewAction(Object... data) {
			boolean isSystemApp = (Boolean) data[0];
			AppInfo.getInstance().opSystemApp = isSystemApp ? Operator.IS : Operator.NOT;
			sendBroadCastSimpleSoft(BroadCastAction.FILTER_SYSTEM_APPS, null);
		}
	};
	
	
    OnActionExpandListener searchExpandListener = new OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            return true;
        }
        
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
            return true;
        }
    };
    
	SearchView.OnQueryTextListener searchTextChangeListener = new SearchView.OnQueryTextListener(){
	    @Override
	    public boolean onQueryTextChange(String newText){
	    	//nếu như khác query cũ, mới filter
	    	if (!newText.equals(AppInfo.getInstance().strQueryApp)) {
	    		AppInfo.getInstance().strQueryApp = newText;
	    		sendBroadCastSimpleSoft(BroadCastAction.SEARCH, null);
			}
	        return true;
	    }
	    
	    @Override
	    public boolean onQueryTextSubmit(String query){
	        return true;
	    }
	};

	private String[] tabTitles;
	@Override
	protected boolean isShowAdSlider() {
		return true;
	}

}
