package com.azsspc.az_vault.gamp.tiles;

import android.content.Context;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Tile;

public class Avatar extends Tile {

    int old;
    String sex;
    String[] properties;
    String[] items;

    Avatar() {
        this.icon = R.drawable.ic_avatar;
        this.color = R.color.avatar;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }

    public void setItems(String[] items) {
        this.items = items;
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
