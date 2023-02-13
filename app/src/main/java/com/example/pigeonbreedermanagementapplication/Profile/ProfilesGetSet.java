package com.example.pigeonbreedermanagementapplication.Profile;

public class ProfilesGetSet {

    private int id;
    private String name;

    public ProfilesGetSet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProfilesGetSet() {
    }

    // toString for printing the contents of a class object
    @Override
    public String toString() {
        return "Profiles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
