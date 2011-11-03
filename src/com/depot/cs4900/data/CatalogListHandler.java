package com.depot.cs4900.data;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.depot.cs4900.CatalogByTitle;
import com.depot.cs4900.Constants;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class CatalogListHandler extends DefaultHandler {
	private static final String CLASSTAG = CatalogListHandler.class.getSimpleName();
    Handler phandler = null;
    CatalogList _list;
    CatalogEntry _product;
    String _lastElementName = "";
    StringBuilder sb = null;
    Context _context;

    public CatalogListHandler(Context c, Handler progresshandler) {
        this._context = c;
        if (progresshandler != null) {
            this.phandler = progresshandler;
            Message msg = new Message();
            msg.what = 0;
            msg.obj = ("Processing Catalog List");
            this.phandler.sendMessage(msg);
        }
    }

    public CatalogList getList() {
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

        // initialize our CatalogLIst object - this will hold our parsed contents
        this._list = new CatalogList(this._context);

        // initialize the CatalogEntry object
        this._product = new CatalogEntry();

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
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        try {
            this.sb = new StringBuilder("");

            if (localName.equals("product")) {
                // create a new item

                Message msg = new Message();
                msg.what = 0;
                msg.obj = (localName);
                if (this.phandler != null) {
                    this.phandler.sendMessage(msg);
                }

                this._product = new CatalogEntry();

            }
        } catch (Exception ee) {
            Log.d(Constants.LOGTAG, " " + CatalogListHandler.CLASSTAG + ee.getStackTrace().toString());
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

        if (localName.equals("product")) {
            // add our product to the list!
            this._list.addCatalogEntry(this._product);
            Message msg = new Message();
            msg.what = 0;
            msg.obj = ("Storing Product # " + this._product.get_product_id());
            if (this.phandler != null) {
                this.phandler.sendMessage(msg);
            }

            return;
        }

        if (localName.equals("id")) {
            this._product.set_product_id(this.sb.toString());
            return;
        }
        if (localName.equals("title")) {
            this._product.set_title(this.sb.toString());
            return;
        }
        if (localName.equals("description")) {
            this._product.set_description(this.sb.toString());
            return;
        }
        if (localName.equals("price")) {
            this._product.set_price(this.sb.toString());
            return;
        }
        if (localName.equals("popularity")) {
            this._product.set_popularity(this.sb.toString());
            return;
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        String theString = new String(ch, start, length);
       // Log.d(Constants.LOGTAG, " " + CatalogListHandler.CLASSTAG + "characters[" + theString + "]");
        this.sb.append(theString);
    }
}
