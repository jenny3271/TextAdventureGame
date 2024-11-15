package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.WorldLoader;

class TestGameManager {

	@Test
    public void testInitialLocation() {
        GameManager gameManager = new GameManager();
        assertNotNull(gameManager.getCurrentLocation(), "Current location should not be null");
        assertEquals("Start", gameManager.getCurrentLocation().getName());
    }

	@Test
	public void testMoveToLocation() {
	    GameManager gameManager = new GameManager();
	    gameManager.moveToLocation(Direction.NORTH);
	    assertEquals("Forest", gameManager.getCurrentLocation().getName());

	    gameManager.moveToLocation(Direction.SOUTH);
	    assertEquals("Start", gameManager.getCurrentLocation().getName());
	}

    @Test
    public void testInvalidMove() {
        GameManager gameManager = new GameManager();
        gameManager.moveToLocation(Direction.WEST);
        assertEquals("Start", gameManager.getCurrentLocation().getName());
    }

    @Test
    public void testGetLocations() {
        GameManager gameManager = new GameManager();
        Map<String, Location> locations = gameManager.getLocations();
        assertNotNull(locations);
        assertTrue(locations.containsKey("Start"));
        assertTrue(locations.containsKey("Forest"));
        assertTrue(locations.containsKey("Cave"));
        assertTrue(locations.containsKey("River"));
        assertTrue(locations.containsKey("Mountain"));
        assertTrue(locations.containsKey("Village"));
    }
    
    @Test
    public void testLoadWorldInformation() throws IOException {
        WorldLoader loader = new WorldLoader();
        Map<String, Location> locations = loader.loadWorld("resources/world.txt");
        GameManager gameManager = new GameManager();
        gameManager.loadWorldInformation("resources/world.txt");
        assertEquals(locations.size(), gameManager.getLocations().size());
    }
    
    @Test
    public void testLoadWorldInformationIOException() {
        GameManager gameManager = new GameManager();
        gameManager.loadWorldInformation("invalid");
        assertNull(gameManager.getCurrentLocation(), "Current location should be null due to IOException");
    }
}
