package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
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
    
    static Stream<Arguments> provideInvalidValues() {
        return Stream.of(
            Arguments.of("constructor", null, "Description", "Name cannot be null or empty."),
            Arguments.of("constructor", "Name", null, "Description cannot be null or empty."),
            Arguments.of("constructor", "", "Description", "Name cannot be null or empty."),
            Arguments.of("constructor", "Name", "", "Description cannot be null or empty."),
            Arguments.of("setName", null, "Description", "Name cannot be null or empty."),
            Arguments.of("setName", "", "Description", "Name cannot be null or empty."),
            Arguments.of("setDescription", "Name", null, "Description cannot be null or empty."),
            Arguments.of("setDescription", "Name", "", "Description cannot be null or empty.")
        );
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidValues")
    void testInvalidValues(String method, String name, String description, String expectedMessage) {
        if (method.equals("constructor")) {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                new Location(name, description);
            });
            assertEquals(expectedMessage, thrown.getMessage());
        } else if (method.equals("setName")) {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                this.location.setName(name);
            });
            assertEquals(expectedMessage, thrown.getMessage());
        } else if (method.equals("setDescription")) {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                this.location.setDescription(description);
            });
            assertEquals(expectedMessage, thrown.getMessage());
        }
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
        Action lookAction = new Action("Look", "Look around the area.");
        Action moveAction = new Action("Move", "Move to a different location.");
        List<Action> actions = Arrays.asList(lookAction, moveAction);
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
        Action lookAction = new Action("Look", "Look around the area.");
        Action moveAction = new Action("Move", "Move to a different location.");
        List<Action> actions = Arrays.asList(lookAction, moveAction);
        this.location.setAvailableActions(actions);
        Location forest = new Location("Forest", "A dense forest.");
        this.location.addConnectedLocation(Direction.NORTH, forest);

        String expected = "Location{" +
                "name='Start" + System.lineSeparator() +
                "description='This is the starting location." + System.lineSeparator() +
                "hasHazard=true" + System.lineSeparator() +
                "isGoal=true" + System.lineSeparator() +
                "connectedLocations=[NORTH]" + System.lineSeparator() +
                "availableActions=[" + lookAction.toString() + ", " + moveAction.toString() + "]" +
                '}';

        assertEquals(expected, this.location.toString());
    }
    
    @Test
    void testSetHazards() {
        Hazard fire = new Hazard("Fire", 20);
        Hazard flood = new Hazard("Flood", 15);
        List<Hazard> hazards = List.of(fire, flood);

        this.location.setHazards(hazards);

        assertEquals(2, this.location.getHazards().size(), "There should be 2 hazards.");
        assertTrue(this.location.getHazards().contains(fire), "The hazards should contain Fire.");
        assertTrue(this.location.getHazards().contains(flood), "The hazards should contain Flood.");
    }
}
