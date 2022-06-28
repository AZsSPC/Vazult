package com.azsspc.az_vault.gamp;

import com.azsspc.az_vault.gamp.tiles.*;

import java.util.Map;

public class Script {
    public Settings settings;
    public Map<String, Avatar> avatars;
    public Map<String, Item> items;
    public Map<String, Target> targets;
    public Map<String, Property> properties;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Map<String, Avatar> getAvatars() {
        return avatars;
    }

    public void setAvatars(Map<String, Avatar> avatars) {
        this.avatars = avatars;
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    public Map<String, Target> getTargets() {
        return targets;
    }

    public void setTargets(Map<String, Target> targets) {
        this.targets = targets;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }
}
