package com.simplesoft.simplesofttemplate.main.view;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import com.simplesoft.simplesofttemplate.function.DTO.ListAppItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.CollectionUtil.Operator;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;

/**
 * Aplication of SimpleSoft App
 * @author: duongdt3
 * @version: 1.0 
 * @since:  1.0
 * @time: 18:43:32 19 Jul 2014
 */
public class AppInfo extends Application {
	
	private static AppInfo instance = null;
	BaseActivity activityContext = null;
	// khong doi
	public static final String DEV_ID = "107535046";
	// doi
	public static final String APP_ID = "207236345";
	
	boolean isForeground = false;
	String lastActivityCall = "";
	public ListAppItemInfo listData;
	public Operator opSystemApp = Operator.NOT;
	public String strQueryApp = "";
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        // setup handler for uncaught exception 
        Thread.setDefaultUncaughtExceptionHandler(_unCaughtExceptionHandler);
        
		//lấy thông in debug mode
		boolean isDebug = false;
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			isDebug = ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
			
		} catch (Exception ex) {
			isDebug = false;
		}
		//set debug mode to logger 
		LogUtil.setIsDebugMode(isDebug); 
	}
	
	public static AppInfo getInstance() {
		return instance;
	}
	
	/**
	 * @return the activityContext
	 */
	public BaseActivity getActivityContext() {
		return activityContext;
	}
	
	/**
	 * @param activityContext the activityContext to set
	 */
	public void setActivityContext(BaseActivity activityContext) {
		this.activityContext = activityContext;
	}
	
	
	/**
	 * call it when stop activity
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 16:56:39 7 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param ac
	 */
	public void callActivityStop(Activity ac){
		if(ac == null || lastActivityCall.equals(ac.getClass().getName())){
			isForeground = false;
		}
	}
	
	/**
	 * call it when resume Activity
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 16:56:22 7 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param ac
	 */
	public void callActivityResume(Activity ac){
		lastActivityCall = (ac != null ? ac.getClass().getName() : "");
		isForeground = true;
	}
	
	/**
	 * Check app is foreground
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 16:56:00 7 Jul 2014
	 * @return: boolean
	 * @throws:  
	 * @return
	 */
	public boolean isAppForeground(){
		return isForeground;
	}
	
	 // uncaught exception handler variable
    private UncaughtExceptionHandler defaultUEH;

    // handler listener
    private Thread.UncaughtExceptionHandler _unCaughtExceptionHandler =
        new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {

               /* // here I do logging of exception to a db
                PendingIntent myActivity = PendingIntent.getActivity(getContext(),
                    192837, new Intent(getContext(), MyActivity.class),
                    PendingIntent.FLAG_ONE_SHOT);

                AlarmManager alarmManager;
                alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 
                    15000, myActivity );
                System.exit(2);*/

            	LogUtil.logWtf(ex);
                // re-throw critical exception further to the os (important)
                defaultUEH.uncaughtException(thread, ex);
            }
     };

}
