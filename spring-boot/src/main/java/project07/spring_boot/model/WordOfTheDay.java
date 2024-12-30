package project07.spring_boot.model;

import java.time.LocalDate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WordOfTheDay {
    private String word;
    private boolean canBeDaily;
    private LocalDate dateUsed;
    
    // Default constructor
    public WordOfTheDay() {
    }

    // Constructor with parameters
    public WordOfTheDay(String word, boolean canBeDaily, LocalDate dateUsed) {
        this.word = word;
        this.canBeDaily = canBeDaily;
        this.dateUsed = dateUsed;
    }

    // Getter and Setter Methods
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isCanBeDaily() {
        return canBeDaily;
    }

    public void setCanBeDaily(boolean canBeDaily) {
        this.canBeDaily = canBeDaily;
    }

    public LocalDate getDateUsed() {
        return dateUsed;
    }

    public void setDateUsed(LocalDate dateUsed) {
        this.dateUsed = dateUsed;
    }
}
