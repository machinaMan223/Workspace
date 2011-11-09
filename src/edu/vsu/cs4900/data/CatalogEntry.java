package edu.vsu.cs4900.data;

import android.os.Bundle;

public class CatalogEntry {
	private String _product_id = "";
	private String _title = "";
	private String _description = "";
	private String _price = "";

	CatalogEntry() {
		// Empty
	}

	public String get_product_id() {
		return this._product_id;
	}

	public void set_product_id(String _product_id) {
		this._product_id = _product_id;
	}

	public String get_title() {
		return this._title;
	}

	public void set_title(String _title) {
		this._title = _title;
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
		return this._title + ",\t$" + this._price;
	}

	public String toXMLString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("<product>");
		sb.append("<id>" + this._product_id + "</id>");
		sb.append("<title>" + this._title + "</title>");
		sb.append("<description>" + this._description + "</description>");
		sb.append("<price type=\"decimal\">" + this._price + "</price>");
		sb.append("</product>");
		return sb.toString() + "\n";
	}

	public Bundle toBundle() {
		Bundle b = new Bundle();
		b.putString("product_id", this._product_id);
		b.putString("title", this._title);
		b.putString("description", this._description);
		b.putString("price", this._price);

		return b;
	}

	public static CatalogEntry fromBundle(Bundle b) {
		CatalogEntry ce = new CatalogEntry();
		ce.set_product_id(b.getString("product_id"));
		ce.set_title(b.getString("title"));
		ce.set_description(b.getString("description"));
		ce.set_price(b.getString("price"));

		return ce;
	}
}
