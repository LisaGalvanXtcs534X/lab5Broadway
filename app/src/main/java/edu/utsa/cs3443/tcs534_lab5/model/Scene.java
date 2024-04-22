package edu.utsa.cs3443.tcs534_lab5.model;

import java.util.List;

/**
 * The Scene class represents a scene in a theatrical performance. It contains information about the scene number,
 * title, and roles played in the scene.
 * It provides methods to retrieve and modify scene details.
 *
 * @author Lisa Galvan tcs534
 *
 */
public class Scene {
    private int number;
    private String title;
    private List<Role> roles;

    /**
     * Constructs a new Scene instance with the specified scene number, title, and roles.
     *
     * @param number The number of the scene.
     * @param title The title of the scene.
     * @param roles The list of roles played in the scene.
     */
    public Scene(int number, String title, List<Role> roles) {
        this.number = number;
        this.title = title;
        this.roles = roles;
    }

    /**
     * Getter for the number of the scene.
     *
     * @return The number of the scene.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter for number of the scene.
     *
     * @param number The number of the scene.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter for the title of the scene.
     *
     * @return The title of the scene.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title of the scene.
     *
     * @param title The title of the scene.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the list of roles played in the scene.
     *
     * @return The list of roles played in the scene.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Setter for list of roles played in the scene.
     *
     * @param roles The list of roles played in the scene.
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
