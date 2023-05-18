package com.example.pigeonbreedermanagementapplication.HealthCalendar;

public class HCalendarGetSet {
    private int health_id;
    private String note_date;
    private String ring_id;
    private String note_description;
    private String health_status;
    private String note_medication;
    private String symptoms_list;
    private int disease_id;
    private int profile_id;

    public HCalendarGetSet(int health_id, String note_date, String ring_id, String note_description, String health_status, String note_medication, String symptoms_list, int disease_id, int profile_id) {
        this.health_id = health_id;
        this.note_date = note_date;
        this.ring_id = ring_id;
        this.note_description = note_description;
        this.health_status = health_status;
        this.note_medication = note_medication;
        this.symptoms_list = symptoms_list;
        this.disease_id = disease_id;
        this.profile_id = profile_id;
    }

    @Override
    public String toString() {
        return "HCalendarGetSet{" +
                "health_id=" + health_id +
                ", note_date='" + note_date + '\'' +
                ", ring_id='" + ring_id + '\'' +
                ", note_description='" + note_description + '\'' +
                ", health_status='" + health_status + '\'' +
                ", note_medication='" + note_medication + '\'' +
                ", symptoms_list='" + symptoms_list + '\'' +
                ", disease_id=" + disease_id +
                ", profile_id=" + profile_id +
                '}';
    }

    public int getHealth_id() {
        return health_id;
    }

    public void setHealth_id(int health_id) {
        this.health_id = health_id;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

    public String getRing_id() {
        return ring_id;
    }

    public void setRing_id(String ring_id) {
        this.ring_id = ring_id;
    }

    public String getNote_description() {
        return note_description;
    }

    public void setNote_description(String note_description) {
        this.note_description = note_description;
    }

    public String getHealth_status() {
        return health_status;
    }

    public void setHealth_status(String health_status) {
        this.health_status = health_status;
    }

    public String getNote_medication() {
        return note_medication;
    }

    public void setNote_medication(String note_medication) {
        this.note_medication = note_medication;
    }

    public String getSymptoms_list() {
        return symptoms_list;
    }

    public void setSymptoms_list(String symptoms_list) {
        this.symptoms_list = symptoms_list;
    }

    public int getDisease_id() {
        return disease_id;
    }

    public void setDisease_id(int disease_id) {
        this.disease_id = disease_id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }
}