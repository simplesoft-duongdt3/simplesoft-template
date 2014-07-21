package com.simplesoft.simplesofttemplate.main.view;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.simplesoft.simplesappstemplate.R;
import com.simplesoft.simplesofttemplate.main.controller.IRequestView;
import com.simplesoft.simplesofttemplate.main.controller.RequestAction;
import com.simplesoft.simplesofttemplate.main.controller.RequestData;
import com.simplesoft.simplesofttemplate.main.controller.ResponseData;
import com.simplesoft.simplesofttemplate.main.controller.SimpleController;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashConfig.Theme;

public abstract class BaseActivity extends FragmentActivity implements IRequestView {
	private StartAppAd startAppAd = new StartAppAd(this);

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	protected ActionBar actionBar;
	protected FrameLayout fragHolder;
	protected LinearLayout llAds;
	private ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// save last activity
		AppInfo.getInstance().setActivityContext(this);
		StartAppSDK.init(this, AppInfo.DEV_ID, AppInfo.APP_ID, true);

		if (isShowAdsWhenStart()) {
			StartAppAd.showSplash(this, savedInstanceState, new SplashConfig()
					.setTheme(Theme.SKY).setLogo(R.drawable.ic_launcher)
					.setAppName(getString(R.string.app_name)));
		}

		setContentView(R.layout.activity_base_draw);
		actionBar = getActionBar();
		fragHolder = (FrameLayout) findViewById(R.id.fragHolder);
		llAds = (LinearLayout)findViewById(R.id.llAds);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.menu_draw, R.string.menu_draw) {

			public void onDrawerClosed(View view) {
				// getActionBar().setTitle(title);
			}

			public void onDrawerOpened(View drawerView) {
				// getActionBar().setTitle(getString(R.string.menu_draw));
			}
		};
		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (!isShowDrawMenu()) {
			// lock draw menu
			mDrawerLayout
					.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		}

		if (actionBar != null) {
			actionBar.setDisplayShowTitleEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeButtonEnabled(true);
		}
		
		
	}

	/**
	 * Switch Activity with Intent
	 * 
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 08:53:52 20 Jul 2014
	 * @return: void
	 * @throws:
	 * @param intent
	 */
	public void switchActivity(Intent intent) {
		startActivity(intent);
	}

	/**
	 * Switch Activity with Activity Class
	 * 
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 08:54:10 20 Jul 2014
	 * @return: void
	 * @throws:
	 * @param activityClass
	 */
	public void switchActivity(Class<?> activityClass) {
		Intent intent = new Intent(this, activityClass);
		switchActivity(intent);
	}

	/**
	 * Check show ad when stop
	 * 
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 09:10:46 20 Jul 2014
	 * @return: boolean
	 * @throws:
	 * @return
	 */
	protected abstract boolean isShowAdsWhenStop();

	protected abstract boolean isShowAdsWhenStart();

	protected abstract boolean isShowDrawMenu();

	protected void setDrawMenuAdapter(ArrayAdapter<?> adapter) {
		// Set the adapter for the list view
		mDrawerList.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		AppInfo.getInstance().callActivityResume(this);
		startAppAd.onResume();
		startAppAd.loadAd();
	}

	@Override
	protected void onPause() {
		super.onPause();
		startAppAd.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		AppInfo.getInstance().callActivityStop(this);
		if (isShowAdsWhenStop()) {
			startAppAd.onBackPressed();
		}
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);
		onDrawMenuChange(position);
	}

	/**
	 * Call when Draw menu change item
	 * 
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 09:55:32 20 Jul 2014
	 * @return: void
	 * @throws:
	 * @param position
	 */
	protected abstract void onDrawMenuChange(int position);

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		if (isShowDrawMenu()) {
			mDrawerToggle.syncState();
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (isShowDrawMenu()) {
			mDrawerToggle.onConfigurationChanged(newConfig);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns true, then it
		// has handled the app icon touch event
		if (isShowDrawMenu() && mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// Handle your other action bar items...
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
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
		Toast.makeText(this, rspData.errorMessage, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public Activity getActivityContext() {
		return this;
	}
	
	
	/**
	 * Add ViewPagerInfo and Tab ActionBar
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 18:32:37 19 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param vPagerInfo
	 */
	protected void setViewPagerInfo(ViewPagerInfo vPagerInfo){
		removeAllInBackStack(getSupportFragmentManager());
		
		if (viewPager == null) {
			// Add ViewPager
			ViewGroup vgroup = (ViewGroup) getLayoutInflater().inflate(R.layout.viewpager, null, false);
			ViewGroup parent = (ViewGroup) vgroup.findViewById(R.id.parent);
			viewPager = (ViewPager) vgroup.findViewById(R.id.pager);
			parent.removeAllViews();
			
			fragHolder.removeAllViews();
			fragHolder.addView(viewPager);        
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		}
		
		actionBar.removeAllTabs();
		// Adding Tabs
		String [] tabTitles = vPagerInfo.getTabTitle();
        for (String tab_name : tabTitles) {
        	Tab tab = actionBar.newTab().setText(tab_name).setTabListener(tabListener);
        	actionBar.addTab(tab);
        }
        viewPager.setOnPageChangeListener(pageChangeListener);
        viewPager.setAdapter(vPagerInfo.getvPagerAdapter());
        viewPager.setCurrentItem(0);
        onViewPagerChange(0);
	}

	/** Defining ViewPager listener */
    ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
    	@Override
    	public void onPageSelected(int position) {
    		super.onPageSelected(position);
    		if (actionBar != null) {
    			actionBar.setSelectedNavigationItem(position);
    			onViewPagerChange(position);
			}
    	}
    };
    
    /** Defining action bar tab listener */
    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
    	
    	@Override
    	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
    	}
    	
    	@Override
    	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
    		if (viewPager != null) {
    			viewPager.setCurrentItem(tab.getPosition());
			}
    	}
    	
    	@Override
    	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
    	}
    };

    protected abstract void onViewPagerChange(int pos);
    
    
    /**
     * Switch fragment
     * @author: duongdt3
     * @since: 1.0
     * @time: 08:50:10 20 Jul 2014
     * @return: void
     * @throws:  
     * @param frag
     * @param isRemoveBackStack
     */
    public void switchFragment(BaseFragment frag, boolean isRemoveBackStack) {
    	//nếu có Viewpager rồi, thì xoá đi, add fragment vào thôi
    	if (viewPager != null) {
    		viewPager = null;
    		fragHolder.removeAllViews();
    		actionBar.removeAllTabs();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		}
    	FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		if (isRemoveBackStack) {
			removeAllInBackStack(fm);
		}
		String TAG = frag.getTAG();
		
		Fragment existsFrag = fm.findFragmentByTag(TAG);
		if (existsFrag != null) {
			ft.remove(existsFrag);
		}
		ft.add(R.id.fragHolder, frag, TAG);
		ft.addToBackStack(TAG);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
    
    protected void removeAllInBackStack(FragmentManager fm) {
		try {
			fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fm.executePendingTransactions();
		} catch (Exception e) {
			LogUtil.log(e);
		}
	}
}
