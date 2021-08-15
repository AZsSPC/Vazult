package com.azsspc.az_vault.gamp.tiles;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Tile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Item extends Tile {
    int weight;

    Item(JSONObject data) throws JSONException {
        super(data);
        this.img = R.drawable.ic_item;
        this.color = R.color.item;
        this.weight = data.getInt("weight");
    }

    public static HashMap<String, Item> createArray(JSONArray data) {
        int rel = data.length();
        HashMap<String, Item> ret = new HashMap<>();
        for (int i = 0; i < rel; i++)
            try {
                Item item = new Item(data.getJSONObject(i));
                ret.put(item.getId(), item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return ret;
    }

    public int getWeight() {
        return weight;
    }
}
