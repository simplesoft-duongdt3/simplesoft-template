package com.simplesoft.simplesofttemplate.main.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.simplesoft.simplerootcheck.R;
import com.simplesoft.simplesofttemplate.main.controller.IRequestView;
import com.simplesoft.simplesofttemplate.main.controller.RequestAction;
import com.simplesoft.simplesofttemplate.main.controller.RequestData;
import com.simplesoft.simplesofttemplate.main.controller.ResponseData;
import com.simplesoft.simplesofttemplate.main.controller.SimpleController;

/**
 * BaseFragment.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 09:39:33 19 Jul 2014
 */
public abstract class BaseFragment extends Fragment implements IRequestView, IBroadCastReceiver{
	
	protected BaseActivity parent;
	protected LinearLayout viewRoot;
	BaseBroadcastReceiver receiver = new BaseBroadcastReceiver(this);
	
	public BaseFragment() {
		super();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		parent = (BaseActivity) activity;		
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//register Broadcast Receiver
		IntentFilter filter = new IntentFilter(BaseBroadcastReceiver.BC_ACTION_SIMPLESOFT);
		AppInfo.getInstance().registerReceiver(receiver, filter);
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_base, null, false);
		viewRoot = (LinearLayout) view.findViewById(R.id.llMain);
		viewRoot.addView(container);
		return view;
	}
	
	/**
	 * get tag of fragment
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 08:48:20 20 Jul 2014
	 * @return: void
	 * @throws:  
	 */
	public String getTAG() {
		return this.getClass().getName();
	}
	
	public void sendViewRequest(RequestAction rqAction) {
		sendViewRequest(rqAction, new Bundle());
	}
	
	public void sendViewRequest(RequestAction rqAction, Bundle data) {
		RequestData rData = new RequestData();
		rData.action = rqAction;
		rData.sender = this;
		rData.viewData = data;
		SimpleController.getInstance().handleViewRequest(rData);
	}
	
	@Override
	public void handleViewDataResponseError(ResponseData rspData) {
		Toast.makeText(parent, rspData.responseMessage, Toast.LENGTH_LONG).show();
	}
		
	@Override
	public void handleViewDataResponseSuccess(ResponseData rspData) {
		Toast.makeText(parent, rspData.rqData.action.getRquestName() + " ", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public Activity getActivityContext() {
		return parent;
	}

	@Override
	public void onReceiverBroadCastSend(String action, Bundle data) {
		if (this != null && this.isVisible()) {
			//nếu chứa action thì thực hiện
			if (data.containsKey(BundleKey.BC_ACTION_SEND.getName())) {
				BroadCastAction bcAction = (BroadCastAction) data.getSerializable(BundleKey.BC_ACTION_SEND.getName());
				doActionBroadCast(bcAction, data);
			}
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		AppInfo.getInstance().unregisterReceiver(receiver);
	}
	
	public <T> void showDialog(BaseDialogFragment<T> fragDialog){
	    fragDialog.show(parent.getSupportFragmentManager(), "dialog");
	}
	
	/*public <T> void showDialog(BaseDialogFragment<T> fragDialog, T dto){
	    fragDialog.show(parent.getSupportFragmentManager(), "dialog");
	}*/
}
