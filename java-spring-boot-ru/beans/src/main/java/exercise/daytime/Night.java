package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class Night implements Daytime {
    private String name = "night";
    private String initMessage = "Bean Night has been initialized";

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
