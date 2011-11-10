package edu.vsu.cs4900.data;

import android.os.Bundle;
import android.util.Log;

public class UsersEntry {

    private String _users_id = "";
    private String _name = "";
    private String _fullName = "";
    private String _address = "";
    private String _classification = "";
    private String _emailAddress = "";
    private String _paid = "";

    UsersEntry() {
        // constructor

    }

    public String get_users_id() {
        return this._users_id;
    }

    public void set_users_id(String _users_id) {
        this._users_id = _users_id;
    }
    
    public String get_name() {
        return this._name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_fullName() {
        return this._fullName;
    }

    public void set_fullName(String _fullName) {
        this._fullName = _fullName;
    }

    public String get_address() {
        return this._address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_classification() {
        return this._classification;
    }

    public void set_classification(String _classification) {
      this._classification = _classification;
    }

    public String get_emailAddress() {
        return this._emailAddress;
    }

    public void set_emailAddress(String _emailAddress) {
      this._emailAddress = _emailAddress;
    }
    
    public String get_paid() {
        return this._paid;
    }

    public void set_paid(String _paid) {
      this._paid = _paid;
    }
    
    public String toXMLString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("<users>");
        sb.append("<id>" + this._users_id + "</id>");
        sb.append("<name>" + this._name + "</name>");
        sb.append("<fullName>" + this._fullName + "</fullName>");
        sb.append("<address>" + this._address + "</address>");
        sb.append("<classification>" + this._classification + "</classification>");
        sb.append("<emailAddress>" + this._emailAddress + "</emailAddress>");
        sb.append("<paid>" + this._paid + "</paid>");
        sb.append("</users>");
        return sb.toString() + "\n";
    }
    
    public String toString() {
    	 return this._fullName + " " + this._classification;
    	    
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString("users_id", this._users_id);
        b.putString("name", this._name);
        b.putString("fullName", this._fullName);
        b.putString("address", this._address);
        b.putString("classification", this._classification);
        b.putString("emailAddress", this._emailAddress);
        b.putString("paid", this._paid);
        
        return b;
    }

    public static UsersEntry fromBundle(Bundle b) {
        UsersEntry ce = new UsersEntry();
        ce.set_users_id(b.getString("users_id"));
        ce.set_name(b.getString("name"));
        ce.set_fullName(b.getString("fullName"));
        ce.set_address(b.getString("address"));
        ce.set_classification(b.getString("classification"));
        ce.set_emailAddress(b.getString("emailAddress"));
        ce.set_paid(b.getString("paid"));

        return ce;
    }
}


