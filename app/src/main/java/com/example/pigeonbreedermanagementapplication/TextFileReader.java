package com.example.pigeonbreedermanagementapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Use KnnDiseasePredictor as Reference
public class TextFileReader {
    public static void main(String[] args) {
        try {
            String filePath = "C:/Users/reyes/AndroidStudioProjects/BreederManagementSystem/app/src/main/java/com/example/pigeonbreedermanagementapplication/KnnDiseasePredictor.txt";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                // Process each line of the text file
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
