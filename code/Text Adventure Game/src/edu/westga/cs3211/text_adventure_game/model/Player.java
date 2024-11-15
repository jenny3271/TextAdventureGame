package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the text adventure game. Manages the player's health,
 * status, and inventory.
 * 
 * @author Jennifer Alvarez
 * @version Fall 2024
 */
public class Player {
	private static final int INITIAL_HEALTH = 100;
	private int health;
	private PlayerStatus status;
	private Location currentLocation;
	private List<String> inventory;

	/**
	 * Constructs a new Player with the initial health.
	 */
	public Player() {
		this.health = INITIAL_HEALTH;
		this.status = PlayerStatus.ALIVE;
		this.inventory = new ArrayList<>();
	}

	/**
	 * Gets the current health of the player.
	 * 
	 * @return the current health
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * Sets the health of the player. If health is 0, the player's status is set to
	 * DEAD.
	 * 
	 * @param health the new health value
	 */
	public void setHealth(int health) {
		if (health < 0) {
			this.health = 0;
		} else {
			this.health = health;
		}
		if (this.health <= 0) {
			this.status = PlayerStatus.DEAD;
		}
	}

	/**
	 * Gets the current status of the player.
	 * 
	 * @return the current status
	 */
	public PlayerStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets the current location of the player.
	 * 
	 * @param newLocation the new location to set
	 */
	public void setCurrentLocation(Location newLocation) {
		this.currentLocation = newLocation;
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
	 * Gets the inventory of the player.
	 * 
	 * @return the inventory
	 */
	public List<String> getInventory() {
		return this.inventory;
	}

	/**
	 * Adds an item to the player's inventory.
	 * 
	 * @param item the item to add
	 */
	public void addItemToInventory(String item) {
		this.inventory.add(item);
	}

	/**
	 * Removes an item from the player's inventory.
	 * 
	 * @param item the item to remove
	 */
	public void removeItemFromInventory(String item) {
		this.inventory.remove(item);
	}

	/**
	 * Reduces the player's health by the specified amount. If health drops to 0 or
	 * below, the player's status is set to DEAD.
	 * 
	 * @param damage the amount of damage to take
	 */
	public void takeDamage(int damage) {
		if (damage < 0) {
			throw new IllegalArgumentException("Damage cannot be negative.");
		}
		this.setHealth(this.health - damage);
	}

	@Override
	public String toString() {
		return "Player{" + "health=" + this.health + System.lineSeparator() + "status=" + this.status
				+ System.lineSeparator() + "inventory=" + this.inventory + System.lineSeparator() + '}';
	}
}
