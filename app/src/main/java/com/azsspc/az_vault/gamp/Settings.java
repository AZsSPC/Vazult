package com.azsspc.az_vault.gamp;


public class Settings {
    String img_url;
    String name;
    String lore;
    String author;
    int inventory;
    int properties;
    int balance_max;
    int balance_min;
    String version;

    public Settings() {
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getProperties() {
        return properties;
    }

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public int getBalance_max() {
        return balance_max;
    }

    public void setBalance_max(int balance_max) {
        this.balance_max = balance_max;
    }

    public int getBalance_min() {
        return balance_min;
    }

    public void setBalance_min(int balance_min) {
        this.balance_min = balance_min;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
