package com.depot.cs4900.data;

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

import com.depot.cs4900.Constants;
import com.depot.cs4900.EntrancePage;

public class UserList {
	private static final String CLASSTAG = UserList.class.getSimpleName();
    private Context _context = null;
    private List<UserEntry> _userlist;

    UserList(Context c) {
        this._context = c;
        this._userlist = new Vector<UserEntry>(0);
    }

    int addUserEntry(UserEntry userEntry) {
        this._userlist.add(userEntry);
        return this._userlist.size();
    }

    UserEntry getUserEntry(int location) {
        return this._userlist.get(location);
    }

    public List<UserEntry> getAllUserEntries() {
        return this._userlist;
    }

    int getUserEntryCount() {
        return this._userlist.size();
    }

    void replace(UserEntry newUserEntry) {
        try {
            UserList newlist = new UserList(this._context);
            for (int i = 0; i < getUserEntryCount(); i++) {
            	UserEntry ce = getUserEntry(i);
                if (ce.get_user_id().equals(newUserEntry.get_user_id())) {
                    Log.d(Constants.LOGTAG, " " + UserList.CLASSTAG + "Replacing UserEntry");
                    newlist.addUserEntry(newUserEntry);
                } else {
                    newlist.addUserEntry(ce);
                }
            }
            this._userlist = newlist._userlist;
            persist();
        } catch (Exception e) {

        }
    }

    // Write to the XML file
    public void persist() {
        try {
            FileOutputStream fos = this._context.openFileOutput(Constants.USER_XML_FILE_NAME, Context.MODE_PRIVATE);
            fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n".getBytes());
            fos.write("<users>\n".getBytes());            
            for (int i = 0; i < getUserEntryCount(); i++) {
            	UserEntry ce = getUserEntry(i);
                fos.write(ce.toXMLString().getBytes());
            }
            fos.write("</users>\n".getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.d(Constants.LOGTAG, " " + UserList.CLASSTAG + "Failed to write out file?" + e.getMessage());
        }
    }

    // Read from the XML file
    public static UserList parse(Context context) {
        try {
            FileInputStream fis = context.openFileInput(Constants.USER_XML_FILE_NAME);

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
            UserListHandler clHandler = new UserListHandler(context, null /*
                                                                         * no progress updates when
                                                                         * reading file
                                                                         */);

            // assign our handler
            xmlreader.setContentHandler(clHandler);

            // perform the synchronous parse
            xmlreader.parse(is);

            // clean up
            fis.close();

            // return our new userlist
            return clHandler.getList();
        } catch (Exception e) {
            Log.d(Constants.LOGTAG, " " + UserList.CLASSTAG + "Error parsing user list xml file: " + e.getMessage());
            return null;
        }
    }

}
