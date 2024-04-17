package edu.utsa.cs3443.tcs534_lab5.model;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String realName;
    private List<String> roles; // Change to List<String>

    public User(String username, String password, String realName, List<String> roles) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static List<User> readUsersFromFile(Context context, String fileName) {
        List<User> users = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",\\s*"); // Split by comma and optional whitespace
                String username = data[0];
                String password = data[1];
                String realName = data[2];
                List<String> userRoles = new ArrayList<>();
                for (int i = 3; i < data.length; i++) {
                    userRoles.add(data[i]);
                }
                users.add(new User(username, password, realName, userRoles));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean validate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
