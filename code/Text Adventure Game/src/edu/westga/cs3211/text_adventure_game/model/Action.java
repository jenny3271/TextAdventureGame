package edu.westga.cs3211.text_adventure_game.model;

/**
 * The Action class represents an action that a player can take in the game.
 * Each action has a name and a description.
 * 
 * @author Jennifer Alvarez
 * @version Fall 2024
 */
public class Action {
	private String name;
    private String description;
    
    /**
     * Constructs a new Action with the specified name and description.
     *
     * @param name        the name of the action
     * @param description the description of the action
     * @throws IllegalArgumentException if name or description is null or empty
     */
    public Action(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.name = name;
        this.description = description;
    }
    
    /**
     * Gets the name of the action.
     *
     * @return the name of the action
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the action.
     *
     * @param name the new name of the action
     * @throws IllegalArgumentException if name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }
    
    /**
     * Gets the description of the action.
     *
     * @return the description of the action
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the action.
     *
     * @param description the new description of the action
     * @throws IllegalArgumentException if description is null or empty
     */
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }
    
    /**
     * Executes the action.
     *
     * @param player the player performing the action
     */
    public void execute(Player player) {
    }
    
    /**
     * Determines if the action is available to the player.
     *
     * @param player the player attempting the action
     * @return true if the action is available, false otherwise
     */
    public boolean isAvailable(Player player) {
        return true;
    }

    @Override
    public String toString() {
        return "Action{" + System.lineSeparator()
                + "name='" + this.name + System.lineSeparator()
                + ", description='" + this.description + System.lineSeparator()
                + '}';
    }
}
