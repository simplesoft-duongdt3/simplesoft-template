/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.controller;

import java.io.Serializable;

import android.os.Bundle;
import android.os.SystemClock;

public class RequestData implements Serializable{
	private static final long serialVersionUID = -4413016828948977395L;
	public RequestAction action;
	public Bundle viewData;
	public IRequestView sender;
	public long timeStart = 0;
	public long timeEnd = 0;
	public long timeExecute = 0;
	public long timeEndRender = 0;
	public long timeExecuteRender = 0;
	public long timeTotal = 0;
	public RequestData() {
		timeStart = SystemClock.elapsedRealtime();
	}
}