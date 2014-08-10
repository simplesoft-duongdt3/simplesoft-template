package com.simplesoft.simplesofttemplate.main.controller;

import android.app.Activity;
import android.os.Bundle;

/**
 * IRequestView.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 15:05:43 20 Jul 2014
 */
public interface IRequestView {
	void sendViewRequest(RequestAction action);
	void sendViewRequest(RequestAction action, Bundle data);
	void handleViewDataResponseSuccess(ResponseData rspData);
	void handleViewDataResponseError(ResponseData rspData);
	Activity getActivityContext();
}
