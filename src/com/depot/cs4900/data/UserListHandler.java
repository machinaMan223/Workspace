package com.depot.cs4900.data;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.depot.cs4900.Constants;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class UserListHandler extends DefaultHandler {
	private static final String CLASSTAG = UserListHandler.class.getSimpleName();
    Handler uhandler = null;
    UserList _list;
    UserEntry _user;
    String _lastElementName = "";
    StringBuilder sb = null;
    Context _context;

    public UserListHandler(Context c, Handler progresshandler) {
        this._context = c;
        if (progresshandler != null) {
            this.uhandler = progresshandler;
            Message msg = new Message();
            msg.what = 0;
            msg.obj = ("Processing User List");
            this.uhandler.sendMessage(msg);
        }
    }

    public UserList getList() {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = ("Fetching List");
        if (this.uhandler != null) {
            this.uhandler.sendMessage(msg);
        }
        return this._list;
    }

    @Override
    public void startDocument() throws SAXException {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = ("Starting Document");
        if (this.uhandler != null) {
            this.uhandler.sendMessage(msg);
        }

        // initialize our UserLIst object - this will hold our parsed contents
        this._list = new UserList(this._context);

        // initialize the UserEntry object
        this._user = new UserEntry();

    }

    @Override
    public void endDocument() throws SAXException {
        Message msg = new Message();
        msg.what = 0;
        msg.obj = ("End of Document");
        if (this.uhandler != null) {
            this.uhandler.sendMessage(msg);
        }

    }

    @Override
    public void startElement(String userspaceURI, String localName, String qName, Attributes atts) throws SAXException {
        try {
            this.sb = new StringBuilder("");

            if (localName.equals("user")) {
                // create a new item

                Message msg = new Message();
                msg.what = 0;
                msg.obj = (localName);
                if (this.uhandler != null) {
                    this.uhandler.sendMessage(msg);
                }

                this._user = new UserEntry();

            }
        } catch (Exception ee) {
            Log.d(Constants.LOGTAG, " " + UserListHandler.CLASSTAG + ee.getStackTrace().toString());
        }
    }

    @Override
    public void endElement(String userspaceURI, String localName, String qName) throws SAXException {

        if (localName.equals("user")) {
            // add our user to the list!
            this._list.addUserEntry(this._user);
            Message msg = new Message();
            msg.what = 0;
            msg.obj = ("Storing User # " + this._user.get_user_id());
            if (this.uhandler != null) {
                this.uhandler.sendMessage(msg);
            }

            return;
        }

        if (localName.equals("id")) {
            this._user.set_user_id(this.sb.toString());
            return;
        }
        if (localName.equals("name")) {
            this._user.set_name(this.sb.toString());
            return;
        }
        if (localName.equals("hashed-password")) {
            this._user.set_password(this.sb.toString());
            return;
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        String theString = new String(ch, start, length);
       // Log.d(Constants.LOGTAG, " " + UserListHandler.CLASSTAG + "characters[" + theString + "]");
        this.sb.append(theString);
    }
}
