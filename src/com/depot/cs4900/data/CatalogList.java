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

public class CatalogList {

    private Context _context = null;
    private List<CatalogEntry> _cataloglist;

    CatalogList(Context c) {
        this._context = c;
        this._cataloglist = new Vector<CatalogEntry>(0);
    }

    int addCatalogEntry(CatalogEntry catalogEntry) {
        this._cataloglist.add(catalogEntry);
        return this._cataloglist.size();
    }

    CatalogEntry getCatalogEntry(int location) {
        return this._cataloglist.get(location);
    }

    public List<CatalogEntry> getAllCatalogEntries() {
        return this._cataloglist;
    }

    int getCatalogEntryCount() {
        return this._cataloglist.size();
    }

    void replace(CatalogEntry newCatalogEntry) {
        try {
            CatalogList newlist = new CatalogList(this._context);
            for (int i = 0; i < getCatalogEntryCount(); i++) {
            	CatalogEntry ce = getCatalogEntry(i);
                if (ce.get_product_id().equals(newCatalogEntry.get_product_id())) {
                    Log.d("Depot", "Replacing CatalogEntry");
                    newlist.addCatalogEntry(newCatalogEntry);
                } else {
                    newlist.addCatalogEntry(ce);
                }
            }
            this._cataloglist = newlist._cataloglist;
            persist();
        } catch (Exception e) {

        }
    }

    public void persist() {
        try {
            FileOutputStream fos = this._context.openFileOutput("catalog.xml", Context.MODE_PRIVATE);
            fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n".getBytes());
            fos.write("<products>\n".getBytes());            
            for (int i = 0; i < getCatalogEntryCount(); i++) {
            	CatalogEntry ce = getCatalogEntry(i);
                fos.write(ce.toXMLString().getBytes());
            }
            fos.write("</products>\n".getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.d("Depot", "Failed to write out file?" + e.getMessage());
        }
    }

    public static CatalogList parse(Context context) {
        try {
            FileInputStream fis = context.openFileInput("catalog.xml");

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
            CatalogListHandler clHandler = new CatalogListHandler(context, null /*
                                                                         * no progress updates when
                                                                         * reading file
                                                                         */);

            // assign our handler
            xmlreader.setContentHandler(clHandler);

            // perform the synchronous parse
            xmlreader.parse(is);

            // clean up
            fis.close();

            // return our new cataloglist
            return clHandler.getList();
        } catch (Exception e) {
            Log.d("Depot", "Error parsing catalog list xml file: " + e.getMessage());
            return null;
        }
    }

}
