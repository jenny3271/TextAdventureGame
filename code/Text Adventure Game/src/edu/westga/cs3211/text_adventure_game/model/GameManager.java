package edu.westga.cs3211.text_adventure_game.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private List<Action> actions;
	private Player player;

	/**
	 * Constructs a new GameManager and initializes the locations.
	 */
	public GameManager() {
		this.locations = new HashMap<>();
		this.actions = new ArrayList<>();
		this.player = new Player();
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
			this.extractActions();
		} catch (IOException exception) {
			System.out.print(exception.getMessage());
			this.currentLocation = null;
		}
	}

	/**
	 * Sets the current location of the player.
	 * 
	 * @param newLocation the new location to set
	 */
	public void setCurrentLocation(Location newLocation) {
		this.currentLocation = newLocation;
		this.checkForHazards();
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
	 * Gets the player.
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
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
			this.checkForHazards();
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

	/**
	 * Gets all the actions available in the game.
	 * 
	 * @return a list of all actions
	 */
	public List<Action> getActions() {
		return this.actions;
	}

	/**
	 * Executes an action at the current location.
	 * 
	 * @param action the action to perform
	 */
	public void performAction(Action action) {
		if (this.currentLocation.getAvailableActions().contains(action)) {
			action.execute(this.player);
			if (action.getNewLocation() != null) {
				this.setCurrentLocation(action.getNewLocation());
			}
		}
	}

	/**
	 * Checks if the current location has a hazard and applies it to the player.
	 */
	public void checkForHazards() {
		Location currentLocation = this.getCurrentLocation();
		List<Hazard> hazards = currentLocation.getHazards();
		Player player = this.getPlayer();

		for (Hazard hazard : hazards) {
			player.setHealth(player.getHealth() - hazard.getDamage());
		}
	}

	/**
	 * Checks if the game is over.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		return this.player.getStatus() == PlayerStatus.DEAD || this.currentLocation.isGoal();
	}

	/**
	 * Restarts the game from the beginning.
	 */
	public void restartGame() {
		this.player = new Player();
		this.currentLocation = this.locations.get("Start");
	}

	/**
	 * Extracts actions from the loaded locations.
	 */
	private void extractActions() {
		for (Location location : this.locations.values()) {
			if (location.getAvailableActions() != null) {
				this.actions.addAll(location.getAvailableActions());
			}
		}
	}
}
