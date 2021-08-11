package com.azsspc.az_vault;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DataLoader {
    public static String active_script_url;
    public static JSONObject active_script_settings;
    public static JSONArray active_script_avatars;
    public static JSONArray active_script_items;
    public static JSONArray active_script_properties;
    public static JSONArray active_script_targets;
    public static final String gfs_error = "gfs_error";

    public static String getScripSetting(String target) {
        try {
            return active_script_settings.getString(target);
        } catch (JSONException e) {
            e.printStackTrace();
            return "null";
        }
    }

    public static void loadScript(Context c, String script_url) {
        try {
            String file_name = script_url.replaceAll("^.+/", "");
            active_script_url = script_url;
            String script_in = getFromStorage(c, file_name);
            JSONObject uj = new JSONObject(script_in.equals(gfs_error)
                    ? DataLoader.getFromCloud(c, file_name, script_url)
                    : script_in
            );
            active_script_settings = uj.getJSONObject("settings");
            active_script_avatars = uj.getJSONArray("avatars");
            active_script_items = uj.getJSONArray("items");
            active_script_properties = uj.getJSONArray("properties");
            active_script_targets = uj.getJSONArray("targets");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getFromCloud(Context c, String file_name, String url) {
        new FromCloudLoader().execute(c, url, file_name);
        return getFromStorage(c, file_name);
    }

    public static String getFromStorage(Context c, String file_name) {
        try {
            StringBuilder ret = new StringBuilder();
            String line;
            BufferedReader bread = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(c.getFilesDir(), file_name))
            ));
            while ((line = bread.readLine()) != null) ret.append(line);
            return ret.toString().replaceAll("\\s+", " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gfs_error;
    }
}

/**
 * <p>
 * [0] - context
 * </p>
 * <p>
 * [1] - load url
 * </p>
 * <p>
 * [2] - file name
 * </p>
 */
class FromCloudLoader extends AsyncTask<Object, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Object... os) {
        Context c = (Context) os[0];
        String url = (String) os[1];
        String file_name = (String) os[2];
        try {
            ReadableByteChannel rbc = Channels.newChannel(new URL(url).openStream());
            FileOutputStream fos = new FileOutputStream(new File(c.getFilesDir(), file_name));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}