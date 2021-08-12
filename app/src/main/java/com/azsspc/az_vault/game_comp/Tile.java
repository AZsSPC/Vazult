package com.azsspc.az_vault.game_comp;

import android.view.View;

import com.azsspc.az_vault.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Tile {
    int color;
    int img;
    final String id;
    final String name;
    final String lore;
    final String[] abilities;


    Tile(JSONObject data) throws JSONException {
        this.img = R.drawable.ic_steam;
        this.color = R.color.black;
        this.id = data.getString("id");
        this.name = data.getString("name");
        this.lore = data.getString("lore");
        this.abilities = getFromJSONArray(data.getJSONArray("abilities"));
    }

    public int getColor() {
        return color;
    }

    public int getImg() {
        return img;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public String getTop() {
        return getName();
    }

    public String getCenter() {
        return "• " + String.join("• ", abilities);
    }

    public String getBottom() {
        return getLore();
    }
}
