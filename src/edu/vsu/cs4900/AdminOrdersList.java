package edu.vsu.cs4900;

import java.util.List;

import edu.vsu.cs4900.data.OrderEntry;
import edu.vsu.cs4900.data.OrderList;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class AdminOrdersList extends ListActivity {
private static final String CLASSTAG = AdminOrdersList.class.getSimpleName();
	
	private TextView empty;
	private ProgressDialog progressDialog;
	private OrderAdapter orderAdapter;
	private List<OrderEntry> orders;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(Constants.LOGTAG, " " + AdminOrdersList.CLASSTAG + " onCreate");
		setContentView(R.layout.acm_admin_orders);
		
		this.empty = (TextView) findViewById(R.id.empty);

		// set list properties
		final ListView listView = getListView();
		listView.setItemsCanFocus(false);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setEmptyView(this.empty);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.v(Constants.LOGTAG, " " + AdminOrdersList.CLASSTAG + " onResume");

		// Parse the data from catalog.xml file
		orders = OrderList.parse(this).getAllOrderEntries();
		orderAdapter = new OrderAdapter(AdminOrdersList.this, orders); 
		
		setListAdapter(orderAdapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// // set the current review to the Application (global state placed there)
		// RestaurantFinderApplication application = (RestaurantFinderApplication)
		// getApplication();
		// application.setCurrentReview(this.reviews.get(position));
		//
		// // startFrom page is not stored in application, for example purposes it's
		// a simple "extra"
		// Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_DETAIL);
		// intent.putExtra(Constants.STARTFROM_EXTRA,
		// getIntent().getIntExtra(Constants.STARTFROM_EXTRA, 1));
		// startActivity(intent);
	}
}
