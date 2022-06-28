package com.azsspc.az_vault.gamp.tiles;

import android.content.Context;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Tile;

public class Target extends Tile {
    int chance;
    String[] properties;
    String[] items;
    String[] tags;

    Target() {
        this.icon = R.drawable.ic_target;
        this.color = R.color.target;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getChance() {
        return chance;
    }

    public String[] getItems() {
        return items;
    }


    public String getFullCenter(Context c) {
        return "  • " + c.getString(R.string.target_chance) + ": " + chance + "\n" + getCenter(c);
    }

    public String[] getProperties() {
        return properties;
    }
}
