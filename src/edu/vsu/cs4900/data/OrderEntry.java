package edu.vsu.cs4900.data;

import android.os.Bundle;

public class OrderEntry {
	private String _order_id = "";
	private String _address = "";
	private String _email = "";
	private String _name = "";
	private String _pay_type = "";
	
	OrderEntry() {
		// Empty
	}

	public String get_order_id() {
		return _order_id;
	}

	public void set_order_id(String _order_id) {
		this._order_id = _order_id;
	}

	public String get_address() {
		return _address;
	}

	public void set_address(String _address) {
		this._address = _address;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_pay_type() {
		return _pay_type;
	}

	public void set_pay_type(String _pay_type) {
		this._pay_type = _pay_type;
	}
	
	public String toXMLString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("<order>");
		sb.append("<id type=\"integer\">" + this._order_id + "</id>");
		sb.append("<address>" + this._address + "</address>");
		sb.append("<email>" + this._email + "</email>");
		sb.append("<name>" + this._name + "</name>");
		sb.append("<pay-type>" + this._pay_type + "</pay-type>");
		sb.append("</order>");
		return sb.toString() + "\n";
	}
	
	public Bundle toBundle() {
		Bundle b = new Bundle();
		b.putString("order_id", this._order_id);
		b.putString("address", this._address);
		b.putString("email", this._email);
		b.putString("name", this._name);
		b.putString("pay-type", this._pay_type);

		return b;
	}
	
	public static OrderEntry fromBundle(Bundle b) {
		OrderEntry oe = new OrderEntry();
		oe.set_order_id(b.getString("order_id"));
		oe.set_address(b.getString("address"));
		oe.set_email(b.getString("email"));
		oe.set_name(b.getString("name"));
		oe.set_pay_type(b.getString("pay-type"));

		return oe;
	}
}
