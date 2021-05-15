package nix.alevel.filesystem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {
    public void write(String fileName, List<String> names){
        JSONArray jsonArray = new JSONArray();
        JSONObject object;
        for (int i = 0; i < names.size(); i++) {
            object = new JSONObject();
            object.put("Name", names.get(i));
            jsonArray.put(object);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(jsonArray.toString(2));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

