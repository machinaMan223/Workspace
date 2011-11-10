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
import edu.vsu.cs4900.ACMHome;

public class UsersList {
	private static final String CLASSTAG = UsersList.class.getSimpleName();
    private Context _context = null;
    private List<UsersEntry> _Userslist;

    UsersList(Context c) {
        this._context = c;
        this._Userslist = new Vector<UsersEntry>(0);
    }

    int addUsersEntry(UsersEntry UsersEntry) {
        this._Userslist.add(UsersEntry);
        return this._Userslist.size();
    }

    UsersEntry getUsersEntry(int location) {
        return this._Userslist.get(location);
    }

    public List<UsersEntry> getAllUsersEntries() {
        return this._Userslist;
    }

    int getUsersEntryCount() {
        return this._Userslist.size();
    }

    void replace(UsersEntry newUsersEntry) {
        try {
            UsersList newlist = new UsersList(this._context);
            for (int i = 0; i < getUsersEntryCount(); i++) {
            	UsersEntry ce = getUsersEntry(i);
                if (ce.get_users_id().equals(newUsersEntry.get_users_id())) {
                    Log.d(Constants.LOGTAG, " " + UsersList.CLASSTAG + "Replacing UsersEntry");
                    newlist.addUsersEntry(newUsersEntry);
                } else {
                    newlist.addUsersEntry(ce);
                }
            }
            this._Userslist = newlist._Userslist;
            persist();
        } catch (Exception e) {

        }
    }

    // Write to the XML file
    public void persist() {
        try {
            FileOutputStream fos = this._context.openFileOutput(Constants.USERS_XML_FILE_NAME, Context.MODE_PRIVATE);
            fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n".getBytes());
            fos.write("<Userss>\n".getBytes());            
            for (int i = 0; i < getUsersEntryCount(); i++) {
            	UsersEntry ce = getUsersEntry(i);
                fos.write(ce.toXMLString().getBytes());
            }
            fos.write("</Userss>\n".getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.d(Constants.LOGTAG, " " + UsersList.CLASSTAG + "Failed to write out file?" + e.getMessage());
        }
    }

    // Read from the XML file
    public static UsersList parse(Context context) {
        try {
            FileInputStream fis = context.openFileInput(Constants.USERS_XML_FILE_NAME);

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
            UsersListHandler clHandler = new UsersListHandler(context, null /*
                                                                         * no progress updates when
                                                                         * reading file
                                                                         */);

            // assign our handler
            xmlreader.setContentHandler(clHandler);

            // perform the synchronous parse
            xmlreader.parse(is);

            // clean up
            fis.close();

            // return our new Userslist
            return clHandler.getList();
        } catch (Exception e) {
            Log.d(Constants.LOGTAG, " " + UsersList.CLASSTAG + "Error parsing Users list xml file: " + e.getMessage());
            return null;
        }
    }

}
