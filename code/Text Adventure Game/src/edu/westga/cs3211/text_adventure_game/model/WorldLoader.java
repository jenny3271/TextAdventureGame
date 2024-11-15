package edu.westga.cs3211.text_adventure_game.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads the world information from a text file.
 * 
 * @author Jennifer Alvarez
 * @version Fall 2024
 */
public class WorldLoader {

    /**
     * Loads the world information from the specified file.
     * 
     * @param fileName the name of the file containing the world information
     * @return a map of location names to Location objects
     * @throws IOException if an I/O error occurs
     */
    public Map<String, Location> loadWorld(String fileName) throws IOException {
        Map<String, Location> locations = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String name = parts[0];
                String description = parts[1];
                Location location = new Location(name, description);
                locations.put(name, location);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String name = parts[0];
                Location location = locations.get(name);
                for (int info = 2; info < parts.length; info++) {
                    String[] directionAndLocation = parts[info].split(":");
                    if (directionAndLocation.length == 2) {
                        Direction direction = Direction.valueOf(directionAndLocation[0]);
                        Location connectedLocation = locations.get(directionAndLocation[1]);
                        location.addConnectedLocation(direction, connectedLocation);
                    } else if (directionAndLocation[0].equals("HAZARD")) {
                    	String hazardName = directionAndLocation[1];
                        int hazardDamage = Integer.parseInt(directionAndLocation[2]);
                        location.addHazard(new Hazard(hazardName, hazardDamage));
                        location.setHasHazard(true);
                    } else if (directionAndLocation[0].equals("GOAL")) {
                        location.setGoal(true);
                    }
                }
            }
        }
        return locations;
    }
}
