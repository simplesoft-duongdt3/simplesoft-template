package com.simplesoft.simplesofttemplate.main.view.control;

/**
 * ListViewEvent.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 12:28:22 21 Jul 2014
 */
public interface ListViewEventReceiver<T> {
	void handleListViewSendEvent(ListViewEventData<T> data);
}
