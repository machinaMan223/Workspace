package edu.vsu.cs4900;

import java.util.List;

import edu.vsu.cs4900.R;

import edu.vsu.cs4900.EventAdapter;
import edu.vsu.cs4900.EventsCalendarList;
import edu.vsu.cs4900.Constants;
import edu.vsu.cs4900.data.EventEntry;
import edu.vsu.cs4900.data.EventList;

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

public class EventsCalendarList extends ListActivity {
	private static final String CLASSTAG = EventsCalendarList.class.getSimpleName();
    private static final int MENU_CHANGE_CRITERIA = Menu.FIRST + 1;
    private static final int MENU_GET_NEXT_PAGE = Menu.FIRST;
    private static final int NUM_RESULTS_PER_PAGE = 8;
  
    private TextView empty;    
    private ProgressDialog progressDialog;
    private EventAdapter eventAdapter;
    private List<EventEntry> event;
  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.acm_events_calendar_list);

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
        Log.v(Constants.LOGTAG, " " + EventsCalendarList.CLASSTAG + " onResume");

        // Parse the data from event.xml file
        event = EventList.parse(this).getAllEventEntries();
		eventAdapter = new EventAdapter(EventsCalendarList.this, event, 0); //0: Sort by title
		setListAdapter(eventAdapter);
    }    
   
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        menu.add(0, EventList.MENU_GET_NEXT_PAGE, 0, R.string.menu_get_next_page).setIcon(
//            android.R.drawable.ic_menu_more);
//        menu.add(0, EventList.MENU_CHANGE_CRITERIA, 0, R.string.menu_change_criteria).setIcon(
//            android.R.drawable.ic_menu_edit);
//        return true;
//    }    

//    @Override
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        Intent intent = null;
//        switch (item.getItemId()) {
//            case MENU_GET_NEXT_PAGE:
//                // increment the startFrom value and call this Activity again
//                intent = new Intent(Constants.INTENT_ACTION_VIEW_LIST);
//                intent.putExtra(Constants.STARTFROM_EXTRA, getIntent().getIntExtra(Constants.STARTFROM_EXTRA, 1)
//                    + EventList.NUM_RESULTS_PER_PAGE);
//                startActivity(intent);
//                return true;
//            case MENU_CHANGE_CRITERIA:
//                intent = new Intent(this, EventCriteria.class);
//                startActivity(intent);
//                return true;
//        }
//        return super.onMenuItemSelected(featureId, item);
//    }
    
    @Override
  	protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent intent = new Intent(Constants.INTENT_ACTION_EVENT_CAL_DETAIL);
      intent.putExtras(event.get((int)id).toBundle());
      startActivity(intent);
    	
    }
}
