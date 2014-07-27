/**
 * Copyright 2014 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.simplesoft.simplesofttemplate.main.view;

import android.os.Bundle;

/**
 * IBroadCast.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 10:22:38 27 Jul 2014
 */
public interface IBroadCastReceiver {
	void onReceiverBroadCastSend(String action, Bundle data);
	void doActionBroadCast(BroadCastAction action, Bundle data);
}