package AppUserConstruction;

import VehicleSorting.Car;
import VehicleSorting.Vehicle;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeTest {

    @Test
    void isRegisteredReturnsTrueWhenPlateMatches() {
        Employee emp = new Employee("Alex");
        User user = new User("Pat", "pat@example.com");
        Vehicle car = new Car("ABC-123", "Model3", "Tesla", 2024, "Black");
        user.registerVehicle(car);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        assertTrue(emp.isRegistered(new Car("ABC-123", "Other", "Other", 2020, "Red"), users));
    }

    @Test
    void isRegisteredReturnsFalseWhenNoMatch() {
        Employee emp = new Employee("Alex");
        User user = new User("Pat", "pat@example.com");
        user.registerVehicle(new Car("XYZ-999", "Civic", "Honda", 2021, "Blue"));

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        assertFalse(emp.isRegistered(new Car("NOPE-1", "Civic", "Honda", 2021, "Blue"), users));
    }

    @Test
    void isRegisteredReturnsFalseForEmptyUserList() {
        Employee emp = new Employee("Alex");
        assertFalse(emp.isRegistered(new Car("ABC-123", "A", "B", 2020, "Red"), new ArrayList<>()));
    }

    @Test
    void checkVehicleRegistrationUsesCashRegisterForRegistered() {
        Employee emp = new Employee("Alex");
        User user = new User("Pat", "pat@example.com");
        Vehicle car = new Car("REG-1", "Leaf", "Nissan", 2022, "Green");
        user.registerVehicle(car);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        String out = captureStdout(() -> emp.checkVehicleRegistration(car, users));
        assertTrue(out.contains("REG-1"));
        assertTrue(out.contains("Fee:"));
    }

    @Test
    void checkVehicleRegistrationUsesCashRegisterForUnregistered() {
        Employee emp = new Employee("Alex");
        Vehicle car = new Car("UNREG-1", "Leaf", "Nissan", 2022, "Green");

        String out = captureStdout(() -> emp.checkVehicleRegistration(car, new ArrayList<>()));
        assertTrue(out.contains("UNREG-1"));
        assertTrue(out.contains("Fine:"));
    }

    @Test
    void gettersAndSetters() {
        Employee emp = new Employee("Sam");
        assertEquals("Sam", emp.getName());
        emp.setName("Taylor");
        assertEquals("Taylor", emp.getName());
    }

    @Test
    void isRegisteredChecksAllUsers() {
        Employee emp = new Employee("Alex");
        User u1 = new User("A", "a@x.com");
        User u2 = new User("B", "b@x.com");
        u2.registerVehicle(new Car("FIND-ME", "M", "B", 2020, "Red"));
        ArrayList<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        assertTrue(emp.isRegistered(new Car("FIND-ME", "X", "Y", 2021, "Blue"), users));
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
