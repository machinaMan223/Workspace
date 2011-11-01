package com.depot.cs4900;

import java.io.FileOutputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.ResponseHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TabHost;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.depot.cs4900.data.CatalogListHandler;
// import com.depot.cs4900.network.HTTPRequestHelper;


public class Catalog extends TabActivity{
	final String tag = "Depot on Rails : Catalog";
    private static final String CLASSTAG = Catalog.class.getSimpleName();
    Prefs myprefs = null;

//    ProgressDialog myprogress;
//    Handler progresshandler;
    
    // use a handler to update the UI (send the handler messages from other threads)
//    private final Handler progresshandler = new Handler() {

//        @Override
//        public void handleMessage(final Message msg) {
//        	myprogress.dismiss();
//        	if (msg.what == 2) 
//        		return;
//        	
//            String bundleResult = msg.getData().getString("RESPONSE");
//
//            try {
//            FileOutputStream fos = getApplication().getApplicationContext().openFileOutput("catalog.xml", Context.MODE_PRIVATE);
//            fos.write(bundleResult.getBytes());
//            fos.flush();
//            fos.close();
//            }catch(Exception e){
//              Log.d("Depot", "Exception: " + e.getMessage());
//              Message m = new Message();
//              m.what = 2; // error occured
//              m.obj = ("Caught an error retrieving catalog data: " + e.getMessage());
//              Catalog.this.progresshandler.sendMessage(m);
//            }
//            
//            final TabHost tabHost = getTabHost();
//
//            tabHost.addTab(tabHost.newTabSpec("tab1")
//                    .setIndicator("By Title")
//                    .setContent(new Intent(Catalog.this, CatalogByTitle.class)));
//
//            tabHost.addTab(tabHost.newTabSpec("tab2")
//                    .setIndicator("By Price")
//                    .setContent(new Intent(Catalog.this, CatalogByPrice.class)));
//
//            tabHost.addTab(tabHost.newTabSpec("tab3")
//                    .setIndicator("By Popularity")
//                    .setContent(new Intent(Catalog.this, CatalogByPopularity.class)));
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.myprefs = new Prefs(getApplicationContext());
        
//        this.myprogress = ProgressDialog.show(this, "Refreshing Catalog", "Please Wait", true, false);
//        
//        final ResponseHandler<String> responseHandler = HTTPRequestHelper.getResponseHandlerInstance(this.progresshandler);
//        
//        // do the HTTP dance in a separate thread (the responseHandler will fire when complete)
//        new Thread() {
//
//            @Override
//            public void run() {
//                HTTPRequestHelper helper = new HTTPRequestHelper(responseHandler);
//                helper.performGet(Catalog.this.myprefs.getServer() + "/products.xml", null, null, null);
//            }
//        }.start();
        
      final TabHost tabHost = getTabHost();

      tabHost.addTab(tabHost.newTabSpec("tab1")
              .setIndicator("By Title")
              .setContent(new Intent(Catalog.this, CatalogByTitle.class)));

      tabHost.addTab(tabHost.newTabSpec("tab2")
              .setIndicator("By Price")
              .setContent(new Intent(Catalog.this, CatalogByPrice.class)));

      tabHost.addTab(tabHost.newTabSpec("tab3")
              .setIndicator("By Popularity")
              .setContent(new Intent(Catalog.this, CatalogByPopularity.class)));
    }
}
