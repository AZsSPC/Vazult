package com.azsspc.az_vault.gamp.tiles;

import android.content.Context;
import android.util.Log;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Tile;

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
        this.balance = data.getInt("balance");
        this.allow = data.getBoolean("allow");
        this.visibility = data.getString("visibility").toLowerCase();
        this.tags = getFromJSONArray(data.getJSONArray("tags"), true);
        this.items = getFromJSONArray(data.getJSONArray("items"), true);
        if (getBalance() > 0) {
            this.img = getBalance() == 1 ? R.drawable.ic_property_1 : R.drawable.ic_property_2;
            this.color = R.color.p_good;
        } else if (getBalance() < 0) {
            this.img = getBalance() == -1 ? R.drawable.ic_property_n1 : R.drawable.ic_property_n2;
            this.color = R.color.p_bad;
        } else {
            this.img = R.drawable.ic_property_0;
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
