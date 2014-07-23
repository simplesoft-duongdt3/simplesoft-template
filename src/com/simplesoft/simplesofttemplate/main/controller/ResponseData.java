/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.controller;

import java.io.Serializable;

import com.simplesoft.simplesofttemplate.main.controller.BaseController.ErrorCode;

public class ResponseData implements Serializable{
	private static final long serialVersionUID = 7574659583474540680L;
	public RequestData rqData;
	public Object data;
	public ErrorCode errorCode = ErrorCode.SUSSESS;
	public String errorMessage;
}