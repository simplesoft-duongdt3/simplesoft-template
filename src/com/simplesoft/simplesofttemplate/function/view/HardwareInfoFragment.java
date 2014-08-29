/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.simplesoft.simplesofttemplate.function.DTO.ItemInfo;
import com.simplesoft.simplesofttemplate.function.viewholder.ViewHolderInfo;
import com.simplesoft.simplesofttemplate.main.utils.DateUtil;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.BaseFragment;
import com.simplesoft.simplesofttemplate.main.view.BroadCastAction;
import com.simplesoft.simplesofttemplate.main.view.control.BaseListAdapter;
import com.simplesoft.simplesysteminfo.R;

/**
 * HardwareInfoFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 00:18:03 29 Aug 2014
 */
public class HardwareInfoFragment extends BaseFragment{

	@Override
	@SuppressLint({ "InflateParams", "NewApi" })
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup vgroup = (ViewGroup) inflater.inflate(R.layout.frag_list_view, container, false);
		ListView lvInfo = (ListView) vgroup.findViewById(R.id.lvInfo);
		
		List<ItemInfo> arrFilter = new ArrayList<ItemInfo>();
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.model), Build.MODEL));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.manufacturer), Build.MANUFACTURER));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.product), Build.PRODUCT));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.brand), Build.BRAND));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.board), Build.BOARD));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.device), Build.DEVICE));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.display), Build.DISPLAY));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.id), Build.ID));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.serial), Build.SERIAL));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.time), new Date().toString()));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.time_from_boot), DateUtil.formatMilisecond(SystemClock.elapsedRealtime())));
		
	    final ActivityManager activityManager = (ActivityManager) parent.getSystemService(Context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(outInfo);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			arrFilter.add(new ItemInfo(StringUtil.getString(R.string.total_ram), StringUtil.formatNumber(outInfo.totalMem >> 20)));
		}
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.ava_ram), StringUtil.formatNumber(outInfo.availMem >> 20) + " Mb"));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.low_memory), outInfo.lowMemory ? StringUtil.getString(R.string.yes) : StringUtil.getString(R.string.no)));
		
		StorageInfo storeInfo = new StorageInfo();
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.diskInTotal), StringUtil.formatNumber(storeInfo.megInTotal) + " Mb"));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.diskInAva), StringUtil.formatNumber(storeInfo.megInAvailable) + " Mb"));
		
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.diskExTotal), StringUtil.formatNumber(storeInfo.megExTotal) + " Mb"));
		arrFilter.add(new ItemInfo(StringUtil.getString(R.string.diskExAva), StringUtil.formatNumber(storeInfo.megExAvailable) + " Mb"));
		
		
		BaseListAdapter<ItemInfo> adapter = new BaseListAdapter<ItemInfo>(arrFilter , new ViewHolderInfo(), null);
		lvInfo.setAdapter(adapter);
		
		return super.onCreateView(inflater, vgroup, savedInstanceState);
	}
	
	@Override
	public void doActionBroadCast(BroadCastAction action, Bundle data) {
		// TODO Auto-generated method stub

	}

	class StorageInfo {
		public StorageInfo() {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
				getExternalInfoNewApi18();
				getInternalInfoNewApi18();
			} else{
				getInternalInfo();
				getExternalInfo();
			}
		}
		public long megInAvailable = 0;
		public long megInTotal = 0;
		public long megExAvailable = 0;
		public long megExTotal = 0;

		@SuppressWarnings("deprecation")
		public void getInternalInfo() {
			try {
				StatFs stat = new StatFs(Environment.getDataDirectory()
						.getPath());
				long bytesAvailable = (long) stat.getFreeBlocks()
						* (long) stat.getBlockSize();
				megInAvailable = bytesAvailable / 1048576;
				long bytesTotal = (long) stat.getBlockSize()
						* (long) stat.getBlockCount();
				megInTotal = bytesTotal / 1048576;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		@SuppressWarnings("deprecation")
		public void getExternalInfo() {
			try {
				StatFs stat = new StatFs(Environment
						.getExternalStorageDirectory().getPath());
				long bytesAvailable = (long) stat.getFreeBlocks()
						* (long) stat.getBlockSize();
				megExAvailable = bytesAvailable / 1048576;
				long bytesExTotal = (long) stat.getBlockSize()
						* (long) stat.getBlockCount();
				megExTotal = bytesExTotal / 1048576;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		@SuppressLint("NewApi")
		public void getInternalInfoNewApi18() {
			try {
				StatFs stat = new StatFs(Environment.getDataDirectory()
						.getPath());
				long bytesAvailable = (long) stat.getFreeBlocksLong()
						* (long) stat.getBlockSizeLong();
				megInAvailable = bytesAvailable / 1048576;
				long bytesTotal = (long) stat.getBlockSizeLong()
						* (long) stat.getBlockCountLong();
				megInTotal = bytesTotal / 1048576;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		@SuppressLint("NewApi")
		public void getExternalInfoNewApi18() {
			try {
				StatFs stat = new StatFs(Environment
						.getExternalStorageDirectory().getPath());
				long bytesAvailable = (long) stat.getFreeBlocksLong()
						* (long) stat.getBlockSizeLong();
				megExAvailable = bytesAvailable / 1048576;
				long bytesExTotal = (long) stat.getBlockSizeLong()
						* (long) stat.getBlockCountLong();
				megExTotal = bytesExTotal / 1048576;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
