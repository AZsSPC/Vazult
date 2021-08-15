package com.azsspc.az_vault.gamp;

import android.content.Context;

import com.azsspc.az_vault.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Tile {
    public static final String VISIBLE = "visible";
    public static final String INVISIBLE = "invisible";
    public int color;
    public int img;
    public final String id;
    public final String name;
    public final String lore;
    public final String[] abilities;


    public Tile(JSONObject data) throws JSONException {
        this.img = R.drawable.ic_steam;
        this.color = R.color.black;
        this.id = data.getString("id").toLowerCase();
        this.name = data.getString("name");
        this.lore = data.getString("lore");
        this.abilities = getFromJSONArray(data.getJSONArray("abilities"), false);
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

    public String getTop(Context c) {
        return getName();
    }

    public String getCenter(Context c) {
        return toList(abilities);
    }

    public String getBottom(Context c) {
        return getLore();
    }

    public static String toList(String[] s) {
        return "  • " + String.join("\n  • ", s);
    }

    public static boolean doesTagsEquals(String[] t1, String[] t2) {
        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList(t1));
        set.addAll(Arrays.asList(t2));
        return set.size() != (t1.length + t2.length);
    }
}
