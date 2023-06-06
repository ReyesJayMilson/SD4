package com.example.pigeonbreedermanagementapplication;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class PigeonDataJsonCreator {
    public static void main(String[] args) {
        List<PigeonDataset> symptoms = new ArrayList<>();
        symptoms.add(new PigeonDataset("pigeon1", 0, 0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));

        // Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(symptoms);

        // Write JSON string to file
        try (FileWriter writer = new FileWriter("pigeonDataset.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
