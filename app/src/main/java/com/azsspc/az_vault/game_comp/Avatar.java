package com.azsspc.az_vault.game_comp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Avatar {

    int old;
    String id;
    String name;
    String job;
    String sex;
    String lore;
    String[] properties;
    String[] items;

    Avatar(JSONObject data) throws JSONException {
        this.old = data.getInt("old");
        this.id = data.getString("id");
        this.lore = data.getString("lore");
        this.name = data.getString("name");
        this.job = data.getString("job");
        this.sex = data.getString("sex");
        this.properties = getFromJSONArray(data.getJSONArray("properties"));
        this.items = getFromJSONArray(data.getJSONArray("items"));
    }

    public static HashMap<String, Avatar> createArray(JSONArray data) {
        int rel = data.length();
        HashMap<String, Avatar> ret = new HashMap<>();
        for (int i = 0; i < rel; i++)
            try {
                Avatar avatar = new Avatar(data.getJSONObject(i));
                ret.put(avatar.getId(), avatar);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return ret;
    }

    public int getOld() {
        return old;
    }

    public String getId() {
        return id;
    }

    public String getLore() {
        return lore;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getSex() {
        return sex;
    }

    public String[] getProperties() {
        return properties;
    }

    public String[] getItems() {
        return items;
    }

}
