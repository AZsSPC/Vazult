package com.azsspc.az_vault.gamp;

import android.content.Context;

import com.azsspc.az_vault.R;

import java.util.Arrays;
import java.util.HashSet;

public class Tile {
    public static final String VISIBLE = "visible";
    public static final String INVISIBLE = "invisible";
    public int icon = R.drawable.ic_steam;
    public int color = R.color.black;
    public String name;
    public String lore;
    public String[] abilities;


    public int getColor() {
        return color;
    }

    public int getIcon() {
        return icon;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
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
