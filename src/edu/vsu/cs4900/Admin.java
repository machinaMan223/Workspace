package edu.vsu.cs4900;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Admin extends Activity {
	private Button btnEvents;
	private Button btnUsers;
	private Button btnProducts;
	private Button btnOrders;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acm_admin);
		
		this.btnEvents = (Button) findViewById(R.id.event_button);
		this.btnUsers = (Button) findViewById(R.id.users_button);
		this.btnProducts = (Button) findViewById(R.id.products_button);
		this.btnOrders = (Button) findViewById(R.id.orders_button);
		
		this.btnEvents.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("edu.vsu.cs4900.LIST_ADMIN_EVENTS");
				startActivity(intent);
			}
		});
		this.btnUsers.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("edu.vsu.cs4900.LIST_ADMIN_USERS");
				startActivity(intent);
			}
		});
		this.btnProducts.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("edu.vsu.cs4900.LIST_ADMIN_PRODUCTS");
				startActivity(intent);
			}
		});
		this.btnOrders.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("edu.vsu.cs4900.LIST_ADMIN_ORDERS");
				startActivity(intent);
			}
		});
	}

}
