package com.azsspc.az_vault.gamp.tiles;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Tile;

public class Item extends Tile {
    int weight;
    String type;
    String img;

    Item() {
        this.icon = R.drawable.ic_item;
        this.color = R.color.item;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
