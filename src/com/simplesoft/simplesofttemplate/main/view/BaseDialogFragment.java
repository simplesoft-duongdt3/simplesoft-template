package com.simplesoft.simplesofttemplate.main.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.simplesoft.simplerootcheck.R;
import com.simplesoft.simplesofttemplate.main.view.control.BaseViewHolder;
import com.simplesoft.simplesofttemplate.main.view.control.ListViewEventReceiver;

/**
 * BaseDialogFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 11:50:42 29 Jul 2014
 */
public class BaseDialogFragment<T> extends DialogFragment {
	
	private BaseViewHolder<T> viewHolder;
	private T dto;

	public BaseDialogFragment(T dto, BaseViewHolder<T> viewHolder, ListViewEventReceiver<T> event, boolean isFullScreen) {
		this.viewHolder = viewHolder;
		this.viewHolder.setListViewEventReceiver(event);
		this.dto = dto;
		if (isFullScreen) {
			setIsFullScreen();
		}
	}
	
	private void setIsFullScreen(){
		this.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_Appthemeblue);
	}
	
	public final void renderDialog(T dto){
		this.dto = dto;
		viewHolder.render(dto, -1);
	}

	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
		View view = viewHolder.initView(container);
		//back ground chung cho dialog
		view.setBackgroundResource(R.drawable.bg_black_pattern);
		renderDialog(dto);
	    return view;
	}
	
	/** The system calls this only when creating the layout in a dialog. */
	@Override
	public final Dialog onCreateDialog(Bundle savedInstanceState) {
	    Dialog dialog = super.onCreateDialog(savedInstanceState);
	    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    return dialog;
	}
}
