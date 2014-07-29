package com.simplesoft.simplesofttemplate.constance;

import com.simplesoft.simpleappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo.ItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;

public enum PermissionGroup {
	ALL("ALL", StringUtil.getString(R.string.cat_all), new String[]{""}, new String[]{}),
	COST_MONEY("COST_MONEY",StringUtil.getString(R.string.cat_cost_money), new String[]{"COST_MONEY"}, new String[]{}),
	PERSONAL_INFO("PERSONAL_INFO", StringUtil.getString(R.string.cat_personal_info), new String[]{"ACCOUNTS","BOOKMARKS","CALENDAR","LOCATION","MESSAGES","PERSONAL_INFO","SOCIAL_INFO","SYNC_SETTINGS","USER_DICTIONARY","VOICEMAIL","WRITE_USER_DICTIONARY"}, new String[]{}),
	AFFECTS_BATTERY("AFFECTS_BATTERY", StringUtil.getString(R.string.cat_affect_battery),  new String[]{"AFFECTS_BATTERY"}, new String[]{}),
	NETWORK("NETWORK", StringUtil.getString(R.string.cat_network),  new String[]{"BLUETOOTH_NETWORK", "NETWORK"}, new String[]{}),
	CAMERA_MICROPHONE("CAMERA_MICROPHONE",StringUtil.getString(R.string.cat_camera_microphone), new String[]{"CAMERA","MICROPHONE"}, new String[]{}),
	SYSTEM("SYSTEM", StringUtil.getString(R.string.cat_system_change), new String[]{"ACCESSIBILITY_FEATURES","APP_INFO","AUDIO_SETTINGS","DEVELOPMENT_TOOLS","DISPLAY","HARDWARE_CONTROLS","PHONE_CALLS","SCREENLOCK","STATUS_BAR","STORAGE","SYSTEM_CLOCK","SYSTEM_TOOLS","DEVICE_ALARMS","WALLPAPER"}, new String[]{}),	
	;

	private String name;
	private String[] groups;
	private String displayName;
	private String[] names;
	
	private PermissionGroup(String pName, String pDisplayName, String[] pGroups, String[] pNames) {
		this.name = pName;
		this.displayName = pDisplayName;
		this.groups = pGroups;
		this.names = pNames;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public boolean contain(ItemInfo input){
		boolean res = false;
		for (String groupName : groups) {
			res = input.group.contains(groupName);
			if (res) {
				break;
			}
		}
		
		if (!res) {
			for (String namePer : names) {
				res = input.name.contains(namePer);
				if (res) {
					break;
				}
			}
		}
		return res;
	}
	
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
}
