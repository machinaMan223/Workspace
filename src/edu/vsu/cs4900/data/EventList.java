package edu.vsu.cs4900.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.util.Log;

import edu.vsu.cs4900.Constants;
import edu.vsu.cs4900.data.EventEntry;
import edu.vsu.cs4900.data.EventList;
import edu.vsu.cs4900.data.EventListHandler;

public class EventList {
	private static final String CLASSTAG = EventList.class.getSimpleName();
    private Context _context = null;
    private List<EventEntry> _eventlist;

    EventList(Context c) {
        this._context = c;
        this._eventlist = new Vector<EventEntry>(0);
    }

    int addEventEntry(EventEntry eventEntry) {
        this._eventlist.add(eventEntry);
        return this._eventlist.size();
    }

    EventEntry getEventEntry(int location) {
        return this._eventlist.get(location);
    }

    public List<EventEntry> getAllEventEntries() {
        return this._eventlist;
    }

    int getEventEntryCount() {
        return this._eventlist.size();
    }

    void replace(EventEntry newEventEntry) {
        try {
            EventList newlist = new EventList(this._context);
            for (int i = 0; i < getEventEntryCount(); i++) {
            	EventEntry ee = getEventEntry(i);
                if (ee.get_event_id().equals(newEventEntry.get_event_id())) {
                    Log.d(Constants.LOGTAG, " " + EventList.CLASSTAG + "Replacing EventEntry");
                    newlist.addEventEntry(newEventEntry);
                } else {
                    newlist.addEventEntry(ee);
                }
            }
            this._eventlist = newlist._eventlist;
            persist();
        } catch (Exception e) {

        }
    }

    // Write to the XML file
    public void persist() {
        try {
            FileOutputStream fos = this._context.openFileOutput(Constants.EVENTS_XML_FILE_NAME, Context.MODE_PRIVATE);
            fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n".getBytes());
            fos.write("<events>\n".getBytes());            
            for (int i = 0; i < getEventEntryCount(); i++) {
            	EventEntry ee = getEventEntry(i);
                fos.write(ee.toXMLString().getBytes());
            }
            fos.write("</events>\n".getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.d(Constants.LOGTAG, " " + EventList.CLASSTAG + "Failed to write out file?" + e.getMessage());
        }
    }

    // Read from the XML file
    public static EventList parse(Context context) {
        try {
            FileInputStream fis = context.openFileInput(Constants.EVENTS_XML_FILE_NAME);

            if (fis == null) {
                return null;
            }
            
            InputSource is = new InputSource(fis);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();
            EventListHandler eventHandler = new EventListHandler(context, null);
            xmlreader.setContentHandler(eventHandler);
            xmlreader.parse(is);
            fis.close();

            // return our new eventlist
            return eventHandler.getList();
        } catch (Exception e) {
            Log.d(Constants.LOGTAG, " " + EventList.CLASSTAG + "Error parsing event list xml file: " + e.getMessage());
            return null;
        }
    }
}
