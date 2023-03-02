package com.example.pigeonbreedermanagementapplication.Egg;

public class EggsGetSet {
    private int egg_id;
    private int cage_number;
    private int nest_number;
    private String laying_date;
    private String hatching_date;
    private String egg_status;
    private String father;
    private String mother;
    private int profile_id;

    public EggsGetSet(int egg_id, int cage_number, int nest_number, String laying_date, String hatching_date, String egg_status, String father, String mother, int profile_id) {
        this.egg_id = egg_id;
        this.cage_number = cage_number;
        this.nest_number = nest_number;
        this.laying_date = laying_date;
        this.hatching_date = hatching_date;
        this.egg_status = egg_status;
        this.father = father;
        this.mother = mother;
        this.profile_id = profile_id;
    }

    @Override
    public String toString() {
        return "EggsGetSet{" +
                "egg_id=" + egg_id +
                ", cage_number=" + cage_number +
                ", nest_number=" + nest_number +
                ", laying_date='" + laying_date + '\'' +
                ", hatching_date='" + hatching_date + '\'' +
                ", egg_status='" + egg_status + '\'' +
                ", father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", profile_id=" + profile_id +
                '}';
    }

    public int getEgg_id() {
        return egg_id;
    }

    public void setEgg_id(int egg_id) {
        this.egg_id = egg_id;
    }

    public int getCage_number() {
        return cage_number;
    }

    public void setCage_number(int cage_number) {
        this.cage_number = cage_number;
    }

    public int getNest_number() {
        return nest_number;
    }

    public void setNest_number(int nest_number) {
        this.nest_number = nest_number;
    }

    public String getLaying_date() {
        return laying_date;
    }

    public void setLaying_date(String laying_date) {
        this.laying_date = laying_date;
    }

    public String getHatching_date() {
        return hatching_date;
    }

    public void setHatching_date(String hatching_date) {
        this.hatching_date = hatching_date;
    }

    public String getEgg_status() {
        return egg_status;
    }

    public void setEgg_status(String egg_status) {
        this.egg_status = egg_status;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }
}