package project07.spring_boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
/* Test class for class Guess
 * 
 * @author Kelly Ploszaj, Mason DeCora
 */
public class GuessTest {

    private Guess guess;
    private char[] testWord = {'h', 'o', 'u', 's', 'e'};

    @BeforeEach
    public void setUp() {
        guess = new Guess(testWord);
    }
    /* basic test to ensure creation of Guess object
     * is functional. 
     */
    @Test
    public void GuessObjectCreation() {
        char[] word = {'h', 'e', 'l', 'l', 'o'};
        Guess guess = new Guess(word);

        assertNotNull(guess);
        assertEquals(word, guess.getDailyWord());

    }

    @Test
    public void testGuessWhenAllLettersCorrect() {
        guess.checkGuess(new char[]{'h', 'o', 'u', 's', 'e'}, 0);

        assertArrayEquals(new char[]{'h','o','u','s','e'}, guess.getGuess(0));
        assertArrayEquals(new int[]{3, 3, 3, 3, 3}, guess.getCorrectness()[0]);
    }

    @Test
    public void testGuessWhenNoLettersCorrect() {
        guess.checkGuess(new char[]{'f', 'i', 'n', 'c', 'z'}, 0);

        assertArrayEquals(new char[]{'f', 'i', 'n', 'c', 'z'}, guess.getGuessedLetters()[0]);
        assertArrayEquals(new int[]{1, 1, 1, 1, 1}, guess.getCorrectness()[0]);
    }

    @Test
    public void testGuessWhenSomeLettersCorrect() {
        guess.checkGuess(new char[]{'h', 'a', 's', 'a', 'e'}, 0);

        assertArrayEquals(new int[]{3, 1, 2, 1, 3}, guess.getCorrectness()[0]);
    }

    @Test
    public void testCorrectnessWhenNewGuess() {
        guess.checkGuess(new char[]{'h', 'a', 's', 'a', 'e'}, 0);
        assertArrayEquals(new int[]{3, 1, 2, 1, 3}, guess.getCurrCorrectness(0));

        guess.checkGuess(new char[]{'h', 'o', 's', 'a', 'e'}, 1);

        assertArrayEquals(new int[]{3,3,2,1,3}, guess.getCorrectness()[1]);
    }

    /**
     * Tests that a letter is displayed as black/0 when all instances of it in the word
     * have already been guessed
     */
    @Test
    public void testCorrectnessWhenLetterAlreadyCorrect() {
        guess.checkGuess(new char[]{'e', 'o', 'u', 's', 'e'}, 0);
        assertArrayEquals(new int[]{1,3,3,3,3}, guess.getCurrCorrectness(0));

        Guess testWord2 = new Guess(new char[]{'s', 't', 'a', 's', 'h'});
        testWord2.checkGuess(new char[]{'b', 'o', 's', 's', 'y'}, 0);
        assertArrayEquals(new int[]{1,1,2,3,1}, testWord2.getCurrCorrectness(0));

        Guess testWord3 = new Guess(new char[]{'p', 'l', 'u', 's', 'h'});
        testWord3.checkGuess(new char[]{'s', 'l', 'u', 's', 'h'}, 0);
        assertArrayEquals(new int[]{1,3,3,3,3}, testWord3.getCurrCorrectness(0));

        Guess testWord4 = new Guess(new char[]{'t', 'e', 'r', 'r', 'a'});
        testWord4.checkGuess(new char[]{'t', 'w', 'i', 's', 't'}, 0);
        assertArrayEquals(new int[]{3,1,1,1,1}, testWord4.getCurrCorrectness(0));

        Guess testWord5 = new Guess(new char[]{'t', 'e', 'r', 'r', 'a'});
        testWord5.checkGuess(new char[]{'r', 'e', 'r', 'u', 'n'}, 0);
        assertArrayEquals(new int[]{2,3,3,1,1}, testWord5.getCurrCorrectness(0));
    }

    /**
     * Tests the case when there are two ore more of the same letter and one of them is green and another should be yellow
     */
    @Test
    public void testCorrectnessAlgoWithDoubleLetters() {
        Guess testDoubleLetterWord = new Guess(new char[]{'t', 'e', 'r', 'r', 'a'});
        testDoubleLetterWord.checkGuess(new char[]{'r', 'e', 'r', 'u', 'n'}, 0);
        assertArrayEquals(new int[]{2,3,3,1,1}, testDoubleLetterWord.getCurrCorrectness(0));

        Guess testDoubleWord2 = new Guess(new char[]{'s', 'e', 'v', 'e', 'r'});
        testDoubleWord2.checkGuess(new char[]{'s', 'e', 'v', 'e', 'r'}, 0);
        assertArrayEquals(new int[]{3,3,3,3,3}, testDoubleWord2.getCurrCorrectness(0));
    }

    /* checks if hashmap properly adds keys to the correct values. also ensures
     * getKeysByValue() functions correctly.
     */
    @Test
    public void testHashMap() {
        List<Character> keys = new ArrayList<>();
        keys.add('h');
        keys.add('o');
        keys.add('u');
        keys.add('s');
        keys.add('e');
        guess.checkGuess(new char[]{'h', 'o', 'u', 's', 'e'}, 0);
        System.out.println(guess.getKeysByValue(3));
        assertTrue(guess.getKeysByValue(3).containsAll(keys));

        List<Character> keys2 = new ArrayList<>();
        keys2.add('a');
        guess.checkGuess(new char[]{'h', 'o', 's', 'a', 'e'}, 1);
        assertTrue(guess.getKeysByValue(1).containsAll(keys2));
        assertTrue(guess.getKeysByValue(3).containsAll(keys));

    }
}
