/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.controller;

import android.app.Activity;
import android.os.Bundle;

import com.simplesoft.simplesofttemplate.main.controller.BaseController.RequestAction;
import com.simplesoft.simplesofttemplate.main.controller.BaseController.ResponseData;

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
	void handleViewDataResponse(ResponseData rspData);
	void handleViewDataResponseError(ResponseData rspData);
	Activity getActivityContext();
}
