/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.controller;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.simplesoft.simplesappspermissions.R;
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
    public final void handleModelResponseData(final ResponseData rspData){
    	//tính toán thời gian truy vấn dữ liệu
    	rspData.rqData.timeEnd = SystemClock.elapsedRealtime();
    	rspData.rqData.timeExecute = rspData.rqData.timeEnd - rspData.rqData.timeStart;
    	//ghi log thời gian thực thi render
	    LogUtil.log("Time execute " + rspData.rqData.action.getRquestName() + ": " + rspData.rqData.timeExecute);
	    
    	if (rspData.rqData.sender != null && rspData.rqData.sender.getActivityContext() != null) {
			rspData.rqData.sender.getActivityContext().runOnUiThread(new Runnable() {
				public void run() {
					switch (rspData.errorCode) {
					case SUSSESS:
						rspData.rqData.sender.handleViewDataResponseSuccess(rspData);
						break;
					case ERROR_COMMON:
						rspData.rqData.sender.handleViewDataResponseError(rspData);
						break;
					default:
						break;
				}    	
					
				//tính toán thời gian render
				rspData.rqData.timeEndRender = SystemClock.elapsedRealtime();
			    rspData.rqData.timeExecuteRender = rspData.rqData.timeEndRender - rspData.rqData.timeEnd;
			    //ghi log thời gian thực thi render
			    LogUtil.log("Time execute render " + rspData.rqData.action.getRquestName() + ": " + rspData.rqData.timeExecuteRender);
			    
			    //tính thời gian tổng
			    rspData.rqData.timeTotal = rspData.rqData.timeExecute + rspData.rqData.timeExecuteRender;
			    //ghi log thời gian thực thi
			    LogUtil.log("Total time execute " + rspData.rqData.action.getRquestName() + ": " + rspData.rqData.timeTotal);
				}
			});
		}
    }
    
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
				} catch (Exception ex) {
					String exceptionMessage = LogUtil.getExceptionMessage(ex);
					rpData.errorCode = ErrorCode.ERROR_COMMON;
					rpData.errorMessage = e.action.getRquestName() 
							+ " " + AppInfo.getInstance().getString(R.string.text_error);
					//chi tiết lỗi sẽ gồm tên Controller + action
					String logDecription = String
							.format("Controller: %s ActionCode: %s",
									BaseController.this.getClass().getName(), e.action);
					//log to Console 
					LogUtil.log(logDecription + "\r\n" + exceptionMessage);
				} finally{
					BaseController.this.handleModelResponseData(rpData);
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
	
    public enum ErrorCode{
    	SUSSESS,
    	ERROR_COMMON;
    }
}
