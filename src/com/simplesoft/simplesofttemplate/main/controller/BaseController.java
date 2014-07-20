/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.controller;

import java.io.Serializable;

import android.os.AsyncTask;
import android.os.Bundle;

import com.simplesoft.simplesofttemplate.R;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;


/**
 * BaseController.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 13:46:00 20 Jul 2014
 */
public abstract class BaseController {
    abstract public void handleModelResponse(ResponseData reqData);
    abstract public void handleModelResponseError(ResponseData reqData);
    
    /**
	 * Xử lý event lấy dữ liệu từ View, xử lý lỗi, trả kết quả về View
	 * @author: duongdt3
	 * @since: 09:42:25 8 Apr 2014
	 * @return: void
	 * @throws:  
	 * @param e
	 */
	public void handleViewRequest(final RequestData e){
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
			protected Void doInBackground(Void... params) {
				ResponseData rpData = new ResponseData();
				rpData.rqData = e;
				try {
					Object dto = requestDataByView(e);
					if (dto == null) {
						throw new Exception("ResponseData null");
					}
					rpData.data = dto;
					rpData.errorCode = ErrorCode.SUSSESS;
					BaseController.this.handleModelResponse(rpData);
				} catch (Exception ex) {
					String exceptionMessage = LogUtil.getExceptionMessage(ex);
					rpData.errorCode = ErrorCode.ERROR_COMMON;
					rpData.errorMessage = e.action.getRquestName() 
							+ " " + AppInfo.getInstance().getString(R.string.text_error);
					BaseController.this.handleModelResponseError(rpData);
					//chi tiết lỗi sẽ gồm tên Controller + action
					String logDecription = String
							.format("Controller: %s ActionCode: %s",
									BaseController.this.getClass().getName(), e.action);
					//log to Console 
					LogUtil.log(logDecription + "\r\n" + exceptionMessage);
				}
				return null;
			}
		};
		task.execute();
	}
	
	/**
	 * Điều hướng đến Model tương ứng, lấy dữ liệu
	 * @author: duongdt3
	 * @since: 09:43:11 8 Apr 2014
	 * @return: Object
	 * @throws:  
	 * @param e
	 * @return
	 * @throws Exception
	 */
	abstract protected Object requestDataByView(RequestData e) throws Exception;
	
    public static class RequestData implements Serializable{
		private static final long serialVersionUID = -4413016828948977395L;
		public RequestAction action;
		public Bundle viewData;
		public IRequestView sender;
    }
    
    
    public static class ResponseData implements Serializable{
		private static final long serialVersionUID = 7574659583474540680L;
		public RequestData rqData;
		public Object data;
		public ErrorCode errorCode;
		public String errorMessage;
    }
    
    public enum ErrorCode{
    	SUSSESS,
    	ERROR_COMMON;
    }
    
    public enum RequestAction{
    	GET_LIST_APP("Get list app");
    	private String rquestName;
    	private RequestAction(String rquestName){
    		this.rquestName = rquestName;
    	}
    	
    	/**
		 * @return the rquestName
		 */
		public String getRquestName() {
			return rquestName;
		}
    }
}
