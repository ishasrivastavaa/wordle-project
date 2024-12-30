package project07.spring_boot.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project07.spring_boot.model.WordOfTheDay;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
 * Repository for the WordOfTheDay class which handles getting data out of the database and inserting
 * it into a WordOfTheDay object that we can use
 */
@Repository
public class WordRepo {

    // Create the Spring Boot JDBC template object which handles opening the connection for us
    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Checks if the provided word exists in the database.
     * @param word The word to validate.
     * @return True if the word exists, false otherwise.
     */
    public boolean isValidWord(String word) {
        String sql = "SELECT COUNT(*) FROM word WHERE words = ?";
        Integer count = template.queryForObject(sql, new Object[]{word}, Integer.class);
        return count != null && count > 0;
    }

    /**
     * Fetch eligible words from the database.
     * @param limitDate The date before which a word is considered eligible (not recently used).
     * @return List of eligible WordOfTheDay objects.
     */
    public List<WordOfTheDay> getEligibleWords(LocalDate limitDate) {
        // The SQL statement to fetch words that can be daily and have not been used recently
        String sql = "SELECT * FROM word WHERE can_be_daily = TRUE AND (date_used < ? OR date_used IS NULL)";

        // Use JdbcTemplate to execute the query
        List<WordOfTheDay> words = template.query(sql, new Object[]{limitDate}, (rs, rowNum) -> mapWord(rs));

        return words;
    }

    /**
     * Update the date_used field of a word to mark it as recently used.
     * @param word The word to update.
     * @param dateUsed The date to set as date_used.
     */
    public void updateDateUsed(String word, LocalDate dateUsed) {
        String sql = "UPDATE word SET date_used = ? WHERE words = ?";
        template.update(sql, dateUsed, word);
    }

    /**
     * Helper method to map a ResultSet row to a WordOfTheDay object.
     * @param rs The ResultSet.
     * @return A WordOfTheDay object.
     * @throws SQLException If an SQL exception occurs.
     */
    private WordOfTheDay mapWord(ResultSet rs) throws SQLException {
        WordOfTheDay word = new WordOfTheDay();
        word.setWord(rs.getString(1));
        word.setCanBeDaily(rs.getBoolean("can_be_daily"));
        java.sql.Date sqlDateUsed = rs.getDate("date_used");
        if (sqlDateUsed != null) {
            word.setDateUsed(sqlDateUsed.toLocalDate());
        }
        return word;
    }

    /**
     * Fetch the word selected for today, if it exists.
     * @return A WordOfTheDay object for today's word, or null if not set.
     */
    private WordOfTheDay getTodayWord() {
        String sql = "SELECT * FROM word WHERE date_used = ?";
        List<WordOfTheDay> words = template.query(sql, new Object[]{LocalDate.now()}, (rs, rowNum) -> mapWord(rs));
        if (words.isEmpty()) {
            return null;
        }
        return words.get(0);
    }

    /**
     * Fetch a random eligible word and update its date_used.
     * Ensures the same word is returned for all users on the same day.
     * @return A WordOfTheDay object representing the selected word.
     */
    public WordOfTheDay getRandomWord() {
        // First, check if there is a word already selected for today
        WordOfTheDay todayWord = getTodayWord();
        if (todayWord != null) {
            return todayWord;
        }

        // No word selected for today, proceed to select a new one
        LocalDate limitDate = LocalDate.now().minusDays(30);

        List<WordOfTheDay> words = getEligibleWords(limitDate);

        if (words.isEmpty()) {
            throw new IllegalStateException("No eligible words available to select.");
        }

        Random random = new Random();
        WordOfTheDay selected = words.get(random.nextInt(words.size()));

        // Update date_used to today
        selected.setDateUsed(LocalDate.now());
        updateDateUsed(selected.getWord(), selected.getDateUsed());

        return selected;
    }
}
