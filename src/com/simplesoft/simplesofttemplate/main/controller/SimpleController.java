/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.controller;

import com.simplesoft.simplesofttemplate.main.model.SimpleModel;

/**
 * SimpleController.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 14:02:13 20 Jul 2014
 */
public class SimpleController extends BaseController {

	@Override
	public void handleModelResponse(final ResponseData rspData) {
		if (rspData.rqData.sender != null && rspData.rqData.sender.getActivityContext() != null) {
			rspData.rqData.sender.getActivityContext().runOnUiThread(new Runnable() {
				public void run() {
					rspData.rqData.sender.handleViewDataResponse(rspData);
				}
			});
		}
	}

	@Override
	public void handleModelResponseError(final ResponseData rspData) {
		if (rspData.rqData.sender != null && rspData.rqData.sender.getActivityContext() != null) {
			rspData.rqData.sender.getActivityContext().runOnUiThread(new Runnable() {
				public void run() {
					rspData.rqData.sender.handleViewDataResponseError(rspData);
				}
			});
		}
	}

	@Override
	protected Object requestDataByView(RequestData e) throws Exception {
		Object data = null;
		if (e.action == RequestAction.GET_LIST_APP) {
			data = SimpleModel.getAllAppInfo();
		}
		return data;
	}

	static SimpleController controller;
	private SimpleController(){
	}
	/**
	 * get instance of Controller
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 15:09:23 20 Jul 2014
	 * @return: void
	 * @throws:  
	 */
	public static BaseController getInstance() {
		if (controller == null) {
			controller = new SimpleController();
		}
		return controller;
	}

}
