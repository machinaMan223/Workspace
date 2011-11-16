package edu.vsu.cs4900;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.ViewFlipper;
import android.widget.EditText;

import edu.vsu.cs4900.data.*;

public class NewProduct extends Activity {
	private static final String CLASSTAG = NewProduct.class.getSimpleName();

	Prefs myprefs = null;

	private EditText title_text;
	private EditText desciption_text;
	private EditText price_text;

	private Button create_button;
	private Button cancel_button;

	private ProgressDialog progressDialog;

	private CatalogEntry product;
	private CatalogList catalog;

	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(final Message msg) {
			Log.v(Constants.LOGTAG,
							" "
							+ NewProduct.CLASSTAG
							+ " create worker thread done.");
			progressDialog.dismiss();

			finish();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_product);

		myprefs = new Prefs(getApplicationContext());

		product = new CatalogEntry();

		title_text = (EditText) findViewById(R.id.product_title);
		desciption_text = (EditText) findViewById(R.id.product_description);
		price_text = (EditText) findViewById(R.id.product_price);

		// update
		create_button = (Button) findViewById(R.id.product_new_button);
		create_button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				createProdut();
			}
		});
		// cancel
		cancel_button = (Button) findViewById(R.id.product_cancel_button);
		cancel_button.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				finish();

			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v(Constants.LOGTAG + ": " + NewProduct.CLASSTAG, " onResume");
	}

	private void createProdut() {

		Log.v(Constants.LOGTAG, " " + NewProduct.CLASSTAG + " updateProduct");

		// Get ready to send the HTTP PUT request to update the Product data on
		// the server
		// ...

		this.progressDialog = ProgressDialog.show(this, " Working...",
				" Creating Product", true, false);

		product.set_title(title_text.getText().toString());
		product.set_description(desciption_text.getText().toString());
		product.set_price(price_text.getText().toString());
		catalog = CatalogList.parse(NewProduct.this);
		catalog.create(product);

		// update product on the server in a separate thread for
		// ProgressDialog/Handler
		// when complete send "empty" message to handler
		new Thread() {
			@Override
			public void run() {
				// networking stuff ...
				handler.sendEmptyMessage(0);
			}
		}.start();
	}
	
	
}
