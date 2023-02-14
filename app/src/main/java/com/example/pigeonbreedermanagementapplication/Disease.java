package com.example.pigeonbreedermanagementapplication;

public class Disease {
    private int id;
    private String name;
    private String desc;

    private String symp;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getSymp(){
        return symp;
    }

    public void setSymp(String desc){
        this.symp = symp;
    }
}
