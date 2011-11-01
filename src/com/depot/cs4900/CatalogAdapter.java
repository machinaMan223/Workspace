package com.depot.cs4900;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import java.util.Collections;
import java.util.Comparator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.depot.cs4900.data.*;
/**
 * Custom adapter for "Review" model objects.
 * 
 * @author charliecollins
 */
public class CatalogAdapter extends BaseAdapter {

    private static final String CLASSTAG = CatalogAdapter.class.getSimpleName();
    private final Context context;
    private final List<CatalogEntry> catalog;

    public CatalogAdapter(Context context, List<CatalogEntry> catalog, int order) {
        this.context = context;
        this.catalog = catalog;
        
        switch (order){
        case 0: // by title
        	Collections.sort(catalog, new Comparator(){
       		 
                public int compare(Object o1, Object o2) {
                	CatalogEntry ce1 = (CatalogEntry) o1;
                	CatalogEntry ce2 = (CatalogEntry) o2;
                   return ce1.get_title().compareToIgnoreCase(ce2.get_title());
                }
     
            });
        	break;
        case 1: // by price
        	Collections.sort(catalog, new Comparator(){
       		 
                public int compare(Object o1, Object o2) {
                	
                	CatalogEntry ce1 = (CatalogEntry) o1;
                	CatalogEntry ce2 = (CatalogEntry) o2;
                	
                	Float price1 = Float.parseFloat(ce1.get_price());
                	Float price2 = Float.parseFloat(ce2.get_price());
                    return price1.compareTo(price2);
                }
     
            });
        	break;
        case 2: // by popularity
        	Collections.sort(catalog, new Comparator(){
          		 
                public int compare(Object o1, Object o2) {
                	
                	CatalogEntry ce1 = (CatalogEntry) o1;
                	CatalogEntry ce2 = (CatalogEntry) o2;

                	Float pop1 = Float.parseFloat(ce1.get_popularity());
                	Float pop2 = Float.parseFloat(ce2.get_popularity());
                    return -pop1.compareTo(pop2) ;
                }
     
            });
        	break;
        }

        
        Log.v(Constants.LOGTAG, " " + CatalogAdapter.CLASSTAG + " catalog size - " + this.catalog.size());
    }

    public int getCount() {
        return this.catalog.size();
    }

    public Object getItem(int position) {
        return this.catalog.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    	CatalogEntry re = this.catalog.get(position);
        return new CatalogListView(this.context, re.get_title(), re.get_price(), re.get_popularity());
    }

    /**
     * CatalogListView that adapter returns as it's view item per row.
     * 
     * @author zhiguang xu
     */
    private final class CatalogListView extends LinearLayout {

        private TextView title;
        private TextView price;
        private TextView popularity;

        public CatalogListView(Context context, String title, String price, String popularity) {

            super(context);
            setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 3, 5, 0);

            this.title = new TextView(context);
            this.title.setText(title);
            this.title.setTextSize(16f);
            this.title.setTextColor(Color.WHITE);
            this.addView(this.title, params);

            this.price = new TextView(context);
            this.price.setText("$ " + price);
            this.price.setTextSize(8f);
            this.price.setTextColor(Color.GRAY);
            this.addView(this.price, params);
        }
    }
}
