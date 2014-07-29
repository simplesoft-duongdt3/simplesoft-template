package com.simplesoft.simplesofttemplate.main.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.hardware.SensorManager;
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
import android.widget.ListView;
import android.widget.Toast;

import com.simplesoft.simpleappspermissions.R;
import com.simplesoft.simplesofttemplate.main.controller.IRequestView;
import com.simplesoft.simplesofttemplate.main.controller.RequestAction;
import com.simplesoft.simplesofttemplate.main.controller.RequestData;
import com.simplesoft.simplesofttemplate.main.controller.ResponseData;
import com.simplesoft.simplesofttemplate.main.controller.SimpleController;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;
import com.squareup.seismic.ShakeDetector;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.banner.Banner;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashConfig.Theme;

public abstract class BaseActivity extends FragmentActivity implements IRequestView, ShakeDetector.Listener, IBroadCastReceiver{
	private StartAppAd startAppAd = new StartAppAd(this);

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	protected ActionBar actionBar;
	protected FrameLayout fragHolder;
	protected Banner llAds;
	private ViewPager viewPager;
	BaseBroadcastReceiver receiver = new BaseBroadcastReceiver(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// save last activity
		AppInfo.getInstance().setActivityContext(this);
				
		//detect shake
		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	    ShakeDetector sd = new ShakeDetector(this);
	    sd.start(sensorManager);
	    
		StartAppSDK.init(this, AppInfo.DEV_ID, AppInfo.APP_ID, true);
		if (isShowAdsWhenStart()) {
			StartAppAd.showSplash(this, savedInstanceState, new SplashConfig()
					.setTheme(Theme.SKY).setLogo(R.drawable.ic_launcher)
					.setAppName(getString(R.string.app_name)));
		}

		setContentView(R.layout.activity_base_draw);
		actionBar = getActionBar();
		fragHolder = (FrameLayout) findViewById(R.id.fragHolder);
		llAds = (Banner)findViewById(R.id.startAppBanner);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.appthemeblue_ic_navigation_drawer, R.string.menu_draw, R.string.menu_draw) {

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
		
		// ad
		StartAppAd.showSlider(this);
		startAppAd.showAd();
		startAppAd.loadAd();
	}

	/**
	 * Switch Activity + Bundle data. 
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 02:47:44 24 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param activityClass
	 * @param data
	 */
	public void switchActivity(Class<?> activityClass, Bundle data) {
		Intent intent = new Intent(this, activityClass);
		intent.putExtras(data);
		switchActivity(intent);
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
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		AppInfo.getInstance().callActivityResume(this);
		//register Broadcast Receiver
		IntentFilter filter = new IntentFilter(BaseBroadcastReceiver.BC_ACTION_SIMPLESOFT);
		AppInfo.getInstance().registerReceiver(receiver, filter);
		startAppAd.onResume();
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
		AppInfo.getInstance().unregisterReceiver(receiver);
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
	public void handleViewDataResponseSuccess(ResponseData rspData) {
		Toast.makeText(this, rspData.responseMessage, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void handleViewDataResponseError(ResponseData rspData) {
		Toast.makeText(this, rspData.responseMessage, Toast.LENGTH_SHORT).show();
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
	 * @param vPagerInfo new ViewPagerInfo()
	 * @param navigationMode ActionBar.NAVIGATION_MODE_STANDARD ActionBar.NAVIGATION_MODE_LIST ActionBar.NAVIGATION_MODE_TABS
	 */
	@SuppressLint("InflateParams")
	protected void setViewPagerInfo(ViewPagerInfo vPagerInfo, int navigationMode){
		removeAllInBackStack(getSupportFragmentManager());
		
		if (viewPager == null) {
			// Add ViewPager
			ViewGroup vgroup = (ViewGroup) getLayoutInflater().inflate(R.layout.viewpager, null, false);
			ViewGroup parent = (ViewGroup) vgroup.findViewById(R.id.parent);
			viewPager = (ViewPager) vgroup.findViewById(R.id.pager);
			parent.removeAllViews();
			//viewPager.setPageTransformer(true, new DefaultTransformer());
			fragHolder.removeAllViews();
			fragHolder.addView(viewPager);        
		}
		
		if (actionBar != null) {
			actionBar.removeAllTabs();
			// Adding Tabs
			String [] tabTitles = vPagerInfo.getTabTitle();
			for (String tab_name : tabTitles) {
				Tab tab = actionBar.newTab().setText(tab_name).setTabListener(tabListener);
				actionBar.addTab(tab);
			}
			actionBar.setNavigationMode(navigationMode);
		}
        
        viewPager.setOnPageChangeListener(pageChangeListener);
        viewPager.setAdapter(vPagerInfo.getvPagerAdapter());
        viewPager.setCurrentItem(0);
        onViewPagerChange(0);
	}
	
	protected void setViewPagerInfo(ViewPagerInfo vPagerInfo){
		setViewPagerInfo(vPagerInfo, ActionBar.NAVIGATION_MODE_TABS);
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
		//ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.setCustomAnimations(
				R.anim.card_flip_right_in, R.anim.card_flip_right_out,
				R.anim.card_flip_left_in, R.anim.card_flip_left_out);
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
    
    //Back quay lại trang trước
/*    @Override
    public void onBackPressed() {
    	if (viewPager == null) {
    		super.onBackPressed();
		} else{
	    	if (viewPager.getCurrentItem() == 0) {
	            // If the user is currently looking at the first step, allow the system to handle the
	            // Back button. This calls finish() on this activity and pops the back stack.
	            super.onBackPressed();
	        } else {
	            // Otherwise, select the previous step.
	        	viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
	        }
		}
    }*/
    
    @Override
	public void hearShake() {
    	Toast.makeText(this, "Shake!", Toast.LENGTH_SHORT).show();
	}
    
    public void sendBroadCastSimpleSoft(BroadCastAction action, Bundle pData){
		Intent intent = new Intent();
    	intent.setAction(BaseBroadcastReceiver.BC_ACTION_SIMPLESOFT);
    	if (pData == null) {
			pData = new Bundle();
		}
    	pData.putSerializable(BundleKey.BC_ACTION_SEND.getName(), action);
    	intent.putExtras(pData);
    	
		AppInfo.getInstance().sendBroadcast(intent);
	}
	
	@Override
	public void onReceiverBroadCastSend(String action, Bundle data) {
		//nếu chứa action thì thực hiện
		if(data.containsKey(BundleKey.BC_ACTION_SEND.getName())){
			BroadCastAction bcAction = (BroadCastAction) data.getSerializable(BundleKey.BC_ACTION_SEND.getName());
			doActionBroadCast(bcAction, data);
		}
	}
}
