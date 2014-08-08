/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.function.DTO;

import java.io.Serializable;

import com.simplesoft.simplesofttemplate.main.view.control.ListViewEventAction;

/**
 * ListViewItem.java
 * 
 * @author: duongdt3
 * @version: 1.0
 * @since: 1.0
 * @time: 11:18:33 8 Aug 2014
 */
public class ListViewItemInfo implements Serializable {
	private static final long serialVersionUID = -4791710595637747211L;

	public ListViewItemInfo(String pName, int pIcon, ListViewEventAction pAction, Object pData) {
		this.name = pName;
		this.data = pData;
		this.icon = pIcon;
		this.action = pAction;
	}

	public String name;
	public Object data;;
	public int icon;
	public ListViewEventAction action;
}
