package edu.vsu.cs4900;

import java.util.Collections;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.vsu.cs4900.data.*;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RosterAdapter extends BaseAdapter {
	private static final String CLASSTAG = RosterAdapter.class.getSimpleName();
	private final Context context;
	private final List<UsersEntry> users;

	public RosterAdapter(Context context, List<UsersEntry> users, int order) {
		this.context = context;
		this.users = users;

		

		Log.v(Constants.LOGTAG, " " + RosterAdapter.CLASSTAG + " Roster size - "
				+ this.users.size());
	}

	@Override
	public int getCount() {
		return this.users.size();
	}

	@Override
	public Object getItem(int position) {
		return this.users.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		UsersEntry re = this.users.get(position);
		return new RosterListView(this.context, re.get_fullName(), re.get_classification());
	}

	private final class RosterListView extends LinearLayout {

		private TextView fullName;
		private TextView classification;

		public RosterListView(Context context, String fullName, String classification) {

			super(context);
			setOrientation(LinearLayout.VERTICAL);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 3, 5, 0);

			this.fullName = new TextView(context);
			this.fullName.setText(fullName);
			this.fullName.setTextSize(16f);
			this.fullName.setTextColor(Color.WHITE);
			this.addView(this.fullName, params);

			this.classification = new TextView(context);
			this.classification.setText(classification);
			this.classification.setTextSize(8f);
			this.classification.setTextColor(Color.GRAY);
			this.addView(this.classification, params);
		}
	}
}