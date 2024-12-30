package project07.spring_boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import project07.spring_boot.Player;
import project07.spring_boot.model.WordOfTheDay;
import project07.spring_boot.repo.WordRepo;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class HelloController {

    @Autowired
    private WordRepo wordRepo;
    private Player user;
    int count = 0;

    @PostConstruct
    public void initUser() {
        WordOfTheDay word = wordRepo.getRandomWord();
        user = new Player("user", word.getWord());
    }

    /**
     * Controller to get a random daily word each day
     * and send it to the frontend
     *
     * @return String of a new random dailyWord
     */
    @GetMapping("/dailyWord")
    public String getDailyWord() {
        String word = user.getWordOfTheDay();
        

        return word;
    }

    /**
     * Controller to make sure a word is valid (not
     * just random letters and a real word that could
     * be guessed). This also makes the guess to store
     * the result in our correctness and letters array
     * once we make sure that the guess is acceptable.
     *
     * @return ResponseEntity of a boolean returning if the guess is valid
     */
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/validate")
    public ResponseEntity validateWord(@RequestParam String word) {
        boolean isValid = wordRepo.isValidWord(word);
        
        if(isValid){
            user.makeGuess(word);
        }
        
        return ResponseEntity.ok(isValid); // Return true if word is not null, otherwise false
    }

    /**
     * Controller to get the correctness array
     * and send it to the frontend
     *
     * @return int[][] of the correctness color grid
     */
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/correctness")
    public int[][] correctness() {
        return user.getGuess().getCorrectness();
    }

    /**
     * Controller to get the letters array
     * and send it to the frontend
     *
     * @return char[][] of the letters grid
     */
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/letters")
    public char[][] letters() {
        return user.getGuess().getGuessedLetters();
    }

    /**
     * Controller to get the map of correctness
     * for each letter and send it to the frontend
     *
     * @return List<Character> of the correctness map
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/correctnessMap")
    public ResponseEntity<List<Character>> correctnessMap(@RequestParam int value) {
        try {
            List<Character> keys = user.getGuess().getKeysByValue(value);
            return ResponseEntity.ok(keys); // Ensure this returns a proper JSON array
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
