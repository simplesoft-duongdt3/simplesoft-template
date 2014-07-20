/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * TabsPagerAdapter.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 09:33:51 19 Jul 2014
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

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
			frag = new AppPaidFragment();
			break;
		default:
			break;
		}
		return frag;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
