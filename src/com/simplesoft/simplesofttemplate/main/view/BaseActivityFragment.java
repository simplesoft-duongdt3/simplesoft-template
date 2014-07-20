/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;

/**
 * ViewPagerActivity.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 17:58:55 19 Jul 2014
 */
public abstract class BaseActivityFragment extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
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
		ft.replace(R.id.fragHolder, frag, TAG);
		ft.addToBackStack(TAG);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
    
    protected void removeAllInBackStack(FragmentManager fm) {
		int len = fm.getBackStackEntryCount();
		for (int i = 0; i < len; i++) {
			try {
				fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				fm.executePendingTransactions();
			} catch (Exception e) {
				LogUtil.log(e);
			}
		}
	}
}
