package com.azsspc.az_vault;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.azsspc.az_vault.gamp.Avatar;
import com.azsspc.az_vault.gamp.Item;
import com.azsspc.az_vault.gamp.Property;
import com.azsspc.az_vault.gamp.Settings;
import com.azsspc.az_vault.gamp.Target;

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
import java.util.HashMap;

public class DataLoader {
    public static String as_url;
    public static Settings as_settings;
    public static HashMap<String, Avatar> as_avatars;
    public static HashMap<String, Item> as_items;
    public static HashMap<String, Property> as_properties;
    public static HashMap<String, Target> as_targets;
    public static final String gfs_error = "gfs_error";


    public static boolean loadScript(Context c, String script_url) {
        try {
            Toast.makeText(c, c.getString(R.string.sc_load_wait), Toast.LENGTH_SHORT).show();
            as_url = script_url;
            String file_name = as_url.replaceAll("^.+/", "");
            String script_in = getFromStorage(c, file_name);
            JSONObject uj = new JSONObject(script_in.equals(gfs_error)
                    ? DataLoader.getFromCloud(c, file_name, as_url)
                    : script_in
            );
            as_settings = new Settings(uj.getJSONObject("settings"));
            as_avatars = Avatar.createArray(uj.getJSONArray("avatars"));
            as_items = Item.createArray(uj.getJSONArray("items"));
            as_properties = Property.createArray(uj.getJSONArray("properties"));
            as_targets = Target.createArray(uj.getJSONArray("targets"));
            Toast.makeText(c, c.getString(R.string.sc_load_done), Toast.LENGTH_SHORT).show();
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
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
