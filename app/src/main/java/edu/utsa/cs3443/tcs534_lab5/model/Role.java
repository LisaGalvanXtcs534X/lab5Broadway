package edu.utsa.cs3443.tcs534_lab5.model;

public class Role {
    private String characterName;

    public Role(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}