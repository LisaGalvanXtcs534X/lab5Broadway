package edu.utsa.cs3443.tcs534_lab5.model;

/**
 * The Role class represents a role played by an actor in a theatrical performance.
 * It contains information about the character name.
 * It provides methods to retrieve and modify the character name.
 *
 * @author Lisa Galvan tcs534
 *
 */
public class Role {
    private String characterName;

    /**
     * Constructs a new Role instance with the specified character name.
     *
     * @param characterName The name of the character.
     */
    public Role(String characterName) {
        this.characterName = characterName;
    }

    /**
     * Getter for the the character name.
     *
     * @return The name of the character.
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Setter for the character name.
     *
     * @param characterName The name of the character.
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}