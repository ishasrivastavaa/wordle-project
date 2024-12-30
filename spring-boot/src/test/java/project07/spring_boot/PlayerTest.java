package project07.spring_boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/* Test class for class Player
 * 
 * @author Kelsey Fernandez
 */
public class PlayerTest {

    private Player player;
    private Guess mockGuess; // Mock object for Guess class
    private String name = "Kelsey";
    private String wordOfTheDay = "HELLO";

    @BeforeEach
    public void setUp() {
        // Create a mock of the Guess class
        mockGuess = mock(Guess.class);

        // Create a new Player instance
        player = new Player(name, wordOfTheDay);

        // Inject the mock Guess object into the Player instance
        player.setGuess(mockGuess);  // Assumes a setter in Player for testing purposes
    }

    /*
     * Basic test to ensure creation of the Player object
     * is functional.
     */
    @Test
    public void playerObjectCreation() {
        assertNotNull(player);
        assertEquals(name, player.getName());
        assertEquals(0, player.getAttempt());
        assertEquals(wordOfTheDay, player.getWordOfTheDay());
    }

    /*
     * Test to ensure making a guess increments the
     * guess count
     */
    @Test
    public void incrementAttemptReturnsCorrectNumberWhenGuessIsMade() {
        assertNotNull(player);
        assertEquals(0, player.getAttempt());

        player.makeGuess("slate");
        assertEquals(1, player.getAttempt());

        player.makeGuess("gusty");
        player.makeGuess("bread");
        assertEquals(3, player.getAttempt());

        player.makeGuess("bagel");
        player.makeGuess("sound");
        player.makeGuess("moist");
        assertEquals(6, player.getAttempt());
    }

    /*
     * Test to ensure when the user has guessed 6 words
     * an additional guess does not go through
     */
    @Test
    public void makeGuessDoesNotAllowGuessWhenGuessCountIsSix() {
        player.makeGuess("slate");
        player.makeGuess("gusty");
        player.makeGuess("bread");
        player.makeGuess("bagel");
        player.makeGuess("moist");
        player.makeGuess("sound");

        assertEquals(6, player.getAttempt());

        // Ensure that an additional guess does not increment the count
        player.makeGuess("hello");
        assertEquals(6, player.getAttempt());
    }
}

