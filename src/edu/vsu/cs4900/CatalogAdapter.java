package edu.vsu.cs4900;

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

public class CatalogAdapter extends BaseAdapter {
	private static final String CLASSTAG = CatalogAdapter.class.getSimpleName();
	private final Context context;
	private final List<CatalogEntry> catalog;

	public CatalogAdapter(Context context, List<CatalogEntry> catalog, int order) {
		this.context = context;
		this.catalog = catalog;

		switch (order) {
			case 0: // by title
				Collections.sort(catalog, new Comparator() {

					public int compare(Object o1, Object o2) {
						CatalogEntry ce1 = (CatalogEntry) o1;
						CatalogEntry ce2 = (CatalogEntry) o2;
						return ce1.get_title().compareToIgnoreCase(ce2.get_title());
					}

				});
				break;
		}

		Log.v(Constants.LOGTAG, " " + CatalogAdapter.CLASSTAG + " catalog size - "
				+ this.catalog.size());
	}

	@Override
	public int getCount() {
		return this.catalog.size();
	}

	@Override
	public Object getItem(int position) {
		return this.catalog.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		CatalogEntry re = this.catalog.get(position);
		return new CatalogListView(this.context, re.get_title(), re.get_price());
	}

	private final class CatalogListView extends LinearLayout {

		private TextView title;
		private TextView price;

		public CatalogListView(Context context, String title, String price) {

			super(context);
			setOrientation(LinearLayout.VERTICAL);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 3, 5, 0);

			this.title = new TextView(context);
			this.title.setText(title);
			this.title.setTextSize(16f);
			this.title.setTextColor(Color.WHITE);
			this.addView(this.title, params);

			this.price = new TextView(context);
			this.price.setText("$ " + price);
			this.price.setTextSize(8f);
			this.price.setTextColor(Color.GRAY);
			this.addView(this.price, params);
		}
	}
}
