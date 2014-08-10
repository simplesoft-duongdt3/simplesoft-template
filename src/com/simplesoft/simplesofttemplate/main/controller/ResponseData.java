package com.simplesoft.simplesofttemplate.main.controller;

import java.io.Serializable;

import com.simplesoft.simplesofttemplate.main.controller.BaseController.ResponseCode;

public class ResponseData implements Serializable{
	private static final long serialVersionUID = 7574659583474540680L;
	public RequestData rqData;
	public Object data;
	public ResponseCode responseCode = ResponseCode.SUSSESS;
	public String responseMessage;
}