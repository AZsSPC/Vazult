package com.azsspc.az_vault.gamp.tiles;

import android.content.Context;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Tile;

public class Property extends Tile {
    String visible;
    int balance;
    boolean status;
    String[] tags;
    String[] items;

    public Property() {
        this.icon = R.drawable.ic_property_0;
        this.color = R.color.p_normal;
    }

    public void init() {
        if (getBalance() > 0) {
            this.icon = getBalance() == 1 ? R.drawable.ic_property_1 : R.drawable.ic_property_2;
            this.color = R.color.p_good;
        } else if (getBalance() < 0) {
            this.icon = getBalance() == -1 ? R.drawable.ic_property_n1 : R.drawable.ic_property_n2;
            this.color = R.color.p_bad;
        }
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public int getBalance() {
        return balance;
    }

    public String[] getItems() {
        return items;
    }

    public String getVisible() {
        return visible;
    }

    public boolean isStatus() {
        return status;
    }

    public String[] getTags() {
        return tags;
    }

    public String getCenter(Context c) {
        String type = "";
        if (visible.equals(VISIBLE)) type = c.getString(R.string.prop_vis) + "\n";
        else if (visible.equals(INVISIBLE)) type = c.getString(R.string.prop_invis) + "\n";
        return type + toList(abilities);
    }

    public String getFullCenter(Context c) {
        return getCenter(c) +
                "\n---\n" +
                c.getString(R.string.prop_tags) + ": \n" + toList(tags) +
                "\n---\n" +
                c.getString(R.string.prop_allow) + ": " + c.getString(status ? R.string.sc_true : R.string.sc_false);
    }
}
