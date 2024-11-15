package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.Player;
import edu.westga.cs3211.text_adventure_game.model.WorldLoader;

class TestGameManager {

    private GameManager gameManager;

    @BeforeEach
    void setUp() {
        this.gameManager = new GameManager();
    }

    @Test
    void testInitialLocation() {
        assertNotNull(this.gameManager.getCurrentLocation(), "Current location should not be null");
        assertEquals("Start", this.gameManager.getCurrentLocation().getName());
    }

    @Test
    void testMoveToLocation() {
        this.gameManager.moveToLocation(Direction.NORTH);
        assertEquals("Forest", this.gameManager.getCurrentLocation().getName());

        this.gameManager.moveToLocation(Direction.SOUTH);
        assertEquals("Start", this.gameManager.getCurrentLocation().getName());
    }

    @Test
    void testInvalidMove() {
        this.gameManager.moveToLocation(Direction.WEST);
        assertEquals("Start", this.gameManager.getCurrentLocation().getName());
    }

    @Test
    void testGetLocations() {
        Map<String, Location> locations = this.gameManager.getLocations();
        assertNotNull(locations);
        assertTrue(locations.containsKey("Start"));
        assertTrue(locations.containsKey("Forest"));
        assertTrue(locations.containsKey("Cave"));
        assertTrue(locations.containsKey("River"));
        assertTrue(locations.containsKey("Mountain"));
        assertTrue(locations.containsKey("Village"));
    }

    @Test
    void testLoadWorldInformation() throws IOException {
        WorldLoader loader = new WorldLoader();
        Map<String, Location> locations = loader.loadWorld("resources/world.txt");
        this.gameManager.loadWorldInformation("resources/world.txt");
        assertEquals(locations.size(), this.gameManager.getLocations().size());
    }

    @Test
    void testLoadWorldInformationIOException() {
        this.gameManager.loadWorldInformation("invalid");
        assertNull(this.gameManager.getCurrentLocation(), "Current location should be null due to IOException");
    }

    @Test
    void testPerformAction() {
        Action mockAction = new Action("Mock Action", "This is a mock action.") {
            @Override
            public void execute(Player player) {
                player.addItemToInventory("Mock Item");
            }

            @Override
            public boolean isAvailable(Player player) {
                return true;
            }
        };

        this.gameManager.getCurrentLocation().setAvailableActions(List.of(mockAction));
        this.gameManager.performAction(mockAction);
        assertTrue(this.gameManager.getPlayer().getInventory().contains("Mock Item"));
    }

    @Test
    void testPerformActionNotAvailable() {
        Action unavailableAction = new Action("Unavailable Action", "This action is not available.") {
            @Override
            public void execute(Player player) {
                player.addItemToInventory("Unavailable Item");
            }

            @Override
            public boolean isAvailable(Player player) {
                return false;
            }
        };

        this.gameManager.getCurrentLocation().setAvailableActions(List.of());
        this.gameManager.performAction(unavailableAction);
        assertFalse(this.gameManager.getPlayer().getInventory().contains("Unavailable Item"));
    }

    @Test
    void testIsGameOver() {
        this.gameManager.getCurrentLocation().setGoal(true);
        assertTrue(this.gameManager.isGameOver());

        this.gameManager.getCurrentLocation().setGoal(false);
        this.gameManager.getPlayer().setHealth(0);
        assertTrue(this.gameManager.isGameOver());
    }
    
    @Test
    void testIsGameOverPlayerAliveAndNotGoal() {
        this.gameManager.getCurrentLocation().setGoal(false);
        this.gameManager.getPlayer().setHealth(100);
        assertFalse(this.gameManager.isGameOver());
    }

    @Test
    void testCheckForHazards() {
        this.gameManager.loadWorldInformation("resources/world.txt");
        this.gameManager.moveToLocation(Direction.NORTH);
        assertEquals(80, this.gameManager.getPlayer().getHealth());
    }

    @Test
    void testRestartGame() {
        this.gameManager.getPlayer().setHealth(50);
        this.gameManager.moveToLocation(Direction.NORTH);
        this.gameManager.restartGame();
        assertEquals(100, this.gameManager.getPlayer().getHealth(), "Player health should be reset to 100");
        assertEquals("Start", this.gameManager.getCurrentLocation().getName(), "Current location should be reset to Start");
    }
}
