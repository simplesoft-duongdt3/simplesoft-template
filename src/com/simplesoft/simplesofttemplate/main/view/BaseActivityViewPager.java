/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.simplesoft.simplesappspermissions.R;

/**
 * ViewPagerActivity.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 17:58:55 19 Jul 2014
 */
public abstract class BaseActivityViewPager extends BaseActivity {

	private ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Initilization
		// Add ViewPager
		ViewGroup vgroup = (ViewGroup) getLayoutInflater().inflate(R.layout.viewpager, null, false);
		ViewGroup parent = (ViewGroup) vgroup.findViewById(R.id.parent);
		viewPager = (ViewPager) vgroup.findViewById(R.id.pager);
		parent.removeAllViews();

        fragHolder.addView(viewPager);        
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}
	
	public static class ViewPagerInfo{
		public String [] tabTitle;
		public FragmentPagerAdapter vPagerAdapter;
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
		
		actionBar.removeAllTabs();
		// Adding Tabs
        for (String tab_name : vPagerInfo.tabTitle) {
        	Tab tab = actionBar.newTab().setText(tab_name).setTabListener(tabListener);
        	actionBar.addTab(tab);
        }
        viewPager.setOnPageChangeListener(pageChangeListener);
        viewPager.setAdapter(vPagerInfo.vPagerAdapter);
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
    	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    	}
    	
    	@Override
    	public void onTabSelected(Tab tab, FragmentTransaction ft) {
    		if (viewPager != null) {
    			viewPager.setCurrentItem(tab.getPosition());
			}
    	}
    	
    	@Override
    	public void onTabReselected(Tab tab, FragmentTransaction ft) {
    	}
    };

    protected abstract void onViewPagerChange(int pos);
}
