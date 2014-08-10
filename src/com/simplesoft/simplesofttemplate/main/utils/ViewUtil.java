package com.simplesoft.simplesofttemplate.main.utils;

import android.view.View;
import android.view.ViewGroup;

import com.simplesoft.simplesofttemplate.main.view.AppInfo;

/**
 * ViewUtil.java
 * 
 * @author: duongdt3
 * @version: 1.0
 * @since: 1.0
 * @time: 12:42:52 20 Jul 2014
 */
public class ViewUtil {

	/**
	 * Change int dip 2 px
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 13:02:48 20 Jul 2014
	 * @return: int
	 * @throws:  
	 * @param dips
	 * @return
	 */
	public static int dip2Pixel(int dips) {
		int ret = (int) (AppInfo.getInstance().getResources().getDisplayMetrics().density * dips + 0.5f);
		return ret;
	}

	/**
	 * Change float dip 2 px
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 13:03:00 20 Jul 2014
	 * @return: int
	 * @throws:  
	 * @param dips
	 * @return
	 */
	public static int dip2Pixel(float dips) {
		int ret = (int) (AppInfo.getInstance().getResources().getDisplayMetrics().density * dips + 0.5f);
		return ret;
	}
	/**
	 *  Set background for View, by drawable selector
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 13:01:39 20 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param v
	 * @param idResourceBackground
	 */
	public static void setBackgroundBySelector(View v, int idResourceBackground) {
		setBackgroundByReSource(v, idResourceBackground,
				TYPE_BACKGROUND_DRAWABLE_SELECTOR);
	}

	/**
	 * Set background for View by color
	 * @author: duongdt3
	 * @since: 1.0
	 * @time: 13:01:26 20 Jul 2014
	 * @return: void
	 * @throws:  
	 * @param v
	 * @param color
	 */
	public static void setBackgroundByColor(View v, int color) {
		setBackgroundByReSource(v, color, TYPE_BACKGROUND_COLOR);
	}

	static final int TYPE_BACKGROUND_DRAWABLE_SELECTOR = 1;
	static final int TYPE_BACKGROUND_COLOR = 2;

	static void setBackgroundByReSource(View v, int resource, int typeBackground) {
		// store layout param
		int paddingLeft = v.getPaddingLeft();
		int paddingRight = v.getPaddingRight();
		int paddingTop = v.getPaddingTop();
		int paddingBottom = v.getPaddingBottom();
		ViewGroup.LayoutParams viewParam = v.getLayoutParams();

		switch (typeBackground) {
		case TYPE_BACKGROUND_DRAWABLE_SELECTOR:
			// set background
			v.setBackgroundResource(resource);
			break;
		case TYPE_BACKGROUND_COLOR:
			// set background
			v.setBackgroundColor(resource);
			break;

		default:
			break;
		}

		// re layout
		if (viewParam != null) {
			v.setLayoutParams(viewParam);
		}

		v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
	}
}
