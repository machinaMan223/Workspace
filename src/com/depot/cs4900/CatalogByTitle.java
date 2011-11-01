
package com.depot.cs4900;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.depot.cs4900.data.*;

public class CatalogByTitle extends ListActivity {
    private static final String CLASSTAG = CatalogByTitle.class.getSimpleName();
//    private static final int MENU_CHANGE_CRITERIA = Menu.FIRST + 1;
//    private static final int MENU_GET_NEXT_PAGE = Menu.FIRST;
//    private static final int NUM_RESULTS_PER_PAGE = 8;
    
    private TextView empty;    
    private ProgressDialog progressDialog;
    private CatalogAdapter catalogAdapter;
    private List<CatalogEntry> catalog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, " " + CatalogByTitle.CLASSTAG + " onCreate");

        this.setContentView(R.layout.catalog_by_title);

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
        Log.v(Constants.LOGTAG, " " + CatalogByTitle.CLASSTAG + " onResume");

        // Parse the data from catalog.xml file
        catalog = CatalogList.parse(this).getAllCatalogEntries();
		catalogAdapter = new CatalogAdapter(CatalogByTitle.this, catalog, 0); //0: Sort by title
		setListAdapter(catalogAdapter);
    }    
//   
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        menu.add(0, ReviewList.MENU_GET_NEXT_PAGE, 0, R.string.menu_get_next_page).setIcon(
//            android.R.drawable.ic_menu_more);
//        menu.add(0, ReviewList.MENU_CHANGE_CRITERIA, 0, R.string.menu_change_criteria).setIcon(
//            android.R.drawable.ic_menu_edit);
//        return true;
//    }    
//
//    @Override
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        Intent intent = null;
//        switch (item.getItemId()) {
//            case MENU_GET_NEXT_PAGE:
//                // increment the startFrom value and call this Activity again
//                intent = new Intent(Constants.INTENT_ACTION_VIEW_LIST);
//                intent.putExtra(Constants.STARTFROM_EXTRA, getIntent().getIntExtra(Constants.STARTFROM_EXTRA, 1)
//                    + ReviewList.NUM_RESULTS_PER_PAGE);
//                startActivity(intent);
//                return true;
//            case MENU_CHANGE_CRITERIA:
//                intent = new Intent(this, ReviewCriteria.class);
//                startActivity(intent);
//                return true;
//        }
//        return super.onMenuItemSelected(featureId, item);
//    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
//        // set the current review to the Application (global state placed there)
//        RestaurantFinderApplication application = (RestaurantFinderApplication) getApplication();
//        application.setCurrentReview(this.reviews.get(position));
//
//        // startFrom page is not stored in application, for example purposes it's a simple "extra"
//        Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_DETAIL);
//        intent.putExtra(Constants.STARTFROM_EXTRA, getIntent().getIntExtra(Constants.STARTFROM_EXTRA, 1));
//        startActivity(intent);
    }    

}
