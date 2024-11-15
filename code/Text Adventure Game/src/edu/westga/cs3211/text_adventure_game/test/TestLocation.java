package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Location;

class TestLocation {

	private Location location;

    @BeforeEach
    void setUp() {
        this.location = new Location("Start", "This is the starting location.");
    }

    @Test
    void testConstructor() {
        assertEquals("Start", this.location.getName());
        assertEquals("This is the starting location.", this.location.getDescription());
        assertFalse(this.location.isHasHazard());
        assertFalse(this.location.isGoal());
        assertNotNull(this.location.getConnectedLocations());
        assertTrue(this.location.getConnectedLocations().isEmpty());
    }
    
    @Test
    void testSetName() {
        this.location.setName("New Start");
        assertEquals("New Start", this.location.getName());
    }

    @Test
    void testSetDescription() {
        this.location.setDescription("New description");
        assertEquals("New description", this.location.getDescription());
    }

    @Test
    void testSetHasHazard() {
        this.location.setHasHazard(true);
        assertTrue(this.location.isHasHazard());
    }
    
    @Test
    void testSetGoal() {
        this.location.setGoal(true);
        assertTrue(this.location.isGoal());
    }

    @Test
    void testSetAvailableActions() {
        List<String> actions = Arrays.asList("Look", "Move");
        this.location.setAvailableActions(actions);
        assertEquals(actions, this.location.getAvailableActions());
    }
    
    @Test
    void testAddConnectedLocation() {
        Location forest = new Location("Forest", "A dense forest.");
        this.location.addConnectedLocation(Direction.NORTH, forest);
        Map<Direction, Location> connectedLocations = this.location.getConnectedLocations();
        assertEquals(1, connectedLocations.size());
        assertEquals(forest, connectedLocations.get(Direction.NORTH));
    }
    
    @Test
    void testToString() {
        this.location.setHasHazard(true);
        this.location.setGoal(true);
        List<String> actions = Arrays.asList("Look", "Move");
        this.location.setAvailableActions(actions);
        Location forest = new Location("Forest", "A dense forest.");
        this.location.addConnectedLocation(Direction.NORTH, forest);

        String expected = "Location{" +
                "name='Start" + System.lineSeparator() +
                "description='This is the starting location." + System.lineSeparator() +
                "hasHazard=true" + System.lineSeparator() +
                "isGoal=true" + System.lineSeparator() +
                "connectedLocations=[NORTH]" + System.lineSeparator() +
                "availableActions=[Look, Move]" +
                '}';

        assertEquals(expected, this.location.toString());
    }
}
