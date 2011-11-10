package edu.vsu.cs4900;

import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.vsu.cs4900.EventAdapter;
import edu.vsu.cs4900.Constants;
import edu.vsu.cs4900.data.EventEntry;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter {
	
	private static final String CLASSTAG = EventAdapter.class.getSimpleName();
    private final Context context;
    private final List<EventEntry> event;

    public EventAdapter(Context context, List<EventEntry> event, int order) {
        this.context = context;
        this.event = event;
        
        switch (order){
        case 0: // by name
        	Collections.sort(event, new Comparator(){
       		 
                public int compare(Object o1, Object o2) {
                	EventEntry ce1 = (EventEntry) o1;
                	EventEntry ce2 = (EventEntry) o2;
                   return ce1.get_name().compareToIgnoreCase(ce2.get_name());
                }
     
            });
        	break;
        case 1: // by price
        	Collections.sort(event, new Comparator(){
       		 
                public int compare(Object o1, Object o2) {
                	
                	EventEntry ce1 = (EventEntry) o1;
                	EventEntry ce2 = (EventEntry) o2;
                	
                	Float price1 = Float.parseFloat(ce1.get_price());
                	Float price2 = Float.parseFloat(ce2.get_price());
                    return price1.compareTo(price2);
                }
     
            });
        	break;
        case 2: // by start_at
        	Collections.sort(event, new Comparator(){
          		 
                public int compare(Object o1, Object o2) {
                	
                	EventEntry ce1 = (EventEntry) o1;
                	EventEntry ce2 = (EventEntry) o2;
                	return ce1.get_start_at().compareToIgnoreCase(ce2.get_start_at());
                }
     
            });
        	break;
        }

        
        Log.v(Constants.LOGTAG, " " + EventAdapter.CLASSTAG + " event size - " + this.event.size());
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private final class EventListView extends LinearLayout {

        private TextView name;
        private TextView address;
        private TextView start_at;

        public EventListView(Context context, String name, String address, String start_at) {

            super(context);
            setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 3, 5, 0);

            this.name = new TextView(context);
            this.name.setText(name);
            this.name.setTextSize(16f);
            this.name.setTextColor(Color.WHITE);
            this.addView(this.name, params);

            this.address = new TextView(context);
            this.address.setText("$ " + address);
            this.address.setTextSize(8f);
            this.address.setTextColor(Color.GRAY);
            this.addView(this.address, params);
        }
    }

}
