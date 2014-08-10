package com.simplesoft.simplesofttemplate.main.controller;

import android.os.SystemClock;

import com.simplesoft.simpleappspermissions.R;
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
					switch (rspData.responseCode) {
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
		final String newThreadName = e.action.getRquestName();
		Thread thread = new Thread(newThreadName) {
		      public void run(){
		    	  LogUtil.log("Start thread " + newThreadName);
		    	  
		    	  ResponseData rpData = new ResponseData();
		    	  rpData.rqData = e;
		    	  try {
		    		  Object dto = requestDataByView(e);
		    		  if (dto == null) {
		    			  throw new Exception("ResponseData null");
		    		  }
		    		  rpData.data = dto;
		    		  rpData.responseCode = ResponseCode.SUSSESS;
		    		  rpData.responseMessage = e.action.getRquestName() + " " + AppInfo.getInstance().getString(R.string.text_success);
		    	  } catch (Exception ex) {
		    		  rpData.responseCode = ResponseCode.ERROR_COMMON;
		    		  rpData.responseMessage = e.action.getRquestName() 
		    				  + " " + AppInfo.getInstance().getString(R.string.text_error);
		    		  //chi tiết lỗi sẽ gồm tên Controller + action
		    		  String logDecription = String.format("ActionCode: %s", e.action.getRquestName());
		    		  //log to Console 
		    		  LogUtil.log(logDecription, ex);
		    	  } finally{
		    		  BaseController.this.handleModelResponseData(rpData);
		    		  
		    		  LogUtil.log("End thread " + newThreadName);
		    	  }
		      }
		   };
		   thread.start();
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
	
    public enum ResponseCode{
    	SUSSESS,
    	ERROR_COMMON;
    }
}
