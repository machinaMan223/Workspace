package edu.vsu.cs4900;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ACMHome extends Activity {
	private Button btnEventCalendar;
	private Button btnRoster;
	private Button btnCatalog;
	private Button btnAdmin;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acm_home);
        
        this.btnEventCalendar = (Button) findViewById(R.id.events_calendar_button);
        this.btnRoster = (Button) findViewById(R.id.roster_button);
        this.btnCatalog = (Button) findViewById(R.id.catalog_button);
        this.btnAdmin = (Button) findViewById(R.id.admin_button);
        
        this.btnEventCalendar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("edu.vsu.cs4900.LIST_EVENT_CALENDAR");
				startActivity(intent);
			}
		});
        this.btnRoster.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("edu.vsu.cs4900.LIST_ROSTER");
				startActivity(intent);
			}
		});
        this.btnCatalog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("edu.vsu.cs4900.SHOW_STORE");
				startActivity(intent);
			}
		});
        this.btnAdmin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("edu.vsu.cs4900.SHOW_ADMIN");
				startActivity(intent);
			}
		});
    }
}