/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerInfo{
	private String [] tabTitle;
	private FragmentPagerAdapter vPagerAdapter;
	
	public ViewPagerInfo(String [] tabTitle, FragmentPagerAdapter vPagerAdapter) {
		this.tabTitle = tabTitle;
		this.vPagerAdapter = vPagerAdapter;
	}
	
	public ViewPagerInfo(String [] tabTitle, final BaseFragment [] arrFragment, FragmentManager fm) {
		this.tabTitle = tabTitle;
		this.vPagerAdapter = new FragmentPagerAdapter(fm) {
			
			@Override
			public int getCount() {
				return arrFragment.length;
			}
			
			@Override
			public Fragment getItem(int pos) {
				return arrFragment[pos];
			}
		};
	}
	
	public ViewPagerInfo(String [] tabTitle, final List<BaseFragment> listFragment, FragmentManager fm) {
		this.tabTitle = tabTitle;
		this.vPagerAdapter = new FragmentPagerAdapter(fm) {
			
			@Override
			public int getCount() {
				return listFragment.size();
			}
			
			@Override
			public Fragment getItem(int pos) {
				return listFragment.get(pos);
			}
		};
	}
	
	/**
	 * @return the tabTitle
	 */
	public String[] getTabTitle() {
		return tabTitle;
	}
	
	/**
	 * @return the vPagerAdapter
	 */
	public FragmentPagerAdapter getvPagerAdapter() {
		return vPagerAdapter;
	}
}