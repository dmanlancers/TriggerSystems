package com.wetrig.dev.wetrig.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.wetrig.dev.wetrig.MyApplication;
import com.wetrig.dev.wetrig.POJO.Child;
import com.wetrig.dev.wetrig.POJO.Group;
import com.wetrig.dev.wetrig.R;

import java.util.ArrayList;

public class ExpandListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<Group> groups;

	ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

	public ExpandListAdapter(Context context, ArrayList<Group> groups) {
		this.context = context;
		this.groups = groups;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		ArrayList<Child> chList = groups.get(groupPosition).getItems();
		return chList.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {



		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {

		Child child = (Child) getChild(groupPosition, childPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.child_item, null);
		}

		if (imageLoader == null)
			imageLoader = MyApplication.getInstance().getImageLoader();

		TextView tv = (TextView) convertView.findViewById(R.id.systemName);
		NetworkImageView iv = (NetworkImageView) convertView
				.findViewById(R.id.sysImg);

		tv.setText(child.getName().toString());
		iv.setImageUrl(child.getImage(), imageLoader);

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		ArrayList<Child> chList = groups.get(groupPosition).getItems();

		return chList.size();
	}

	@Override
	public Object getGroup(int groupPosition) {

		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {

		return groups.size();
	}

	@Override
	public long getGroupId(int groupPosition) {


		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {
		Group group = (Group) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater inf = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = inf.inflate(R.layout.group_item, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.group_name);
		NetworkImageView imgView = (NetworkImageView) convertView.findViewById(R.id.folderImg);
		tv.setText(group.getName());
		imgView.setImageUrl(group.getImage(), imageLoader);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {


		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {



		return true;

	}

}