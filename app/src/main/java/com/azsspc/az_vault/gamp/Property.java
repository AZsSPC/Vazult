package com.azsspc.az_vault.gamp;

import android.content.Context;

import com.azsspc.az_vault.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Property extends Tile {
    String visibility;
    int balance;
    boolean allow;
    String[] tags;
    String[] items;

    public Property(JSONObject data) throws JSONException {
        super(data);
        this.img = R.drawable.ic_property_0;
        this.balance = data.getInt("balance");
        this.allow = data.getBoolean("allow");
        this.visibility = data.getString("visibility");
        this.tags = getFromJSONArray(data.getJSONArray("tags"));
        this.items = getFromJSONArray(data.getJSONArray("items"));
        if (getBalance() > 0) {
            this.img = R.drawable.ic_property_2;
            this.color = R.color.p_good;
        } else if (getBalance() < 0) {
            this.img = R.drawable.ic_property_n2;
            this.color = R.color.p_bad;
        } else {
            this.img = R.drawable.ic_property_n2;
            this.color = R.color.p_normal;
        }
    }

    public static HashMap<String, Property> createArray(JSONArray data) {
        int rel = data.length();
        HashMap<String, Property> ret = new HashMap<>();
        for (int i = 0; i < rel; i++)
            try {
                Property property = new Property(data.getJSONObject(i));
                ret.put(property.getId(), property);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return ret;
    }

    public int getBalance() {
        return balance;
    }

    public String[] getItems() {
        return items;
    }

    public String getVisibility() {
        return visibility;
    }

    public boolean isAllow() {
        return allow;
    }

    public String[] getTags() {
        return tags;
    }

    public String getCenter(Context c) {
        String type = "";
        if (visibility.equals(VISIBLE)) type = c.getString(R.string.prop_vis) + "\n";
        else if (visibility.equals(INVISIBLE)) type = c.getString(R.string.prop_invis) + "\n";
        return type + toList(abilities);
    }

    public String getFullCenter(Context c) {
        return getCenter(c) +
                "\n---\n" +
                c.getString(R.string.prop_tags) + ": \n" + toList(tags) +
                "\n---\n" +
                c.getString(R.string.prop_allow) + ": " + c.getString(allow ? R.string.sc_true : R.string.sc_false);
    }
}