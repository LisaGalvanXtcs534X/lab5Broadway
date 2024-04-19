package edu.utsa.cs3443.tcs534_lab5.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Act {
    private int number;
    private List<Scene> scenes;

    public Act(int number) {
        this.number = number;
        this.scenes = new ArrayList<>();
        loadScenesFromFile("act" + number + ".txt");
    }

    public int getNumber() {
        return number;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public List<Scene> getScenesForActor(List<User> users, String actorUsername, String actorPassphrase) {
        List<Scene> scenesForActor = new ArrayList<>();
        for (Scene scene : scenes) {
            for (Role role : scene.getRoles()) {
                String roleName = role.getCharacterName(); // Get the full role name
                String roleLastName = extractLastName(roleName); // Extract last name from role name
                // Iterate through the users and check if the actor is associated with this role
                for (User user : users) {
                    if (user.getUsername().equals(actorUsername) && user.getPassword().equals(actorPassphrase)) {
                        if (user.getRoles().contains(roleLastName)) {
                            scenesForActor.add(scene);
                            break; // Found a match, no need to continue searching roles in this scene
                        }
                    }
                }
            }
        }
        // Log the size of scenesForActor
        System.out.println("Scenes for actor: " + scenesForActor.size());
        return scenesForActor;
    }


    private String extractLastName(String fullName) {
        // Find the substring between '(' and ')', exclusive, and trim any whitespace
        int startIndex = fullName.indexOf('(');
        int endIndex = fullName.lastIndexOf(')');
        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            // Return the substring without parentheses, and trim any whitespace
            return fullName.substring(startIndex + 1, endIndex).trim();
        } else {
            // If parentheses are not found or they are empty, return the entire name trimmed
            return fullName.trim();
        }
    }


    private void loadScenesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by the delimiter " - "
                String[] parts = line.split(" - ", 2);
                if (parts.length == 2) {
                    int sceneNumber = Integer.parseInt(parts[0].trim());
                    String[] sceneData = parts[1].split(": ", 2);
                    if (sceneData.length == 2) {
                        String title = sceneData[0].replace("\"", "");
                        String[] roleNames = sceneData[1].split(", ");
                        List<Role> roles = new ArrayList<>();
                        for (String roleName : roleNames) {
                            // Create Role objects from role names
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
