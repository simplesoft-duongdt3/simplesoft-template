package com.simplesoft.simplesofttemplate.constance;

import com.simplesoft.simpleappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo.ItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;

public enum PermissionGroup {
	ALL("ALL", StringUtil.getString(R.string.cat_all), new String[]{""}, new String[]{}),
	COST_MONEY("COST_MONEY",StringUtil.getString(R.string.cat_cost_money), new String[]{"COST_MONEY"}, new String[]{}),
	BILLING("BILLING", StringUtil.getString(R.string.cat_billing), new String[]{}, new String[]{"vending.BILLING"}),
	PERSONAL_INFO("PERSONAL_INFO", StringUtil
			.getString(R.string.cat_personal_info), new String[] { "ACCOUNTS", "BOOKMARKS", "CALENDAR", "LOCATION", "MESSAGES", "PERSONAL_INFO", "SOCIAL_INFO",
			"SYNC_SETTINGS", "USER_DICTIONARY", "VOICEMAIL", "WRITE_USER_DICTIONARY" }, new String[] { "USE_CREDENTIALS", "ACCOUNT_MANAGER", "MANAGE_ACCOUNTS",
			"GET_ACCOUNTS", "AUTHENTICATE_ACCOUNTS", "WRITE_HISTORY_BOOKMARKS", "READ_HISTORY_BOOKMARKS", "LOCATION_HARDWARE", "ACCESS_FINE_LOCATION",
			"ACCESS_COARSE_LOCATION", "RECEIVE_WAP_PUSH", "READ_SMS", "SEND_SMS", "SEND_RESPOND_VIA_MESSAGE", "BROADCAST_WAP_PUSH",
			"RECEIVE_EMERGENCY_BROADCAST", "BROADCAST_SMS", "WRITE_SMS", "RECEIVE_MMS", "RECEIVE_SMS", "READ_CELL_BROADCASTS", "WRITE_CALENDAR",
			"READ_PROFILE", "BIND_KEYGUARD_APPWIDGET", "READ_CALENDAR", "BIND_DIRECTORY_SEARCH", "BIND_APPWIDGET", "WRITE_PROFILE", "RETRIEVE_WINDOW_CONTENT",
			"WRITE_CALL_LOG", "WRITE_SOCIAL_STREAM", "READ_SOCIAL_STREAM", "READ_CALL_LOG", "READ_CONTACTS", "WRITE_CONTACTS", "READ_SYNC_STATS",
			"WRITE_SYNC_SETTINGS", "READ_SYNC_SETTINGS", "READ_USER_DICTIONARY", "ADD_VOICEMAIL", "WRITE_USER_DICTIONARY" }),	
	AFFECTS_BATTERY(
			"AFFECTS_BATTERY", StringUtil.getString(R.string.cat_affect_battery), new String[] { "AFFECTS_BATTERY" }, new String[] {
					"CHANGE_WIFI_MULTICAST_STATE", "TRANSMIT_IR", "WAKE_LOCK", "VIBRATE", "FLASHLIGHT" }),	
	NETWORK("NETWORK", StringUtil.getString(R.string.cat_network),  new String[]{"BLUETOOTH_NETWORK", "NETWORK"}, new String[]{"NFC","CHANGE_WIMAX_STATE","ACCESS_WIMAX_STATE","INTERNET","CHANGE_WIFI_STATE","LOOP_RADIO","ACCESS_WIFI_STATE","CONNECTIVITY_INTERNAL","CHANGE_NETWORK_STATE","RECEIVE_DATA_ACTIVITY_CHANGE","ACCESS_NETWORK_STATE"}),
	SERVICES("SERVICES", StringUtil.getString(R.string.cat_services), new String[] { "SERVICE" }, new String[] {}),
	CAMERA_MICROPHONE("CAMERA_MICROPHONE", StringUtil
			.getString(R.string.cat_camera_microphone), new String[] { "CAMERA", "MICROPHONE" }, new String[] { "CAMERA", "CAMERA_DISABLE_TRANSMIT_LED",
			"RECORD_AUDIO" }), 
	SYSTEM("SYSTEM", StringUtil.getString(R.string.cat_system_change), new String[] { "ACCESSIBILITY_FEATURES", "APP_INFO",
			"AUDIO_SETTINGS", "DEVELOPMENT_TOOLS", "DISPLAY", "HARDWARE_CONTROLS", "PHONE_CALLS", "SCREENLOCK", "STATUS_BAR", "STORAGE", "SYSTEM_CLOCK",
			"SYSTEM_TOOLS", "DEVICE_ALARMS", "WALLPAPER" }, new String[] { "REORDER_TASKS", "RECEIVE_BOOT_COMPLETED", "GET_TASKS", "MANAGE_ACTIVITY_STACKS",
			"REMOVE_TASKS", "PERSISTENT_ACTIVITY", "RESTART_PACKAGES", "KILL_BACKGROUND_PROCESSES", "MODIFY_AUDIO_SETTINGS", "SIGNAL_PERSISTENT_PROCESSES",
			"DUMP", "SET_PROCESS_LIMIT", "READ_LOGS", "ACCESS_ALL_EXTERNAL_STORAGE", "SET_ALWAYS_FINISH", "SET_DEBUG_APP", "WRITE_SECURE_SETTINGS",
			"CHANGE_CONFIGURATION", "SYSTEM_ALERT_WINDOW", "ACCESS_MTP", "HARDWARE_TEST", "MANAGE_USB", "READ_PRECISE_PHONE_STATE", "READ_PHONE_STATE",
			"BIND_CALL_SERVICE", "MODIFY_PHONE_STATE", "PROCESS_OUTGOING_CALLS", "CALL_PHONE", "USE_SIP", "READ_PRIVILEGED_PHONE_STATE", "DISABLE_KEYGUARD", "EXPAND_STATUS_BAR",
			"READ_EXTERNAL_STORAGE", "WRITE_MEDIA_STORAGE", "WRITE_EXTERNAL_STORAGE", "MANAGE_DOCUMENTS", "SET_TIME_ZONE",
			"ASEC_RENAME","START_ANY_ACTIVITY","MOUNT_FORMAT_FILESYSTEMS","REMOTE_AUDIO_PLAYBACK","CLEAR_APP_CACHE",
			"MOUNT_UNMOUNT_FILESYSTEMS","ASEC_ACCESS","GLOBAL_SEARCH","MANAGE_USERS","SET_PREFERRED_APPLICATIONS","SET_SCREEN_COMPATIBILITY",
			"SUBSCRIBED_FEEDS_READ","BROADCAST_STICKY","GET_PACKAGE_SIZE","ACCESS_MOCK_LOCATION","INSTALL_SHORTCUT","NET_ADMIN","CHANGE_BACKGROUND_DATA_SETTING",
			"BLUETOOTH_STACK","GET_DETAILED_TASKS","SET_WALLPAPER_COMPONENT","INTERACT_ACROSS_USERS_FULL","WRITE_APN_SETTINGS","ASEC_CREATE",
			"BROADCAST_PACKAGE_REMOVED","SET_ANIMATION_SCALE","BATTERY_STATS","UNINSTALL_SHORTCUT","FORCE_STOP_PACKAGES","GLOBAL_SEARCH_CONTROL",
			"GET_APP_OPS_STATS","MODIFY_APPWIDGET_BIND_PERMISSIONS","ASEC_MOUNT_UNMOUNT","WRITE_SETTINGS","WRITE_DREAM_STATE","INTERACT_ACROSS_USERS",
			"ACCESS_LOCATION_EXTRA_COMMANDS","ASEC_DESTROY","NET_TUNNELING","DIAGNOSTIC","SUBSCRIBED_FEEDS_WRITE","READ_DREAM_STATE", "SET_ALARM",
			"SET_WALLPAPER_HINTS", "SET_WALLPAPER"}),
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

	public String[] getGroups() {
		return groups;
	}

	public String[] getPermissionNames() {
		return names;
	}
}
