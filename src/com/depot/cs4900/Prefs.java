package com.depot.cs4900;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Prefs {

    private SharedPreferences _prefs = null;
    private Editor _editor = null;
    private String _username= "Unknown";
    private String _password= "Unknown";
    private String _serverurl = "http://localhost:3000";
    private boolean _valid = false;
    private int _mode = Constants.LOCAL_ONLY;

    public Prefs(Context context) {
        this._prefs = context.getSharedPreferences("PREFS_PRIVATE", Context.MODE_PRIVATE);
        this._editor = this._prefs.edit();
    }

    public String getValue(String key, String defaultvalue) {
        if (this._prefs == null) {
            return "Unknown";
        }

        return this._prefs.getString(key, defaultvalue);
    }

    public void setValue(String key, String value) {
        if (this._editor == null) {
            return;
        }

        this._editor.putString(key, value);

    }

    public String getUserName() {
        if (this._prefs == null) {
            return "Unknown";
        }

        this._username = this._prefs.getString("username", "Unknown");
        return this._username;
    }
    
    public String getPassword() {
        if (this._prefs == null) {
            return "Unknown";
        }

        this._password = this._prefs.getString("password", "Unknown");
        return this._password;
    }

    public String getServer() {
        if (this._prefs == null) {
            return "http://localhost:3000/";
        }

        this._serverurl = this._prefs.getString("serverurl", "http://localhost:3000");
        return this._serverurl;
    }

    public void setUserName(String newusername) {
        if (this._editor == null) {
            return;
        }

        this._editor.putString("username", newusername);
    }
    
    public void setPassword(String newpassword) {
        if (this._editor == null) {
            return;
        }

        this._editor.putString("password", newpassword);
    }
    
    public void setServer(String serverurl) {
        if (this._editor == null) {
            return;
        }
        this._editor.putString("serverurl", serverurl);
    }

    public void setValid(boolean v) {
        if (this._editor == null) {
            return;
        }
        this._editor.putBoolean("valid", v);
    }
    
    public boolean isValid() {
        if (this._prefs == null) {
            return false;
        }

        this._valid = this._prefs.getBoolean("valid", false);
        return this._valid;
    }
    
    public void setMode(int m) {
        if (this._editor == null) {
            return;
        }
        this._editor.putInt("mode", m);
    }
    
    public int getMode() {
        if (this._prefs == null) {
            return Constants.LOCAL_ONLY;
        }

        this._mode = this._prefs.getInt("mode", Constants.LOCAL_ONLY);
        return this._mode;
    }
    
    public void save() {
        if (this._editor == null) {
            return;
        }
        this._editor.commit();
    }
}
