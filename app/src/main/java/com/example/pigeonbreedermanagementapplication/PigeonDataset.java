package com.example.pigeonbreedermanagementapplication;

public class PigeonDataset {
    String ring_id;
    int Difficulty_Swallowing;
    int Vomiting;
    int Yellow_or_Whitish_Cheesy_Growths_in_Mouth_or_Throat;
    int Weight_Loss;
    int Puffed_Feathers;
    int Mucus_in_Throat;
    int Droopiness;
    int Diarrhea;
    int Huddling;
    int Difficulty_Eating;
    int Bloody_Diarrhea;
    int Watery_Feces;
    int Weakness;
    int Depression;
    int Watery;
    int Mucus_on_Feces;
    int Greenish_Feces;
    int Dehydration;
    int Runny_Nose;
    int Coughing;
    int Unusual_Breathing_Sounds;
    int Swollen_Eye;
    int Swollen_Face;
    int Sneezing;
    int Eye_Discharge;
    int Swollen_Sinus;
    int Yawning;
    int Stretching_of_Neck;

    public PigeonDataset(String ring_id, int difficulty_Swallowing, int vomiting, int yellow_or_Whitish_Cheesy_Growths_in_Mouth_or_Throat, int weight_Loss, int puffed_Feathers, int mucus_in_Throat, int droopiness, int diarrhea, int huddling, int difficulty_Eating, int bloody_Diarrhea, int watery_Feces, int weakness, int depression, int watery, int mucus_on_Feces, int greenish_Feces, int dehydration, int runny_Nose, int coughing, int unusual_Breathing_Sounds, int swollen_Eye, int swollen_Face, int sneezing, int eye_Discharge, int swollen_Sinus, int yawning, int stretching_of_Neck) {
        this.ring_id = ring_id;
        Difficulty_Swallowing = difficulty_Swallowing;
        Vomiting = vomiting;
        Yellow_or_Whitish_Cheesy_Growths_in_Mouth_or_Throat = yellow_or_Whitish_Cheesy_Growths_in_Mouth_or_Throat;
        Weight_Loss = weight_Loss;
        Puffed_Feathers = puffed_Feathers;
        Mucus_in_Throat = mucus_in_Throat;
        Droopiness = droopiness;
        Diarrhea = diarrhea;
        Huddling = huddling;
        Difficulty_Eating = difficulty_Eating;
        Bloody_Diarrhea = bloody_Diarrhea;
        Watery_Feces = watery_Feces;
        Weakness = weakness;
        Depression = depression;
        Mucus_on_Feces = mucus_on_Feces;
        Greenish_Feces = greenish_Feces;
        Dehydration = dehydration;
        Runny_Nose = runny_Nose;
        Coughing = coughing;
        Unusual_Breathing_Sounds = unusual_Breathing_Sounds;
        Swollen_Eye = swollen_Eye;
        Swollen_Face = swollen_Face;
        Sneezing = sneezing;
        Eye_Discharge = eye_Discharge;
        Swollen_Sinus = swollen_Sinus;
        Yawning = yawning;
        Stretching_of_Neck = stretching_of_Neck;
    }

    public String getRing_id() {
        return ring_id;
    }

    public void setRing_id(String ring_id) {
        this.ring_id = ring_id;
    }
}
