package com.simplesoft.simplesofttemplate.main.view.control;

import android.view.View;
import android.view.ViewGroup;

/**
 * IViewHolder.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 11:25:31 21 Jul 2014
 */
public abstract class BaseViewHolder<T> {
	private ListViewEventReceiver<T> event;
	protected T dto;
	public int pos;
	public abstract View initView(ViewGroup parent);
	protected abstract void resetView();
	protected abstract void renderView(T dto, int pos);
	public abstract BaseViewHolder<T> clone();
	
	public final void setListViewEventReceiver(ListViewEventReceiver<T> e){
		this.event = e;
	}
	
	public final void render(T dto, int pos){
		this.pos = pos;
		this.dto = dto;
		this.resetView();
		this.renderView(dto, pos);
	}
	
	protected final void sendViewHolderEvent(ListViewEventAction action){
		if (this.event != null) {
			ListViewEventData<T> data = new ListViewEventData<T>();
			data.action = action;
			data.dto = this.dto;
			this.event.handleListViewSendEvent(data);
		}
	}
}
