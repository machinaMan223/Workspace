package edu.vsu.cs4900;

import java.util.List;

import edu.vsu.cs4900.data.OrderEntry;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {
	private static final String CLASSTAG = OrderAdapter.class.getSimpleName();
	private final Context context;
	private final List<OrderEntry> orders;

	public OrderAdapter(Context context, List<OrderEntry> orders) {
		this.context = context;
		this.orders = orders;
		
		Log.v(Constants.LOGTAG, " " + OrderAdapter.CLASSTAG + " catalog size - "
				+ this.orders.size());
	}

	@Override
	public int getCount() {
		return this.orders.size();
	}

	@Override
	public Object getItem(int position) {
		return this.orders.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		OrderEntry oe = this.orders.get(position);
		return new OrderListView(this.context, oe.get_order_id(), oe.get_name());
	}

	private final class OrderListView extends LinearLayout {
		private TextView id;
		private TextView name;
		
		public OrderListView(Context context, String id, String name) {
			super(context);
			setOrientation(LinearLayout.VERTICAL);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 3, 5, 0);
			
			this.id = new TextView(context);
			this.id.setText(id);
			this.id.setTextSize(16f);
			this.id.setTextColor(Color.WHITE);
			this.addView(this.id, params);
			
			this.name = new TextView(context);
			this.name.setText(name);
			this.name.setTextSize(16f);
			this.name.setTextColor(Color.GRAY);
			this.addView(this.name, params);
		}
		
	}
}
