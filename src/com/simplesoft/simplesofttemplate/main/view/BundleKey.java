/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view;

/**
 * BundleKey.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 02:42:43 24 Jul 2014
 */
public enum BundleKey {
	DATA_APP_LIST("DATA_APP_LIST"),
	BC_ID_SEND("BC_ID_SEND"),
	BC_ACTION_SEND("BC_ACTION_SEND"), 
	SEARCH_QUERY("SEARCH_QUERY"),
	;
	
	private String keyName;
	private BundleKey(String pKeyName){
		this.keyName = pKeyName;
	}
	
	/**
	 * @return the keyName
	 */
	public String getName() {
		return keyName;
	}
}
