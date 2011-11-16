package edu.vsu.cs4900;

import java.util.List;

import edu.vsu.cs4900.data.*;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Catalog extends ListActivity {
	private static final String CLASSTAG = Catalog.class.getSimpleName();
	
	private TextView empty;
	private ProgressDialog progressDialog;
	private CatalogAdapter catalogAdapter;
	private List<CatalogEntry> catalog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(Constants.LOGTAG, " " + Catalog.CLASSTAG + " onCreate");
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
		Log.v(Constants.LOGTAG, " " + Catalog.CLASSTAG + " onResume");

		// Parse the data from catalog.xml file
		catalog = CatalogList.parse(this).getAllCatalogEntries();
		catalogAdapter = new CatalogAdapter(Catalog.this, catalog, 0); // 0:
																																					// Sort
																																					// by
																																					// title
		setListAdapter(catalogAdapter);
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
