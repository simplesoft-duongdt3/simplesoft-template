/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.controller;

import java.io.Serializable;

import android.os.Bundle;

public class RequestData implements Serializable{
	private static final long serialVersionUID = -4413016828948977395L;
	public RequestAction action;
	public Bundle viewData;
	public IRequestView sender;
}