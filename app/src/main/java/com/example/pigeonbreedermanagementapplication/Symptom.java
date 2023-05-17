package com.example.pigeonbreedermanagementapplication;

public class Symptom {
    private int symptomId;
    private String symptomName;

    public Symptom(int symptomId, String symptomName) {
        this.symptomId = symptomId;
        this.symptomName = symptomName;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "symptomId=" + symptomId +
                ", symptomName='" + symptomName + '\'' +
                '}';
    }

    public int getSymptomId() {
        return symptomId;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }
}
