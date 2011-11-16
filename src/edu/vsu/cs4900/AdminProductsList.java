package edu.vsu.cs4900;

import java.util.List;

import edu.vsu.cs4900.data.CatalogEntry;
import edu.vsu.cs4900.data.CatalogList;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class AdminProductsList extends ListActivity {
	private static final String CLASSTAG = AdminProductsList.class.getSimpleName();
	private static final int MENU_CREATE = Menu.FIRST;
	
	private TextView empty;
	private ProgressDialog progressDialog;
	private CatalogAdapter catalogAdapter;
	private List<CatalogEntry> catalog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(Constants.LOGTAG, " " + AdminProductsList.CLASSTAG + " onCreate");
		setContentView(R.layout.acm_store);
		
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
		Log.v(Constants.LOGTAG, " " + AdminProductsList.CLASSTAG + " onResume");

		// Parse the data from catalog.xml file
		catalog = CatalogList.parse(this).getAllCatalogEntries();
		catalogAdapter = new CatalogAdapter(AdminProductsList.this, catalog, 0); // 0:
																																					// Sort
																																					// by
																																					// title
		setListAdapter(catalogAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, AdminProductsList.MENU_CREATE, 0, R.string.menu_product_create)
				.setIcon(android.R.drawable.ic_menu_add);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case MENU_CREATE:
			try {
				// Perform action on click
				Intent intent = new Intent(
						Constants.INTENT_ACTION_NEW_PRODUCT);
				startActivity(intent);
			} catch (Exception e) {
				Log.i(Constants.LOGTAG + ": " + AdminProductsList.CLASSTAG,
						"Failed to create a new product [" + e.getMessage() + "]");
			}
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
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
		
		Intent intent = new Intent(Constants.INTENT_ACTION_PRODUCT_DETAIL);
    intent.putExtras(catalog.get((int)id).toBundle());
    startActivity(intent);
	}
}
