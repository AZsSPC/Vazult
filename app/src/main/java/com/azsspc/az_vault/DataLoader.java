package com.azsspc.az_vault;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.azsspc.az_vault.gamp.Script;
import com.azsspc.az_vault.gamp.tiles.Avatar;
import com.azsspc.az_vault.gamp.tiles.Item;
import com.azsspc.az_vault.gamp.tiles.Property;
import com.azsspc.az_vault.gamp.Settings;
import com.azsspc.az_vault.gamp.tiles.Target;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.azsspc.az_vault.MainActivity.SP_KEY_AS;
import static com.azsspc.az_vault.MainActivity.sp;

public class DataLoader {
    public static String as_url;
    public static Script script;
    public static final String gfs_error = "gfs_error";


    public static void loadScript(Context c, String script_url, boolean from_cloud) {
        sp.edit().putString(SP_KEY_AS, as_url = script_url).apply();
        String file_name = as_url.replaceAll("^.+/", "");
        String s = getFromStorage(c, file_name);
        Yaml yml = new Yaml(new Constructor(Script.class));
        script = (Script) yml.load((s.equals(gfs_error) || from_cloud) ? getFromCloud(c, file_name, script_url) : s);
    }

    public static String getFromCloud(Context c, String file_name, String url) {
        Toast.makeText(c, c.getString(R.string.sc_load_wait), Toast.LENGTH_SHORT).show();
        try {
            new FromCloudLoader().execute(c, url, file_name).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(c, c.getString(R.string.sc_load_done), Toast.LENGTH_SHORT).show();
        return getFromStorage(c, file_name);
    }

    public static String getFromStorage(Context c, String file_name) {
        try (BufferedReader bread = new BufferedReader(new InputStreamReader(
                new FileInputStream(new File(c.getFilesDir(), file_name))))) {
            StringBuilder ret = new StringBuilder();
            String line;
            while ((line = bread.readLine()) != null) ret.append(line).append("\n");
            return ret.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gfs_error;
    }
}

/**
 * <p>[0] - context</p>
 * <p>[1] - load url</p>
 * <p>[2] - file name</p>
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
