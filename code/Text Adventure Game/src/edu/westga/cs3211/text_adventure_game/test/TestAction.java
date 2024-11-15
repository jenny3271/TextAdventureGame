package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Player;

class TestAction {

	private Action action;

    @BeforeEach
    void setUp() {
        this.action = new Action("Look", "Look around the area.");
    }

    @Test
    void testConstructor() {
        assertEquals("Look", this.action.getName());
        assertEquals("Look around the area.", this.action.getDescription());
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
                new Action(name, description);
            });
            assertEquals(expectedMessage, thrown.getMessage());
        } else if (method.equals("setName")) {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                this.action.setName(name);
            });
            assertEquals(expectedMessage, thrown.getMessage());
        } else if (method.equals("setDescription")) {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                this.action.setDescription(description);
            });
            assertEquals(expectedMessage, thrown.getMessage());
        }
    }
    
    @Test
    void testSetName() {
        this.action.setName("Move");
        assertEquals("Move", this.action.getName());
    }

    @Test
    void testSetDescription() {
        this.action.setDescription("Move to a different location.");
        assertEquals("Move to a different location.", this.action.getDescription());
    }

    @Test
    void testExecute() {
    	Player player = new Player();
        this.action.execute(player);
    }
    
    @Test
    void testIsAvailable() {
        Player player = new Player();
        assertEquals(true, this.action.isAvailable(player));
    }

    @Test
    void testToString() {
        String expected = "Action{" + System.lineSeparator()
                + "name='Look" + System.lineSeparator()
                + ", description='Look around the area." + System.lineSeparator()
                + '}';
        assertEquals(expected, this.action.toString());
    }
}
