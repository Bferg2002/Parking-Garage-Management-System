package AppUserConstruction;

import VehicleSorting.Car;
import VehicleSorting.SUV;
import VehicleSorting.Vehicle;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Test
    void constructorAndAccessors() {
        User user = new User("Jordan", "j@example.com");
        assertEquals("Jordan", user.getName());
        assertEquals("j@example.com", user.getEmail());
        assertTrue(user.getVehicles().isEmpty());

        user.setName("Casey");
        user.setEmail("c@example.com");
        assertEquals("Casey", user.getName());
        assertEquals("c@example.com", user.getEmail());
    }

    @Test
    void registerAndRemoveVehicle() {
        User user = new User("A", "a@example.com");
        Car car = new Car("P-1", "M", "B", 2020, "Red");
        user.registerVehicle(car);
        assertEquals(1, user.getVehicles().size());
        user.removeVehicleFromAccount(car);
        assertTrue(user.getVehicles().isEmpty());
    }

    @Test
    void updateVehicleInfoIsCallable() {
        User user = new User("A", "a@example.com");
        Car car = new Car("P-1", "M", "B", 2020, "Red");
        user.updateVehicleInfo(car, "NEW", "Blue");
    }

    @Test
    void viewAllRegisteredVehiclesPrintsDetails() {
        User user = new User("A", "a@example.com");
        user.registerVehicle(new SUV("SUV-1", "X5", "BMW", 2023, "Gray"));

        String out = captureStdout(user::viewAllRegisteredVehicles);
        assertTrue(out.contains("BMW"));
        assertTrue(out.contains("X5"));
        assertTrue(out.contains("2023"));
    }

    private static String captureStdout(Runnable action) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
