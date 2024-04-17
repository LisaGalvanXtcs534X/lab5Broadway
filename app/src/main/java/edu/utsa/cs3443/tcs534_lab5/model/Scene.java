package edu.utsa.cs3443.tcs534_lab5.model;

import java.util.List;

public class Scene {
    private int number;
    private String title;
    private List<Role> roles;

    public Scene(int number, String title, List<Role> roles) {
        this.number = number;
        this.title = title;
        this.roles = roles;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public List<Role> getRoles() {
        return roles;
    }
}

