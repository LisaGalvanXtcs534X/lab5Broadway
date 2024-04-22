package edu.utsa.cs3443.tcs534_lab5.model;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The User class represents a user of the application. It contains information about the username, password, real name,
 * and roles associated with the user.
 * It provides methods to retrieve and modify user details, read users from a file, and validate user credentials.
 *
 * @author Lisa Galvan tcs534
 *
 */
public class User {
    private String username;
    private String password;
    private String realName;
    private List<String> roles;

    /**
     * Constructs a new User instance with the specified username, password, real name, and roles.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param realName The real name of the user.
     * @param roles The list of roles associated with the user.
     */
    public User(String username, String password, String realName, List<String> roles) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.roles = roles;
    }

    /**
     * Getter for the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username of the user.
     *
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the real name of the user.
     *
     * @return The real name of the user.
     */
    public String getRealName() {
        return realName;
    }

    /**
     * Setter for the real name of the user.
     *
     * @param realName The real name of the user.
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Getter for the roles associated with the user.
     *
     * @return The list of roles associated with the user.
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Setter for the roles associated with the user.
     *
     * @param roles The list of roles associated with the user.
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Reads user data from a file and returns a list of User objects.
     *
     * @param context The application context.
     * @param fileName The name of the file containing user data.
     * @return A list of User objects read from the file.
     */
    public static List<User> readUsersFromFile(Context context, String fileName) {
        List<User> users = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",\\s*");
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

    /**
     * Validates the user credentials.
     *
     * @param username The username to validate.
     * @param password The password to validate.
     * @return true if the username and password match the user's credentials, otherwise false.
     */
    public boolean validate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
