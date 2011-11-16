package edu.vsu.cs4900;

import edu.vsu.cs4900.data.EventEntry;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class EventDesc extends Activity {
	private static final String CLASSTAG = EventDesc.class.getSimpleName();
	private static final int MENU_MAP_REVIEW = Menu.FIRST;
	
	private TextView tvName;
	private TextView tvStart;
	private TextView tvStop;
	private TextView tvLoc;
	private TextView tvAddy;
	private TextView tvDesc;
	
	private EventEntry event;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_desc);
		
		event = EventEntry.fromBundle(getIntent().getExtras());
		
		tvName = (TextView) findViewById(R.id.tvName);
		tvName.setText(event.get_name());
		tvName.setTextSize(14f);
		tvStart = (TextView) findViewById(R.id.tvStart);
		tvStart.setText(event.get_start_at());
		tvStart.setTextSize(10f);
		tvStop = (TextView) findViewById(R.id.tvStop);
		tvStop.setText(event.get_end_at());
		tvStop.setTextSize(10f);
		tvLoc = (TextView) findViewById(R.id.tvLoc);
		tvLoc.setText(event.get_location());
		tvLoc.setTextSize(10f);
		tvAddy = (TextView) findViewById(R.id.tvAddress);
		tvAddy.setText(event.get_address());
		tvAddy.setTextSize(10f);
		tvDesc = (TextView) findViewById(R.id.tvDesc);
		tvDesc.setText(event.get_description());
		tvDesc.setTextSize(10f);
	}
	
	@Override
  public boolean onCreateOptionsMenu(Menu menu) {
      super.onCreateOptionsMenu(menu);
      menu.add(0, EventDesc.MENU_MAP_REVIEW, 0, R.string.menu_map_view).setIcon(
          android.R.drawable.ic_menu_mapmode);
      return true;
  }
	
	@Override
  public boolean onMenuItemSelected(int featureId, MenuItem item) {
      Intent intent = null;
      switch (item.getItemId()) {
          case MENU_MAP_REVIEW:
              Log.v(Constants.LOGTAG, " " + EventDesc.CLASSTAG + " MAP ");
              if ((this.event.get_address() != null) && !this.event.get_address().equals("")) {
                  intent = new Intent(Intent.ACTION_VIEW, Uri
                      .parse("geo:0,0?q=" + this.event.get_address().toString()));
                  startActivity(intent);
              } else {
                  new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.alert_label)).setMessage(
                      R.string.no_location_message).setPositiveButton("Continue", new OnClickListener() {
                      public void onClick(final DialogInterface dialog, final int arg1) {
                      }
                  }).show();
              }
              return true;
      }
      return super.onMenuItemSelected(featureId, item);
  }
}
