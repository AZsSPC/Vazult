package com.azsspc.az_vault.gamp;

import org.json.JSONException;
import org.json.JSONObject;

public class Settings {
    //String img_url;
    String name;
    String lore;
    String author;
    int inventory;
    int properties;
    int balance_max;
    int balance_min;

    public Settings(JSONObject data) throws JSONException {
        name = data.getString("name");
        lore = data.getString("lore");
        author = data.getString("author");
        inventory = data.getInt("inventory");
        properties = data.getInt("properties");
        balance_max = data.getInt("balance_max");
        balance_min = data.getInt("balance_min");
        //img_url = data.getString("img_url");
    }

    /*
        public String getImgURL() {
            return img_url;
        }
    */
    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public String getAuthor() {
        return author;
    }

    public int getInventorySize() {
        return inventory;
    }

    public int getPropertiesCount() {
        return properties;
    }

    public int getBalanceMax() {
        return balance_max;
    }

    public int getBalanceMin() {
        return balance_min;
    }
}
