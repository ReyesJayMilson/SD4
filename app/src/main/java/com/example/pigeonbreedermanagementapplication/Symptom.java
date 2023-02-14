package com.example.pigeonbreedermanagementapplication;

public class Symptom {
    private int symptomId;
    private String symptomName;

    public Symptom(int symptomId, String symptomName) {
        this.symptomId = symptomId;
        this.symptomName = symptomName;
    }

    public int getSymptomId() {
        return symptomId;
    }

    public String getSymptomName() {
        return symptomName;
    }

}
