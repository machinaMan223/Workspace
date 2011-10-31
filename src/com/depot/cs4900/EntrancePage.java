package com.depot.cs4900;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.RadioGroup;
import android.widget.RadioButton;

public class EntrancePage extends Activity {
	private static final String CLASSTAG = EntrancePage.class.getSimpleName();
	private static final int MENU_SETTINGS = Menu.FIRST;

	final int ACTIVITY_SETTINGS = 1;

	private ViewFlipper mFlipper;

	Prefs myprefs = null;
	
	private Button catalogButton;
	private Button ordersButton;
	private Button usersButton;
	
	private RadioButton auto_synch_button;
	private RadioButton local_only_button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
		mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_in));
		mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_out));
		mFlipper.startFlipping();
		
		myprefs = new Prefs(getApplicationContext());

		// catalog
		catalogButton = (Button) findViewById(R.id.catalogbutton);
		catalogButton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				try {					
					Intent intent = new Intent(
							Constants.INTENT_ACTION_CATALOG_LIST);
					startActivity(intent);

				} catch (Exception e) {
					Log.i(Constants.LOGTAG + ": " + EntrancePage.CLASSTAG, "Failed to load the catalog"
							+ e.getMessage() + "]");
				}
			}
		});

		// orders
		ordersButton = (Button) findViewById(R.id.ordersbutton);
		ordersButton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				try {

				} catch (Exception e) {
					Log.i(Constants.LOGTAG + ": " + EntrancePage.CLASSTAG,
							"Failed to load orders" + e.getMessage() + "]");
				}
			}
		});

		// users
		usersButton = (Button) findViewById(R.id.usersbutton);
		usersButton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				try {

				} catch (Exception e) {
					Log.i(Constants.LOGTAG + ": " +EntrancePage.CLASSTAG,
							"Failed to load users" + e.getMessage() + "]");
				}
			}
		});

		auto_synch_button = (RadioButton) findViewById(R.id.synch);
		auto_synch_button.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				Log.v(Constants.LOGTAG + ": " + EntrancePage.CLASSTAG, " AUTO SYNCH !!!");
				myprefs.setMode(Constants.AUTO_SYNCH);
				myprefs.save();
			}
		});
		
		local_only_button = (RadioButton) findViewById(R.id.local);
		local_only_button.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				Log.v(Constants.LOGTAG + ": " + EntrancePage.CLASSTAG, " LOCAL ONLY !!!");
				myprefs.setMode(Constants.LOCAL_ONLY);
				myprefs.save();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v(Constants.LOGTAG + ": " + EntrancePage.CLASSTAG, " onResume");
		RefreshUserInfo();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		super.onCreateOptionsMenu(menu);
//		menu.add(0, EntrancePage.MENU_SETTINGS, 0, R.string.menu_settings)
//				.setIcon(android.R.drawable.ic_menu_manage);
//		return true;
//	}
	
	public boolean onPrepareOptionsMenu(Menu menu) {

		menu.clear();

		if(myprefs.getMode() == Constants.AUTO_SYNCH) {
			Log.v(Constants.LOGTAG + ": " + EntrancePage.CLASSTAG, " AUTO SYNCH !!!");
			
			menu.add(0, EntrancePage.MENU_SETTINGS, 0, R.string.menu_settings)
			.setIcon(android.R.drawable.ic_menu_manage);
			menu.setGroupVisible(0, true);

		} else {
			Log.v(Constants.LOGTAG + ": " + EntrancePage.CLASSTAG, " LOCAL ONLY !!!");
			menu.setGroupVisible(0, false);

		}

		return super.onPrepareOptionsMenu(menu);

	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case MENU_SETTINGS:
			try {
				// Perform action on click
//				startActivityForResult(new Intent(this, ShowSettings.class),
//						EntrancePage.this.ACTIVITY_SETTINGS);
			} catch (Exception e) {
				Log.i(Constants.LOGTAG + ": " + EntrancePage.CLASSTAG,
						"Failed to Launch Settings [" + e.getMessage() + "]");
			}
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//		switch (requestCode) {
//		case ACTIVITY_SETTINGS:
//			RefreshUserInfo();
//			break;
//		}
//
//	}

	private void RefreshUserInfo() {
		try {

//			if (this.myprefs.isValid()) {
//				catalogButton.setText("Books" + " ("
//						+ this.myprefs.getUserName() + ")");
//				ordersButton.setText("Orders" + " ("
//						+ this.myprefs.getUserName() + ")");
//				ordersButton.setEnabled(true);
//				usersButton.setText("Users" + " (" + this.myprefs.getUserName()
//						+ ")");
//				usersButton.setEnabled(true);
//			} else {
//				catalogButton.setText("Catalog");
//				ordersButton
//						.setText("Orders (Unknown User, Press Menu to Login)");
//				ordersButton.setEnabled(false);
//				usersButton
//						.setText("Users (Unknown User, Press Menu to Login)");
//				usersButton.setEnabled(false);
//			}

		} catch (Exception e) {

		}
	}
}
