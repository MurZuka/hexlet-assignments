package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class Application {
    @GetMapping("about")
    public String about() { return "Welcome to Hexlet!"; }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
