package com.azsspc.az_vault.gamp;

import android.content.Context;

import com.azsspc.az_vault.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Avatar extends Tile {

    int old;
    String sex;
    String[] properties;
    String[] items;

    Avatar(JSONObject data) throws JSONException {
        super(data);
        this.img = R.drawable.ic_avatar;
        this.color = R.color.avatar;
        this.old = data.getInt("old");
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

    public String getSex() {
        return sex;
    }

    public String[] getProperties() {
        return properties;
    }

    public String[] getItems() {
        return items;
    }

    public String getCenter(Context c) {
        return toList(new String[]{
                c.getString(R.string.avatar_sex) + ": " + sex,
                c.getString(R.string.avatar_age) + ": " + old}
        ) + "\n" + toList(abilities);
    }

}
