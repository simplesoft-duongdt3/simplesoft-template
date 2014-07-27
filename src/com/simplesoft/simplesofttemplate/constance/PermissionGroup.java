package com.simplesoft.simplesofttemplate.constance;

public enum PermissionGroup {
	ALL("ALL", ""),
	COST_MONEY("COST_MONEY","COST_MONEY"),
	PERSONAL_INFO("PERSONAL_INFO", "ACCOUNTS, BOOKMARKS, CALENDAR, LOCATION, MESSAGES, PERSONAL_INFO, SOCIAL_INFO, SYNC_SETTINGS, USER_DICTIONARY, VOICEMAIL, WRITE_USER_DICTIONARY"),
	AFFECTS_BATTERY("AFFECTS_BATTERY", "AFFECTS_BATTERY"),
	NETWORK("NETWORK", "BLUETOOTH_NETWORK, NETWORK "),
	CAMERA_MICROPHONE("CAMERA_MICROPHONE","CAMERA_MICROPHONE"),
	SYSTEM("SYSTEM","ACCESSIBILITY_FEATURES, APP_INFO, AUDIO_SETTINGS, DEVELOPMENT_TOOLS, DISPLAY, HARDWARE_CONTROLS, PHONE_CALLS,SCREENLOCK, STATUS_BAR, STORAGE, SYSTEM_CLOCK, SYSTEM_TOOLS, DEVICE_ALARMS,WALLPAPER"),
	;

	String name;
	String group;
	private PermissionGroup(String pName, String group) {
		this.name = pName;
		this.group = group;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public boolean contain(String input){
		return group.contains(input);
	}
}
