package com.example.pigeonbreedermanagementapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiseaseP {

    TextFileReader KNN = new TextFileReader();

    public class DiseasePredictor {
        private Map<String, List<String>> diseaseSymptoms;

        public DiseasePredictor() {
            diseaseSymptoms = new HashMap<>();
            diseaseSymptoms.put("PIGEON CANKER", Arrays.asList("Difficulty Swallowing", "Vomiting", "Yellow or Whitish Cheesy Growths in Mouth or Throat", "Weight Loss", "Puffed Feathers", "Mucus in Throat"));
            diseaseSymptoms.put("PIGEON WORM", Arrays.asList("Weight Loss", "Droopiness", "Diarrhea"));
            diseaseSymptoms.put("PIGEON COCCIDIA", Arrays.asList("Weight Loss", "Huddling", "Difficulty Eating"," Bloody Diarrhea", "Watery Feces", "Weakness", "Depression"));
            diseaseSymptoms.put("PIGEON HEXAMITA", Arrays.asList("Vomiting", "Weight Loss","Diarrhea", "Watery Feces","Weakness", "Mucus on Feces","Greenish Feces", "Dehydration"));
            diseaseSymptoms.put("PIGEON MYCOPLASMA", Arrays.asList("Runny Nose", "Coughing","Unusual Breathing Sounds", "Swollen Eye", "Swollen Face"));
            diseaseSymptoms.put("PIGEON RESPIRATORY INFECTION", Arrays.asList("Difficulty Swallowing", "Runny Nose","Sneezing","Eye Discharge","Swollen Sinus","Yawning","Stretching of Neck"));
            // Add more diseases...
        }

        public Map<String, Double> predictDiseases(List<String> symptoms) {
            Map<String, Double> diseaseMatches = new HashMap<>();
            DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Define the decimal format

            for (String disease : diseaseSymptoms.keySet()) {
                List<String> diseaseSymptomsList = diseaseSymptoms.get(disease);
                int count = 0;
                for (String symptom : symptoms) {
                    if (diseaseSymptomsList.contains(symptom)) {
                        count++;
                    }
                }
                double percentage = (double) count / diseaseSymptomsList.size() * 100;
                if (percentage > 0) {
                    double roundedPercentage = Double.parseDouble(decimalFormat.format(percentage)); // Round the percentage to 2 decimal places
                    diseaseMatches.put(disease, roundedPercentage);
                }
            }

            // If no matches are found, return "Unknown disease"
            if (diseaseMatches.isEmpty()) {
                diseaseMatches.put("Unknown disease", 0.0);
            }

            return diseaseMatches;
        }

    }


}
