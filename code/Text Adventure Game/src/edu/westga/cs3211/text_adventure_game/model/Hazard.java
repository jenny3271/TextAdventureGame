package edu.westga.cs3211.text_adventure_game.model;

/**
 * The Hazard class represents a danger or obstacle in the game. Each hazard has
 * a name and a damage value.
 * 
 * @author Jennifer Alvarez
 * @version Fall 2024
 */
public class Hazard {
	private String name;
	private int damage;

	/**
	 * Constructs a new Hazard with the specified name and damage.
	 *
	 * @param name   the name of the hazard
	 * @param damage the damage value of the hazard
	 * @throws IllegalArgumentException if name is null or empty, or if damage is
	 *                                  negative
	 */
	public Hazard(String name, int damage) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty.");
		}
		if (damage < 0) {
			throw new IllegalArgumentException("Damage cannot be negative.");
		}
		this.name = name;
		this.damage = damage;
	}

	/**
	 * Gets the name of the hazard.
	 *
	 * @return the name of the hazard
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the hazard.
	 *
	 * @param name the new name of the hazard
	 * @throws IllegalArgumentException if name is null or empty
	 */
	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty.");
		}
		this.name = name;
	}

	/**
	 * Gets the damage value of the hazard.
	 *
	 * @return the damage value of the hazard
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Sets the damage value of the hazard.
	 *
	 * @param damage the new damage value of the hazard
	 * @throws IllegalArgumentException if damage is negative
	 */
	public void setDamage(int damage) {
		if (damage < 0) {
			throw new IllegalArgumentException("Damage cannot be negative.");
		}
		this.damage = damage;
	}

	/**
	 * Applies the hazard to a player, reducing their health by the damage value of
	 * the hazard.
	 *
	 * @param player the player to apply the hazard to
	 */
	public void applyTo(Player player) {
		player.setHealth(player.getHealth() - this.damage);
	}

	@Override
	public String toString() {
		return "Hazard{" + "name='" + this.name + System.lineSeparator() + ", damage=" + this.damage
				+ System.lineSeparator() + '}';
	}
}
