package com.simplesoft.simplesofttemplate.function.adapter;

import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.simplesoft.simplesappspermissions.R;
import com.simplesoft.simplesofttemplate.function.DTO.AppItemInfo;
import com.simplesoft.simplesofttemplate.main.utils.LogUtil;
import com.simplesoft.simplesofttemplate.main.utils.StringUtil;
import com.simplesoft.simplesofttemplate.main.view.AppInfo;

/**
 * Mo ta muc dich cua class
 * @author: DungNX
 * @version: 1.0
 * @since: 1.0
 */

public class AppListAdapter extends ArrayAdapter<AppItemInfo> {

	Context context;
	List<AppItemInfo> listAppInfo;
	PackageManager pm = AppInfo.getInstance().getPackageManager();

	/**
	 * @param context
	 * @param resource
	 * @param objects
	 */
	public AppListAdapter(Context context, List<AppItemInfo> objects) {
		super(context, R.layout.app_list_item, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.listAppInfo = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View rowView = convertView;
		
		if(rowView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.app_list_item, parent, false);
			ViewHolder vHolder = new ViewHolder();
			vHolder.ivAppIcon = (ImageView) rowView.findViewById(R.id.ivAppIcon);
			vHolder.tvAppName = (TextView) rowView.findViewById(R.id.tvAppName);
			vHolder.tvVersion = (TextView) rowView.findViewById(R.id.tvVersion);
			rowView.setTag(vHolder);
		}
		
		ViewHolder vHolder = (ViewHolder) rowView.getTag();
		AppItemInfo appItemInfo = listAppInfo.get(position);
		Drawable icon = null;
		try {
			icon = pm.getApplicationIcon(appItemInfo.packageName);
			vHolder.ivAppIcon.setImageDrawable(icon);
		} catch (NameNotFoundException e) {
			LogUtil.log(e);
		}
		vHolder.tvAppName.setText(appItemInfo.name);
		vHolder.tvVersion.setText(StringUtil.getString(R.string.text_version) + ": " + appItemInfo.versionName);
		
		return rowView;
	}

	class ViewHolder {
		ImageView ivAppIcon;
		TextView tvAppName;
		TextView tvVersion;
	}

	
}
