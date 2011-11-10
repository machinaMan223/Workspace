package edu.vsu.cs4900.data;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import edu.vsu.cs4900.Constants;
import edu.vsu.cs4900.data.UsersEntry;
import edu.vsu.cs4900.data.UsersList;
import edu.vsu.cs4900.data.UsersListHandler;

public class UsersListHandler extends DefaultHandler {
	private static final String CLASSTAG = UsersListHandler.class.getSimpleName();
    Handler phandler = null;
    UsersList _list;
    UsersEntry _users;
    String _lastElementName = "";
    StringBuilder sb = null;
    Context _context;

    public UsersListHandler(Context c, Handler progresshandler) {
        this._context = c;
        if (progresshandler != null) {
            this.phandler = progresshandler;
            Message msg = new Message();
            msg.what = 0;
            msg.obj = ("Processing Users List");
            this.phandler.sendMessage(msg);
        }
    }

    public UsersList getList() {
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

        // initialize our UsersLIst object - this will hold our parsed contents
        this._list = new UsersList(this._context);

        // initialize the UsersEntry object
        this._users = new UsersEntry();

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

            if (localName.equals("user")) {
                // create a new item

                Message msg = new Message();
                msg.what = 0;
                msg.obj = (localName);
                if (this.phandler != null) {
                    this.phandler.sendMessage(msg);
                }

                this._users = new UsersEntry();

            }
        } catch (Exception ee) {
            Log.d(Constants.LOGTAG, " " + UsersListHandler.CLASSTAG + ee.getStackTrace().toString());
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

        if (localName.equals("user")) {
            // add our users to the list!
            this._list.addUsersEntry(this._users);
            Message msg = new Message();
            msg.what = 0;
            msg.obj = ("Storing users # " + this._users.get_users_id());
            if (this.phandler != null) {
                this.phandler.sendMessage(msg);
            }

            return;
        }

        if (localName.equals("id")) {
            this._users.set_users_id(this.sb.toString());
            return;
        }
        if (localName.equals("name")) {
            this._users.set_name(this.sb.toString());
            return;
        }
        if (localName.equals("fullName")) {
            this._users.set_fullName(this.sb.toString());
            return;
        }
        if (localName.equals("address")) {
            this._users.set_address(this.sb.toString());
            return;
        }
        if (localName.equals("classification")) {
            this._users.set_classification(this.sb.toString());
          return;
        }
        if (localName.equals("paid")) {
            this._users.set_paid(this.sb.toString());
          return;
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        String theString = new String(ch, start, length);
       // Log.d(Constants.LOGTAG, " " + UsersListHandler.CLASSTAG + "characters[" + theString + "]");
        this.sb.append(theString);
    }
}

