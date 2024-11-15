package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a location in the text adventure game. Each location has a name,
 * description, and can contain a hazard or be a goal location. It also manages
 * available actions for the player and connected locations to facilitate
 * movement.
 * 
 * @author Jennifer Alvarez
 * @version Fall 2024
 */
public class Location {
	private String name;
	private String description;
	private boolean hasHazard;
	private boolean isGoal;
	private List<Action> availableActions;
	private List<Hazard> hazards;
	private Map<Direction, Location> connectedLocations;

	/**
	 * Creates a new Location with the specified name and description.
	 * 
	 * @param name        the name of the location
	 * @param description the description of the location
	 */
	public Location(String name, String description) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty.");
		}
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or empty.");
		}
		this.name = name;
		this.description = description;
		this.connectedLocations = new HashMap<>();
		this.hazards = new ArrayList<>();
		this.availableActions = new ArrayList<>();
	}

	/**
	 * Gets the name of the Location
	 * 
	 * @return the name of the location
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the location
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty.");
		}
		this.name = name;
	}

	/**
	 * Gets the description of the location
	 * 
	 * @return the description of the location
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the location
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or empty.");
		}
		this.description = description;
	}

	/**
	 * Checks if the location has a hazard
	 * 
	 * @return true if the location has a hazard, false otherwise
	 */
	public boolean isHasHazard() {
		return this.hasHazard;
	}

	/**
	 * Sets whether the location has a hazard
	 * 
	 * @param hasHazard true if the location has a hazard, false otherwise
	 */
	public void setHasHazard(boolean hasHazard) {
		this.hasHazard = hasHazard;
	}

	/**
	 * Checks if the location is a goal
	 * 
	 * @return isGoal is true if the location is a goal location, false otherwise
	 */
	public boolean isGoal() {
		return this.isGoal;
	}

	/**
	 * Sets whether the location is a goal
	 * 
	 * @param isGoal is true if the location is a goal location, false otherwise
	 */
	public void setGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}

	/**
	 * Gets the available actions at the current location
	 * 
	 * @return the availableActions at the current location
	 */
	public List<Action> getAvailableActions() {
		return this.availableActions;
	}

	/**
	 * Sets the available actions at the current location
	 * 
	 * @param availableActions the availableActions to set
	 */
	public void setAvailableActions(List<Action> availableActions) {
		this.availableActions = availableActions;
	}

	/**
	 * Gets the hazards at the current location.
	 * 
	 * @return the hazards at the current location
	 */
	public List<Hazard> getHazards() {
		return this.hazards;
	}

	/**
	 * Sets the hazards at the current location.
	 * 
	 * @param hazards the hazards to set
	 */
	public void setHazards(List<Hazard> hazards) {
		this.hazards = hazards;
	}

	/**
	 * Adds the hazard
	 * 
	 * @param hazard the hazard to add
	 */
	public void addHazard(Hazard hazard) {
		this.hazards.add(hazard);
	}

	/**
	 * Gets the connected locations from the current location
	 * 
	 * @return the connectedLocations from the current location
	 */
	public Map<Direction, Location> getConnectedLocations() {
		return this.connectedLocations;
	}

	/**
	 * Adds a connected location in a specified direction.
	 * 
	 * @param direction the direction of the connected location
	 * @param location  the connected location
	 */
	public void addConnectedLocation(Direction direction, Location location) {
		if (!this.connectedLocations.containsKey(direction)) {
			this.connectedLocations.put(direction, location);
			this.availableActions
					.add(new Action(direction.name(), "Move to " + location.getName().toLowerCase(), location));
		}
	}

}
