package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Player;
import edu.westga.cs3211.text_adventure_game.model.PlayerStatus;

public class TestPlayer {
	private Player player;

    @BeforeEach
    void setUp() {
        this.player = new Player();
    }

    @Test
    void testConstructor() {
        assertEquals(100, this.player.getHealth());
        assertEquals(PlayerStatus.ALIVE, this.player.getStatus());
        assertNotNull(this.player.getInventory());
        assertTrue(this.player.getInventory().isEmpty());
    }
    
    @Test
    void testSetHealth() {
        this.player.setHealth(50);
        assertEquals(50, this.player.getHealth());
        assertEquals(PlayerStatus.ALIVE, this.player.getStatus());

        this.player.setHealth(0);
        assertEquals(0, this.player.getHealth());
        assertEquals(PlayerStatus.DEAD, this.player.getStatus());
    }
    
    @Test
    void testSetHealthNegative() {
        this.player.setHealth(-10);
        assertEquals(0, this.player.getHealth());
        assertEquals(PlayerStatus.DEAD, this.player.getStatus());
    }

    @Test
    void testAddItemToInventory() {
        this.player.addItemToInventory("Sword");
        assertEquals(1, this.player.getInventory().size());
        assertTrue(this.player.getInventory().contains("Sword"));
    }

    @Test
    void testRemoveItemFromInventory() {
        this.player.addItemToInventory("Shield");
        this.player.removeItemFromInventory("Shield");
        assertTrue(this.player.getInventory().isEmpty());
    }
    
    @Test
    void testTakeActionAvailable() {
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

        this.player.takeActionAvailable(mockAction);
        assertTrue(this.player.getInventory().contains("Mock Item"));
    }
    
    @Test
    void testTakeActionNotAvailable() {
        Action mockAction = new Action("Mock Action", "This is a mock action.") {
            @Override
            public void execute(Player player) {
                player.addItemToInventory("Mock Item");
            }

            @Override
            public boolean isAvailable(Player player) {
                return false;
            }
        };

        this.player.takeActionAvailable(mockAction);
        assertFalse(this.player.getInventory().contains("Mock Item"));
    }
    
    @Test
    void testTakeDamage() {
        this.player.takeDamage(30);
        assertEquals(70, this.player.getHealth());
        assertEquals(PlayerStatus.ALIVE, this.player.getStatus());

        this.player.takeDamage(70);
        assertEquals(0, this.player.getHealth());
        assertEquals(PlayerStatus.DEAD, this.player.getStatus());
    }

    @Test
    void testTakeDamageNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.player.takeDamage(-10);
        });
    }
    
    @Test
    void testToString() {
        this.player.addItemToInventory("Sword");
        this.player.addItemToInventory("Shield");
        String expectedString = "Player{" + 
                                "health=100" + System.lineSeparator() + 
                                "status=ALIVE" + System.lineSeparator() + 
                                "inventory=[Sword, Shield]" + System.lineSeparator() + 
                                '}';
        assertEquals(expectedString, this.player.toString());
    }
}
