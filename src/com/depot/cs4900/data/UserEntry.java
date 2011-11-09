package com.depot.cs4900.data;

import android.os.Bundle;
import android.util.Log;

public class UserEntry {

    private String _user_id = "";
    private String _name = "";
    private String _password = "";
    private String _address = "";

    UserEntry() {
        // constructor

    }

    public String get_user_id() {
        return this._user_id;
    }

    public void set_user_id(String _user_id) {
        this._user_id = _user_id;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_password() {
        return this._password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
    
    public String get_address() {
        return this._address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }
    
    @Override
    public String toString() {
        return this._name;
    }

    public String toXMLString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("<user>");
        sb.append("<id>" + this._user_id + "</id>");
        sb.append("<name>" + this._name + "</name>");
        sb.append("<hashed-password>" + this._password + "</hashed-password>");
        sb.append("<address>" + this._address + "</address>");
        sb.append("</user>");
        return sb.toString() + "\n";
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString("user_id", this._user_id);
        b.putString("name", this._name);
        b.putString("hashed-password", this._password);
        b.putString("address", this._address);

        return b;
    }

    public static UserEntry fromBundle(Bundle b) {
        UserEntry ce = new UserEntry();
        ce.set_user_id(b.getString("user_id"));
        ce.set_name(b.getString("name"));
        ce.set_password(b.getString("hashed-password"));
        ce.set_address(b.getString("address"));

        return ce;
    }
}
