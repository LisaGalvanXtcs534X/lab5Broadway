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

    public List<Scene> getScenesForActor(String actorFullName) {
        List<Scene> scenesForActor = new ArrayList<>();
        for (Scene scene : scenes) {
            for (Role role : scene.getRoles()) {
                String roleName = role.getCharacterName(); // Get the full role name
                String roleLastName = extractLastName(roleName); // Extract last name from role name
                if (actorFullName.contains(roleLastName)) {
                    scenesForActor.add(scene);
                    break; // Found a match, no need to continue searching roles in this scene
                }
            }
        }
        return scenesForActor;
    }

    private String extractLastName(String fullName) {
        // Assuming the last name is the last word in the full name
        String[] parts = fullName.split("\\s+");
        if (parts.length > 0) {
            return parts[parts.length - 1];
        } else {
            return ""; // No last name found
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
