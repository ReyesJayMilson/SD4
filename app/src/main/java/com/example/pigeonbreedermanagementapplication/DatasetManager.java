package com.example.pigeonbreedermanagementapplication;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class DatasetManager {
    private Context context;

    public DatasetManager(Context context) {
        this.context = context;
    }

    public List<PigeonDataset> loadPigeonData() {
        String jsonString = loadJSONFromAsset("pigeonDataset.json");
        if (jsonString == null) {
            return null; // or throw an exception, or however you want to handle this
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<PigeonDataset>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    private String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

