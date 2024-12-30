package project07.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import project07.spring_boot.repo.WordRepo;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        WordRepo repo = context.getBean(WordRepo.class);

        // Retrieve and print a single random word
        System.out.println(repo.getRandomWord().getWord());
    }
}
