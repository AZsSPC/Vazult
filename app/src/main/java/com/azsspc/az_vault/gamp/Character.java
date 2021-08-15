package com.azsspc.az_vault.gamp;

import android.util.Log;
import android.widget.Toast;

import com.azsspc.az_vault.gamp.tiles.Avatar;
import com.azsspc.az_vault.gamp.tiles.Item;
import com.azsspc.az_vault.gamp.tiles.Property;
import com.azsspc.az_vault.gamp.tiles.Target;

import java.util.ArrayList;
import java.util.Arrays;

import static com.azsspc.az_vault.DataLoader.as_avatars;
import static com.azsspc.az_vault.DataLoader.as_items;
import static com.azsspc.az_vault.DataLoader.as_properties;
import static com.azsspc.az_vault.DataLoader.as_targets;

public class Character {
    final String CKC;
    final Target target;
    final Avatar avatar;
    Item[] inventory;
    Property[] properties;
    String location;
    int balance;

    public Character(String character_key_code) {
        this.CKC = character_key_code;
        String[] key_codes = character_key_code.split(" ", 3);
        target = as_targets.get(key_codes[0]);
        avatar = as_avatars.get(key_codes[1]);

        ArrayList<Property> list_prop = new ArrayList<>();
        ArrayList<Item> list_items = new ArrayList<>();
        ArrayList<String> arr_prop = new ArrayList<>();
        ArrayList<String> arr_items = new ArrayList<>();
        arr_prop.addAll(Arrays.asList(avatar.getProperties()));
        arr_prop.addAll(Arrays.asList(target.getProperties()));
        arr_prop.addAll(Arrays.asList(key_codes[2].split(" ")));
        arr_items.addAll(Arrays.asList(avatar.getItems()));
        arr_items.addAll(Arrays.asList(target.getItems()));

        for (String s : arr_prop) {
            Property p = as_properties.get(s);
            list_prop.add(p);
            arr_items.addAll(Arrays.asList(p.getItems()));
        }
        for (String s : arr_items) list_items.add(as_items.get(s));
        inventory = list_items.toArray(new Item[0]);
        properties = list_prop.toArray(new Property[0]);
        this.balance = 0;
        for (Property p : properties) this.balance += p.getBalance();
    }

    public String getCKC() {
        return CKC;
    }

    public Target getTarget() {
        return target;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Item[] getInventory() {
        return inventory;
    }

    public Property[] getProperties() {
        return properties;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getBalance() {
        return balance;
    }
}
