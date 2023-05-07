package com.example.pigeonbreedermanagementapplication.HealthCalendar;

public class HCalendarGetSet {
    private int health_id;
    private String note_date;
    private String ring_id;
    private String note_description;
    private int profile_id;

    public HCalendarGetSet(int health_id, String note_date, String ring_id, String note_description, int profile_id) {
        this.health_id = health_id;
        this.note_date = note_date;
        this.ring_id = ring_id;
        this.note_description = note_description;
        this.profile_id = profile_id;
    }

    @Override
    public String toString() {
        return "HCalendarGetSet{" +
                "health_id=" + health_id +
                ", note_date='" + note_date + '\'' +
                ", ring_id='" + ring_id + '\'' +
                ", note_description='" + note_description + '\'' +
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

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }
}
