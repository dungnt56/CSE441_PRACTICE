package com.dungnt.thuc_hanh_03.helper;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonHelper {
    public static <T> List<T> loadDataFromJson(Context context, String fileName, Type typeOfT) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.e("JsonHelper", "Error reading JSON file", e);
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(json, typeOfT);
    }
    public static <T> void saveDataToJson(Context context, String fileName, T data) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(data);
        try {
            File file = new File(context.getFilesDir(),fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(jsonString);
            bufferedWriter.close();
            Log.e("JsonHelper", "Save JSON file");

        } catch (IOException e){
            Log.e("JsonHelper", "Error save JSON file", e);
        }

    }
}
