package edu.westga.cs3211.text_adventure_game.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * GameManager class that handles all the logic regarding the Text Adventure
 * Game
 * 
 * @author Jennifer Alvarez
 * @version Fall 2024
 */
public class GameManager {
	private Location currentLocation;
	private Map<String, Location> locations;

	/**
	 * Constructs a new GameManager and initializes the locations.
	 */
	public GameManager() {
		this.locations = new HashMap<>();
		this.loadWorldInformation("resources/world.txt");
	}

	/**
	 * Loads the world information from a text file.
	 * 
	 * @param fileName the name of the file containing the world information
	 */
	public void loadWorldInformation(String fileName) {
		WorldLoader loader = new WorldLoader();
		try {
			this.locations = loader.loadWorld(fileName);
			this.currentLocation = this.locations.get("Start");
		} catch (IOException exception) {
			System.out.print(exception.getMessage());
			this.currentLocation = null;
		}
	}

	/**
	 * Gets the current location of the player.
	 * 
	 * @return the current location
	 */
	public Location getCurrentLocation() {
		return this.currentLocation;
	}

	/**
	 * Moves the player to a new location based on the specified direction.
	 * 
	 * @param direction the direction to move
	 */
	public void moveToLocation(Direction direction) {
		Location newLocation = this.currentLocation.getConnectedLocations().get(direction);
		if (newLocation != null) {
			this.currentLocation = newLocation;
		}
	}

	/**
	 * Gets all the locations in the game.
	 * 
	 * @return a map of all locations
	 */
	public Map<String, Location> getLocations() {
		return this.locations;
	}
}
