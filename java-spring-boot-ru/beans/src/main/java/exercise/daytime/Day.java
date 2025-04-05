package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class Day implements Daytime {
    private String name = "day";
    private String initMessage = "Bean Day has been initialized";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println(initMessage);
    }
    // END
}
