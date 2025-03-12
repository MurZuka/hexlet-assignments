package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method : address.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                System.out.println("Method " + method.getName() + " returns a value of type " + method.getType());
            }
        }
        // END
    }
}
