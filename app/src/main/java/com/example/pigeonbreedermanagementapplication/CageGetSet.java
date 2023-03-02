package com.example.pigeonbreedermanagementapplication;

public class CageGetSet {
    private int cagenumber;
    private int profileid;

    public CageGetSet(int cagenumber, int profileid) {
        this.cagenumber = cagenumber;
        this.profileid = profileid;
    }

    @Override
    public String toString() {
        return "CageGetSet{" +
                "cagenumber=" + cagenumber +
                ", profileid=" + profileid +
                '}';
    }

    public int getCagenumber() {
        return cagenumber;
    }

    public void setCagenumber(int cagenumber) {
        this.cagenumber = cagenumber;
    }

    public int getProfileid() {
        return profileid;
    }

    public void setProfileid(int profileid) {
        this.profileid = profileid;
    }
}
