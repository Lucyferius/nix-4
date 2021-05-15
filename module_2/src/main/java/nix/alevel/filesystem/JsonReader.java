package nix.alevel.filesystem;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public List<String> read(String fileName){
        List<String> names = new ArrayList<>();
        File initialFile = new File(fileName);
        String json = null;
        try {
            json = FileUtils.readFileToString(initialFile, "UTF-8");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        JsonArray jsonElements = new Gson().fromJson(json, JsonArray.class);
        jsonElements.forEach(jsonElement -> {
            JsonObject convertedObject = new Gson().fromJson(jsonElement, JsonObject.class);
            names.add(convertedObject.get("Name").getAsString());
        });
        return names;
    }
}
