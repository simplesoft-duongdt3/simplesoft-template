package com.simplesoft.simplesofttemplate.main.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

/**
 * BaseBroadcastReceiver.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 10:42:44 27 Jul 2014
 */
public class BaseBroadcastReceiver extends BroadcastReceiver {

	public final static String BC_ACTION_SIMPLESOFT = "SIMPLESOFT";
	private long id;
	private IBroadCastReceiver receiver;
	public BaseBroadcastReceiver(IBroadCastReceiver pReceiver) {
		this.id = SystemClock.elapsedRealtime();
		this.receiver = pReceiver;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//nếu xử lý action chung trong ứng dụng
		if (BC_ACTION_SIMPLESOFT.equals(intent.getAction())) {
			
			int idSend = intent.getIntExtra(BundleKey.BC_ID_SEND.getName(), 0);
			//nếu không phải do chính nó gởi, thì tiến hành nhận
			if (this.id != idSend) {
				if (this.receiver != null) {
					Bundle data = intent.getExtras() != null ? intent.getExtras() : new Bundle();
					this.receiver.onReceiverBroadCastSend(intent.getAction(), data);
				}
			}
		}
	}

}
