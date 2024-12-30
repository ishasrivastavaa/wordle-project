package project07.spring_boot;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;


/* class Guess creates a Guess object that will manage the 
 * mechanics for guessing the Wordle word. 
 * 
 * @author Kelly Ploszaj, Mason DeCora
 */

public class Guess{

    // 2D array containing the value of correctness of previous guesses from guessedLetters. 
    // 1 means that the corresponding char in guessedLetters is not in the dailyWord, 
    // 2 means it is in the dailyWord but in the wrong position, 3 is in the word and correct position
    private int[][] correctness; 
    // 2D array of all guesses made for this current Guess object
    private char[][] guessedLetters;
    // daily word to be compared to guesses
    private char[] dailyWord;
    //contains each guessed word and its highest value (for visual of keyboard display).
    //for example, correctness only shows how correct each line is during its guess,
    //but keyboardVals shows the highest value it has been. It only adds keys that
    //have been guessed, i.e. 'a' will not be in the hashmap with a default value, and 
    //will only be added if it is a char within guessedLetters.
    private HashMap<Character, Integer> keyboardVals;

    /* Constructor of a Guess object. Takes a char[] 
     * of a daily word and sets the dailyWord variable
     * to that value.
     * 
     * @param dailyWord: char[] representation of a 
     * word to be guessed.
     */
    public Guess(char[] dailyWord){
        this.dailyWord = dailyWord;
        this.correctness = new int[6][5];
        this.guessedLetters = new char[6][5];
        this.keyboardVals = new HashMap<>();
    }

    /* function to return guessedLetters variable
     * 
     * @return char[][] of current guesses entered
     * in char format rather than in String format.
     * each
     */

    public char[][] getGuessedLetters(){
        return guessedLetters;
    }

    /* function to return the dailyWord variable 
     * 
     * @return char[] dailyWord for the Guess object
     */
    public char[] getDailyWord(){
        return dailyWord;
    }

    /* function to return the correctness variable 
     * 
     * @return int[][] correctness for the Guess object
     */
    public int[][] getCorrectness(){
        return correctness;
    }

    /* function to return the guess based on the attempt number provided
     * 
     * @param guessNum which is the guess number
     * @return char[] of the guess word
     */
    public char[] getGuess(int guessNum){

        return guessedLetters[guessNum];

    }

    /* function to return the correctness based on the attempt number provided
     * 
     * @param guessNum which is the guess number
     * @return int[] of the correctness of the current guess
     */
    public int[] getCurrCorrectness(int guessNum){

        return correctness[guessNum];

    }

    /*
     * 
     */
    public List<Character> getKeysByValue(int value) {
        List<Character> keys = new ArrayList<>();
        for (Entry<Character, Integer> entry : keyboardVals.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    /**
     * Takes the user's guess for the frontend and compares it to the daily word.
     * After the guess is checked, want to update the correctness array and guessedLetters
     * array with any new information and increment the guessCount
     * @param guess The guess that is given by the frontend from the user
     * @param guessNum The current guess number that the player is on, used to update our 2D arrays
     */
    public void checkGuess(char[] guess, int guessNum) {
        // Loop through each letter of the guess
        for (int i = 0; i < 5; i++) {
            // set the correctness of each letter to 1 by default since it had now been guessed
            correctness[guessNum][i] = 1;

            // for each letter, check each other letter in the dailyWord
            for (int j = 0; j < 5; j++) {
                // If the letters are in the same position and equal, update guessedLetters and correctness to 3 and break
                if ((i == j) && (guess[i]==dailyWord[j]) ) {
                    correctness[guessNum][i] = 3;

                    if(keyboardVals.containsKey(guess[i])){
                        if(keyboardVals.get(guess[i]) < 3){
                            keyboardVals.put(guess[i], 3);
                        }
                    }
                    else{
                        keyboardVals.put(guess[i], 3);
                    }
                    break;
                // Current guess letter is in the dailyWord but not in the right position,
                // update guessedLetters to 2

                } else if (guess[i]==dailyWord[j]) {
                    correctness[guessNum][i] = 2;

                    if(keyboardVals.containsKey(guess[i])){
                        if(keyboardVals.get(guess[i]) < 2){
                            keyboardVals.put(guess[i], 2);
                        }
                    }
                    else{
                        keyboardVals.put(guess[i], 2);
                    }

                // Current guess letter is not in the dailyword, keep its correctness at its current value    
                } else{

                    if(keyboardVals.containsKey(guess[i])){
                        if(keyboardVals.get(guess[i]) < 1){
                            keyboardVals.put(guess[i], 1);
                        }
                    }
                    else{
                        keyboardVals.put(guess[i], 1);
                    }
                }
            }
            // After each letter in the guess, put the character into guessedLetters[][]
            guessedLetters[guessNum][i] = guess[i];
        }
        // Then, after every letter of the guess has been checked, for each yellow letter
        // check if there are any other occurrences of that letter in dailyWord. If all other occurrences of
        // that letter are green (3), then set the correctness of that yellow letter to grey (1),
        // else leave it yellow (2) i.e. if there is an occurrence of yellowLetter that is 1 or 2, then
        // leave yellowLetter yellow (2)
        for (int i = 0; i < 5; i++) {
            if (correctness[guessNum][i] == 2) {
                char yellowLetter = guessedLetters[guessNum][i];
                boolean hasOtherYellowInstance = false;
                for (int j = 0; j < 5; j++) {
                    if (j == i) {
                        // exclude the letter that is yellow
                        continue;
                    } else {
                        // Make sure to change yellowLetter to grey only when all other instances are green
                        if (yellowLetter == dailyWord[j]) {
                            if ( ( correctness[guessNum][j] == 1  || correctness[guessNum][j] == 2 ) ) {
                                hasOtherYellowInstance = true;
                                break;
                            }
                        } else {
                            // do nothing, leave correctness as is
                        }
                    }
                }

                //
                if (!hasOtherYellowInstance) {
                    correctness[guessNum][i] = 1;
                }
            }
        }
    }
}
