/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view.control;

import android.view.View;

/**
 * IViewHolder.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 11:25:31 21 Jul 2014
 */
public abstract class BaseViewHolder<T> {
	private ListViewEventReceiver<T> event;
	private T dto;
	public abstract View initView();
	protected abstract void resetView();
	protected abstract void renderView(T dto);
	public abstract BaseViewHolder<T> clone();
	
	public final void setListViewEventReceiver(ListViewEventReceiver<T> e){
		this.event = e;
	}
	
	public final void render(T dto){
		this.dto = dto;
		this.resetView();
		this.renderView(dto);
	}
	
	protected final void sendViewHolderEvent(ListViewEventAction action){
		if (this.event != null) {
			ListViewEventData<T> data = new ListViewEventData<T>();
			data.action = action;
			data.dto = this.dto;
			this.event.onListViewSendEvent(data);
		}
	}
}
