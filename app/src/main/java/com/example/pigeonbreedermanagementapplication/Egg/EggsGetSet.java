package com.example.pigeonbreedermanagementapplication.Egg;

public class EggsGetSet
{
    private int egg_id;
    private int cage_number;
    private int nest_number;
    private String laying_date;
    private String hatching_date;
    private String father;
    private String mother;

    public EggsGetSet(int egg_id, int cage_number, int nest_number, String laying_date, String hatching_date, String father, String mother) {
        this.egg_id = egg_id;
        this.cage_number = cage_number;
        this.nest_number = nest_number;
        this.laying_date = laying_date;
        this.hatching_date = hatching_date;
        this.father = father;
        this.mother = mother;
    }

    @Override
    public String toString() {
        return "EggsGetSet{" +
                "egg_id=" + egg_id +
                ", cage_number=" + cage_number +
                ", nest_number=" + nest_number +
                ", laying_date='" + laying_date + '\'' +
                ", hatching_date='" + hatching_date + '\'' +
                ", father='" + father + '\'' +
                ", mother='" + mother + '\'' +
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
}
