package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Player;
import edu.westga.cs3211.text_adventure_game.model.PlayerStatus;

class TestHazard {

	private Hazard hazard;

    @BeforeEach
    void setUp() {
        this.hazard = new Hazard("Fire", 20);
    }

    @Test
    void testConstructor() {
        assertEquals("Fire", this.hazard.getName());
        assertEquals(20, this.hazard.getDamage());
    }
    
    static Stream<Arguments> provideInvalidValues() {
        return Stream.of(
            Arguments.of("constructor", null, 20, "Name cannot be null or empty."),
            Arguments.of("constructor", "", 20, "Name cannot be null or empty."),
            Arguments.of("constructor", "Fire", -10, "Damage cannot be negative."),
            Arguments.of("setName", null, 20, "Name cannot be null or empty."),
            Arguments.of("setName", "", 20, "Name cannot be null or empty."),
            Arguments.of("setDamage", "Fire", -5, "Damage cannot be negative.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidValues")
    void testInvalidValues(String method, String name, int damage, String expectedMessage) {
        if (method.equals("constructor")) {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                new Hazard(name, damage);
            });
            assertEquals(expectedMessage, thrown.getMessage());
        } else if (method.equals("setName")) {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                this.hazard.setName(name);
            });
            assertEquals(expectedMessage, thrown.getMessage());
        } else if (method.equals("setDamage")) {
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                this.hazard.setDamage(damage);
            });
            assertEquals(expectedMessage, thrown.getMessage());
        }
    }

    @Test
    void testSetName() {
        this.hazard.setName("Ice");
        assertEquals("Ice", this.hazard.getName());
    }

    @Test
    void testSetDamage() {
        this.hazard.setDamage(30);
        assertEquals(30, this.hazard.getDamage());
    }

    @Test
    void testApplyTo() {
        Player player = new Player();
        this.hazard.applyTo(player);
        assertEquals(80, player.getHealth());
        assertEquals(PlayerStatus.ALIVE, player.getStatus());

        this.hazard.setDamage(100);
        this.hazard.applyTo(player);
        assertEquals(0, player.getHealth());
        assertEquals(PlayerStatus.DEAD, player.getStatus());
    }

    @Test
    void testToString() {
        String expected = "Hazard{" +
                "name='Fire" + System.lineSeparator() +
                ", damage=20" + System.lineSeparator() +
                '}';
        assertEquals(expected, this.hazard.toString());
    }
}
