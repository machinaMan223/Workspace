package edu.vsu.cs4900.data;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import edu.vsu.cs4900.Constants;
import edu.vsu.cs4900.data.EventEntry;
import edu.vsu.cs4900.data.EventList;
import edu.vsu.cs4900.data.EventListHandler;

public class EventListHandler extends DefaultHandler {
	private static final String CLASSTAG = EventListHandler.class.getSimpleName();
    Handler ehandler = null;
    EventList _list;
    EventEntry _event;
    String _lastElementName = "";
    StringBuilder sb = null;
    Context _context;

    public EventListHandler(Context c, Handler progresshandler) {
        this._context = c;
        if (progresshandler != null) {
            this.ehandler = progresshandler;
            Message msg = new Message();
            msg.what = 0;
            msg.obj = ("Processing Event List");
            this.ehandler.sendMessage(msg);
        }
    }

    public EventList getList() {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = ("Fetching List");
        if (this.ehandler != null) {
            this.ehandler.sendMessage(msg);
        }
        return this._list;
    }

    @Override
    public void startDocument() throws SAXException {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = ("Starting Document");
        if (this.ehandler != null) {
            this.ehandler.sendMessage(msg);
        }

        // initialize our EventLIst object - this will hold our parsed contents
        this._list = new EventList(this._context);

        // initialize the EventEntry object
        this._event = new EventEntry();

    }

    @Override
    public void endDocument() throws SAXException {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = ("End of Document");
        if (this.ehandler != null) {
            this.ehandler.sendMessage(msg);
        }

    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        try {
            this.sb = new StringBuilder("");

            if (localName.equals("event")) {
                // create a new item

                Message msg = new Message();
                msg.what = 0;
                msg.obj = (localName);
                if (this.ehandler != null) {
                    this.ehandler.sendMessage(msg);
                }

                this._event = new EventEntry();

            }
        } catch (Exception ee) {
            Log.d(Constants.LOGTAG, " " + EventListHandler.CLASSTAG + ee.getStackTrace().toString());
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

        if (localName.equals("event")) {
            // add our event to the list!
            this._list.addEventEntry(this._event);
            Message msg = new Message();
            msg.what = 0;
            msg.obj = ("Storing Event # " + this._event.get_event_id());
            if (this.ehandler != null) {
                this.ehandler.sendMessage(msg);
            }

            return;
        }

        if (localName.equals("id")) {
            this._event.set_event_id(this.sb.toString());
            return;
        }
        if (localName.equals("name")) {
            this._event.set_name(this.sb.toString());
            return;
        }
        if (localName.equals("start_at")) {
            this._event.set_start_at(this.sb.toString());
            return;
        }
        if (localName.equals("end_at")) {
            this._event.set_end_at(this.sb.toString());
            return;
        }
        if (localName.equals("location")) {
            this._event.set_location(this.sb.toString());
            return;
        }
        if (localName.equals("address")) {
            this._event.set_address(this.sb.toString());
            return;
        }
        if (localName.equals("description")) {
            this._event.set_description(this.sb.toString());
            return;
        }
        if (localName.equals("price")) {
            this._event.set_price(this.sb.toString());
            return;
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        String theString = new String(ch, start, length);
       // Log.d(Constants.LOGTAG, " " + EventListHandler.CLASSTAG + "characters[" + theString + "]");
        this.sb.append(theString);
    }

}
