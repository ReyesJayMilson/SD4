package com.example.pigeonbreedermanagementapplication;

public class NestGetSet {
    private int nestnumber;
    private int profileid;

    public NestGetSet(int nestnumber, int profileid) {
        this.nestnumber = nestnumber;
        this.profileid = profileid;
    }

    @Override
    public String toString() {
        return "NestGetSet{" +
                "nestnumber=" + nestnumber +
                ", profileid=" + profileid +
                '}';
    }

    public int getNestnumber() {
        return nestnumber;
    }

    public void setNestnumber(int nestnumber) {
        this.nestnumber = nestnumber;
    }

    public int getProfileid() {
        return profileid;
    }

    public void setProfileid(int profileid) {
        this.profileid = profileid;
    }
}


