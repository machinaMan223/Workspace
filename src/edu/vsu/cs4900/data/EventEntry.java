package edu.vsu.cs4900.data;

import android.os.Bundle;

public class EventEntry {
	private String _event_id = "";
	private String _name = "";
    private String _start_at = "";
    private String _end_at = "";
    private String _location = "";
    private String _address = "";
    private String _description = "";
    private String _price = "";
    
    EventEntry() {
        // constructor

    }
    
    public String get_event_id() {
        return this._event_id;
    }

    public void set_event_id(String _event_id) {
        this._event_id = _event_id;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
    
    public String get_start_at() {
        return this._start_at;
    }

    public void set_start_at(String _start_at) {
        this._start_at = _start_at;
    }
    
    public String get_end_at() {
        return this._end_at;
    }

    public void set_end_at(String _end_at) {
        this._end_at = _end_at;
    }
    
    public String get_location() {
        return this._location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }
    
    public String get_address() {
        return this._address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }
    
    public String get_description() {
        return this._description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }
    
    public String get_price() {
        return this._price;
    }

    public void set_price(String _price) {
        this._price = _price;
    }
    
    @Override
    public String toString() {
        return this._name + ",\t$" + this._start_at;
    }
    
    public String toXMLString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("<event>");
        sb.append("<id>" + this._event_id + "</id>");
        sb.append("<name>" + this._name + "</name>");
        sb.append("<start_at>" + this._start_at + "</start_at>");
        sb.append("<end_at>" + this._end_at + "</end_at>");
        sb.append("<location>" + this._location + "</location>");
        sb.append("<address>" + this._address + "</address>");
        sb.append("<description>" + this._description + "</description>");
        sb.append("<price type=\"decimal\">" + this._price + "</price>");
        sb.append("</event>");
        return sb.toString() + "\n";
    }
    
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString("event_id", this._event_id);
        b.putString("name", this._name);
        b.putString("start_at", this._start_at);
        b.putString("end_at", this._end_at);
        b.putString("location", this._location);
        b.putString("address", this._address);
        b.putString("description", this._description);
        b.putString("price", this._price);

        return b;
    }
    
    public static EventEntry fromBundle(Bundle b) {
        EventEntry ee = new EventEntry();
        ee.set_event_id(b.getString("event_id"));
        ee.set_name(b.getString("name"));
        ee.set_start_at(b.getString("start_at"));
        ee.set_end_at(b.getString("end_at"));
        ee.set_location(b.getString("location"));
        ee.set_address(b.getString("address"));
        ee.set_description(b.getString("description"));
        ee.set_price(b.getString("price"));

        return ee;
    }

}
