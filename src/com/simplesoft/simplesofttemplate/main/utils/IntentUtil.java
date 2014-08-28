package com.simplesoft.simplesofttemplate.main.utils;

import java.io.File;

import android.content.Intent;
import android.net.Uri;

import com.simplesoft.simplesofttemplate.main.view.AppInfo;
import com.simplesoft.simplerootcheck.R;

/**
 * IntentUtil.java
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 21:09:50 7 Aug 2014
 */
public class IntentUtil {
	
	static final String mailUs = "simplesoftsss@gmail.com";
	public static void goToMarketThisApp(){
		goToMarket(SystemUtil.getAppPackageName());
	}
	
	public static void goToMarket(String packName){
		try {
			Intent data = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packName));
			ActivityUtil.switchActivity(data);
		} catch (android.content.ActivityNotFoundException anfe) {
			try {
				Intent data = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + packName));
				ActivityUtil.switchActivity(data);
			} catch (Exception e) {
				LogUtil.log("goToMarket", e);
			}
		}
	}
	
	public static void sendMailFeedBackThisApp(){
		sendMail(mailUs, "[" + StringUtil.getString(R.string.text_feedback) + "] " + SystemUtil.getSystemInfoStr(), "");
	}
	
	public static void sendMail(String mailTo, String subject, String contentMail){
		try {
			
			Intent emailIntent = new Intent(Intent.ACTION_SENDTO
					, Uri.fromParts("mailto", mailTo, null));
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			emailIntent.putExtra(Intent.EXTRA_TEXT, contentMail);
			ActivityUtil.switchActivity(AppInfo.getInstance().getActivityContext(), Intent.createChooser(emailIntent, "Send mail"));
		} catch (Exception e) {
			LogUtil.log("sendMail", e);
		}
		
/*		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
		email.putExtra(Intent.EXTRA_SUBJECT, subject);
		email.putExtra(Intent.EXTRA_TEXT, message);

		// need this to prompts email client only
		email.setType("message/rfc822");

		startActivity(Intent.createChooser(email, "Choose an Email client"));*/
	}

	
	
	public static void shareTextUrlThisApp() {
		shareTextUrl(StringUtil.getString(R.string.app_name), "http://play.google.com/store/apps/details?id=" + SystemUtil.getAppPackageName());
	}
	
	public static void shareTextUrl(String title, String link) {
	    Intent share = new Intent(android.content.Intent.ACTION_SEND);
	    share.setType("text/plain");
	    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
	 
	    // Add data to the intent, the receiving app will decide
	    // what to do with it.
	    share.putExtra(Intent.EXTRA_SUBJECT, title);
	    share.putExtra(Intent.EXTRA_TEXT, link);
	    
	    ActivityUtil.switchActivity(AppInfo.getInstance().getActivityContext(), Intent.createChooser(share, "Share link!"));
	}
	
	public static void shareImage(String imagePath) {
	    Intent share = new Intent(Intent.ACTION_SEND);
	 
	    // If you want to share a png image only, you can do:
	    // setType("image/png"); OR for jpeg: setType("image/jpeg");
	    share.setType("image/*");
	 
	    // Make sure you put example png image named myImage.png in your directory
	    //String imagePath = Environment.getExternalStorageDirectory() + "/myImage.png";
	 
	    File imageFileToShare = new File(imagePath);
	 
	    Uri uri = Uri.fromFile(imageFileToShare);
	    share.putExtra(Intent.EXTRA_STREAM, uri);
	 
	    ActivityUtil.switchActivity(AppInfo.getInstance().getActivityContext(), Intent.createChooser(share, "Share Image!"));
	}
	
}
