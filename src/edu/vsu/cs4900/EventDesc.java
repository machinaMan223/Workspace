package edu.vsu.cs4900;

import edu.vsu.cs4900.data.EventEntry;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EventDesc extends Activity {
	private static final String CLASSTAG = EventDesc.class.getSimpleName();
	
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
}
