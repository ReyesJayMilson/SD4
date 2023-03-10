package com.example.pigeonbreedermanagementapplication.Pigeon;

import java.util.Arrays;

public class PigeonsGetSet {

    private String ring_id;
    private String name;
    private int cage_no;
    private int birth_year;
    private String breed;
    private String gender;
    private String color;

    private String status;
    private String notes;
    private String image;
    private int profile_id;

    public PigeonsGetSet(String ring_id, String name, int cage_no, int birth_year, String breed, String gender, String color, String status, String notes, String image, int profile_id) {
        this.ring_id = ring_id;
        this.name = name;
        this.cage_no = cage_no;
        this.birth_year = birth_year;
        this.breed = breed;
        this.gender = gender;
        this.color = color;
        this.status = status;
        this.notes = notes;
        this.image = image;
        this.profile_id = profile_id;
    }

    @Override
    public String toString() {
        return "PigeonsGetSet{" +
                "ring_id='" + ring_id + '\'' +
                ", name='" + name + '\'' +
                ", cage_no=" + cage_no +
                ", birth_year=" + birth_year +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", color='" + color + '\'' +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                ", image='" + image + '\'' +
                ", profile_id=" + profile_id +
                '}';
    }

    public String getRing_id() {
        return ring_id;
    }

    public void setRing_id(String ring_id) {
        this.ring_id = ring_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCage_no() {
        return cage_no;
    }

    public void setCage_no(int cage_no) {
        this.cage_no = cage_no;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }
}