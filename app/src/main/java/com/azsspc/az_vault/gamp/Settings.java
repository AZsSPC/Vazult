package com.azsspc.az_vault.gamp;

import org.json.JSONException;
import org.json.JSONObject;

public class Settings {
    //String img_url;
    String name;
    String lore;
    String author;
    boolean avatar;
    boolean target;
    int inventory;
    int properties;
    int balance;

    public Settings(JSONObject data) throws JSONException {
        name = data.getString("name");
        lore = data.getString("lore");
        author = data.getString("author");
        avatar = data.getBoolean("avatar");
        target = data.getBoolean("target");
        inventory = data.getInt("inventory");
        properties = data.getInt("properties");
        balance = data.getInt("balance");
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

    public boolean isAvatarAllow() {
        return avatar;
    }

    public boolean isTargetAllow() {
        return target;
    }

    public int getInventorySize() {
        return inventory;
    }

    public int getPropertiesCount() {
        return properties;
    }

    public int getBalance() {
        return balance;
    }
}
