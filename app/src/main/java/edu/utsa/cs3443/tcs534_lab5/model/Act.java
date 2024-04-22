package edu.utsa.cs3443.tcs534_lab5.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Act class represents an act in a theatrical performance. It contains information about the act number and scenes associated with it.
 * It also provides methods for retrieving scenes for a specific actor.
 *
 * @author Lisa Galvan tcs534
 *
 */
public class Act {
    private int number;
    private List<Scene> scenes;

    /**
     * Constructs a new Act instance with the specified act number and loads scenes from a file.
     *
     * @param number The number of the act.
     */
    public Act(int number) {
        this.number = number;
        this.scenes = new ArrayList<>();
        loadScenesFromFile("act" + number + ".txt");
    }

    /**
     * Getter for the number of the act.
     *
     * @return The number of the act.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter for the number of the act.
     *
     * @param number The number of the act.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter for the list of scenes in the act.
     *
     * @return The list of scenes in the act.
     */
    public List<Scene> getScenes() {
        return scenes;
    }

    /**
     * Setter for the list of scenes in the act.
     *
     * @param scenes The list of scenes in the act.
     */
    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }

    /**
     * Retrieves scenes for a specific actor based on their roles.
     *
     * @param users           The list of users.
     * @param actorUsername   The username of the actor.
     * @param actorPassphrase The passphrase of the actor.
     * @return The list of scenes assigned to the actor.
     */
    public List<Scene> getScenesForActor(List<User> users, String actorUsername, String actorPassphrase) {
        List<Scene> scenesForActor = new ArrayList<>();
        for (Scene scene : scenes) {
            for (Role role : scene.getRoles()) {
                String roleName = role.getCharacterName();
                String roleLastName = extractLastName(roleName);
                for (User user : users) {
                    if (user.getUsername().equals(actorUsername) && user.getPassword().equals(actorPassphrase)) {
                        if (user.getRoles().contains(roleLastName)) {
                            scenesForActor.add(scene);
                            break;
                        }
                    }
                }
            }
        }
        return scenesForActor;
    }

    /**
     * Extracts the last name from a full name (character name).
     *
     * @param fullName The full name (character name).
     * @return The last name extracted from the full name.
     */
    private String extractLastName(String fullName) {
        int startIndex = fullName.indexOf('(');
        int endIndex = fullName.lastIndexOf(')');
        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            return fullName.substring(startIndex + 1, endIndex).trim();
        } else {
            return fullName.trim();
        }
    }

    /**
     * Loads scenes from a file into the act.
     *
     * @param fileName The name of the file containing scene data.
     */
    private void loadScenesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ", 2);
                if (parts.length == 2) {
                    int sceneNumber = Integer.parseInt(parts[0].trim());
                    String[] sceneData = parts[1].split(": ", 2);
                    if (sceneData.length == 2) {
                        String title = sceneData[0].replace("\"", "");
                        String[] roleNames = sceneData[1].split(", ");
                        List<Role> roles = new ArrayList<>();
                        for (String roleName : roleNames) {
                            roles.add(new Role(roleName.trim()));
                        }
                        scenes.add(new Scene(sceneNumber, title, roles));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
