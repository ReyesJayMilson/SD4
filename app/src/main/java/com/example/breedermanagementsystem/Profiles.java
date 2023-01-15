package com.example.breedermanagementsystem;

import android.widget.Button;

public class Profiles {

    private int id;
    private String name;

    public Profiles(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Profiles() {
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
//    Button btnSubmit = findViewById(R.id.btn_SubmitProfile);
//    Button btnCancel = findViewById(R.id.btn_CancelProfile);