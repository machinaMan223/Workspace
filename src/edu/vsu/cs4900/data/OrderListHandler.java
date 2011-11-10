package edu.vsu.cs4900.data;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.vsu.cs4900.Constants;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class OrderListHandler extends DefaultHandler {
	private static final String CLASSTAG = OrderListHandler.class
			.getSimpleName();
	Handler phandler = null;
	OrderList _list;
	OrderEntry _order;
	String _lastElementName = "";
	StringBuilder sb = null;
	Context _context;
	
	public OrderListHandler(Context c, Handler progresshandler) {
		this._context = c;
		if (progresshandler != null) {
			this.phandler = progresshandler;
			Message msg = new Message();
			msg.what = 0;
			msg.obj = ("Processing Order List");
			this.phandler.sendMessage(msg);
		}
	}
	
	public OrderList getList() {
		Message msg = new Message();
		msg.what = 0;
		msg.obj = ("Fetching List");
		if (this.phandler != null) {
			this.phandler.sendMessage(msg);
		}
		return this._list;
	}
	
	@Override
	public void startDocument() throws SAXException {
		Message msg = new Message();
		msg.what = 0;
		msg.obj = ("Starting Document");
		if (this.phandler != null) {
			this.phandler.sendMessage(msg);
		}

		// initialize our OrderList object - this will hold our parsed contents
		this._list = new OrderList(this._context);

		// initialize the OrderEntry object
		this._order = new OrderEntry();

	}
	
	@Override
	public void endDocument() throws SAXException {
		Message msg = new Message();
		msg.what = 0;
		msg.obj = ("End of Document");
		if (this.phandler != null) {
			this.phandler.sendMessage(msg);
		}

	}
	
	@Override
	public void startElement(String namespaceURI, String localName, String qName,
			Attributes atts) throws SAXException {
		try {
			this.sb = new StringBuilder("");

			if (localName.equals("order")) {
				// create a new item

				Message msg = new Message();
				msg.what = 0;
				msg.obj = (localName);
				if (this.phandler != null) {
					this.phandler.sendMessage(msg);
				}

				this._order = new OrderEntry();

			}
		}
		catch (Exception ee) {
			Log.d(Constants.LOGTAG, " " + OrderListHandler.CLASSTAG
					+ ee.getStackTrace().toString());
		}
	}
	
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {

		if (localName.equals("order")) {
			// add our product to the list!
			this._list.addOrderEntry(this._order);
			Message msg = new Message();
			msg.what = 0;
			msg.obj = ("Storing Order # " + this._order.get_order_id());
			if (this.phandler != null) {
				this.phandler.sendMessage(msg);
			}

			return;
		}

		if (localName.equals("id")) {
			this._order.set_order_id(this.sb.toString());
			return;
		}
		if (localName.equals("address")) {
			this._order.set_address(this.sb.toString());
			return;
		}
		if (localName.equals("email")) {
			this._order.set_email(this.sb.toString());
			return;
		}
		if (localName.equals("name")) {
			this._order.set_name(this.sb.toString());
			return;
		}
		if (localName.equals("pay-type")) {
			this._order.set_pay_type(this.sb.toString());
			return;
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
		String theString = new String(ch, start, length);
		// Log.d(Constants.LOGTAG, " " + OrderListHandler.CLASSTAG + "characters["
		// + theString + "]");
		this.sb.append(theString);
	}
}
