package com.simplesoft.simplesofttemplate.main.view;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.simplesoft.simplesappspermissions.R;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashConfig.Theme;

public abstract class BaseActivity extends FragmentActivity {
	private StartAppAd startAppAd = new StartAppAd(this);

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	protected ActionBar actionBar;
	protected FrameLayout fragHolder;

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

		actionBar.setDisplayShowTitleEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
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
}
