package project07.spring_boot;
/* class Player creates a Player object that will ... 
 * 
 * @author Kelsey Fernandez
 */

 public class Player{

    // A string for the name of the player.
    private String name; 
    // An Guess object to keep track of the players guessed word.
    private Guess guess;
    // A String to keep track of the word.
    private String wordOfTheDay;
    // A counter variable to keep track of the number of tries the player has guessed a word.
    private int currentAttempt;

    /* Constructor of a Player object. Takes the players 
     * name and sets the name variable to that value. Sets 
     * the currentAttempt to 0.
     * 
     * @param name: String representation of the
     * players name
     * @param wordOfTheDay: String representation of the
     * word of the day
     */
    public Player(String name, String wordOfTheDay){
        this.name = name;
        this.currentAttempt = 0;
        this.wordOfTheDay = wordOfTheDay.toUpperCase();
        this.guess = new Guess(this.wordOfTheDay.toCharArray());
    }

     /* function to return the player name variable
      *
      * @return String of the player's name
      */
     public String getName(){
         return this.name;
     }

    /* function to return the currentAttempt variable
     * 
     * @return int of the number of guesses a player
     * has guessed.
     */

    public int getAttempt(){
        return this.currentAttempt;
    }

    /* 
     * function to increment the currentAttempt variable 
     */
    public void incrementAttempt(){
        this.currentAttempt++;
    }

     /*
      * function to return the wordOfTheDay variable
      *
      * @return String of the word of the day
      */
     public String getWordOfTheDay(){
         return this.wordOfTheDay;
     }

     /*
      * function to return the Guess class
      *
      * @return Guess object
      */
     public Guess getGuess(){
         return this.guess;
     }

     /*
      * setter method to your Player class for testing purposes
      */
     public void setGuess(Guess guess) {
         this.guess = guess;
     }

     /*
     * function to make a guess by the player 
     */
    public void makeGuess(String guess){
        if(this.currentAttempt < 6){
            this.guess.checkGuess(guess.toCharArray(), this.currentAttempt);
            incrementAttempt();
        }
    }
 
}
