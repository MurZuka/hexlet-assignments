package exercise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import exercise.Application;

import static exercise.Application.currentDayTime;

// BEGIN
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping
    public String welcome() {
        String currTimeName = currentDayTime().getName();

        return "It is " + currTimeName + " now! Welcome to Spring!";
    }
}
// END
