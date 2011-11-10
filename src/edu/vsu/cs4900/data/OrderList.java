package edu.vsu.cs4900.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import edu.vsu.cs4900.Constants;

import android.content.Context;
import android.util.Log;

public class OrderList {
	private static final String CLASSTAG = OrderList.class.getSimpleName();
	private Context _context = null;
	private List<OrderEntry> _orderlist;

	OrderList(Context c) {
		this._context = c;
		this._orderlist = new Vector<OrderEntry>(0);
	}
	
	int addOrderEntry(OrderEntry orderEntry) {
		this._orderlist.add(orderEntry);
		return this._orderlist.size();
	}

	OrderEntry getOrderEntry(int location) {
		return this._orderlist.get(location);
	}

	public List<OrderEntry> getAllOrderEntries() {
		return this._orderlist;
	}

	int getOrderEntryCount() {
		return this._orderlist.size();
	}
	
	void replace(OrderEntry newOrderEntry) {
		try {
			OrderList newlist = new OrderList(this._context);
			for (int i = 0; i < getOrderEntryCount(); i++) {
				OrderEntry oe = getOrderEntry(i);
				if (oe.get_order_id().equals(newOrderEntry.get_order_id())) {
					Log.d(Constants.LOGTAG, " " + OrderList.CLASSTAG
							+ "Replacing OrderEntry");
					newlist.addOrderEntry(newOrderEntry);
				}
				else {
					newlist.addOrderEntry(oe);
				}
			}
			this._orderlist = newlist._orderlist;
			persist();
		}
		catch (Exception e) {

		}
	}
	
//Write to the XML file
	public void persist() {
		try {
			FileOutputStream fos = this._context.openFileOutput(
					Constants.CATALOG_XML_FILE_NAME, Context.MODE_PRIVATE);
			fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n".getBytes());
			fos.write("<orders>\n".getBytes());
			for (int i = 0; i < getOrderEntryCount(); i++) {
				OrderEntry oe = getOrderEntry(i);
				fos.write(oe.toXMLString().getBytes());
			}
			fos.write("</orders>\n".getBytes());
			fos.flush();
			fos.close();
		}
		catch (Exception e) {
			Log.d(Constants.LOGTAG, " " + OrderList.CLASSTAG
					+ "Failed to write out file?" + e.getMessage());
		}
	}
	
	//Read from the XML file
	public static OrderList parse(Context context) {
		try {
			FileInputStream fis = context
					.openFileInput(Constants.ORDERS_XML_FILE_NAME);

			if (fis == null) {
				return null;
			}
			// we need an input source for the sax parser
			InputSource is = new InputSource(fis);

			// create the factory
			SAXParserFactory factory = SAXParserFactory.newInstance();

			// create a parser
			SAXParser parser = factory.newSAXParser();

			// create the reader (scanner)
			XMLReader xmlreader = parser.getXMLReader();

			// instantiate our handler
			OrderListHandler olHandler = new OrderListHandler(context, null /*
																																					 * no
																																					 * progress
																																					 * updates
																																					 * when
																																					 * reading
																																					 * file
																																					 */);

			// assign our handler
			xmlreader.setContentHandler(olHandler);

			// perform the synchronous parse
			xmlreader.parse(is);

			// clean up
			fis.close();

			// return our new cataloglist
			return olHandler.getList();
		}
		catch (Exception e) {
			Log.d(Constants.LOGTAG, " " + OrderList.CLASSTAG
					+ "Error parsing catalog list xml file: " + e.getMessage());
			return null;
		}
	}
}
