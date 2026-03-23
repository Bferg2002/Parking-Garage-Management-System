package ParkingGarage;

import AppUserConstruction.User;
import VehicleSorting.Car;
import VehicleSorting.MotorBike;
import VehicleSorting.SUV;
import VehicleSorting.VehicleType;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingGarageTest {

    @Test
    void initialCapacityByType() {
        ParkingGarage garage = new ParkingGarage();
        assertEquals(50, garage.getAvailableSpaces(VehicleType.CAR));
        assertEquals(20, garage.getAvailableSpaces(VehicleType.SUV));
        assertEquals(10, garage.getAvailableSpaces(VehicleType.MOTORBIKE));
        assertEquals(0, garage.getOccupiedSpaces(VehicleType.CAR));
    }

    @Test
    void parkVehicleFindsSpotAndIncrementsOccupancy() {
        ParkingGarage garage = new ParkingGarage();
        Car car = new Car("G-1", "M", "B", 2020, "Red");
        String out = captureStdout(() -> garage.parkVehicle(car));
        assertTrue(out.contains("G-1"));
        assertTrue(out.contains("parked"));
        assertEquals(49, garage.getAvailableSpaces(VehicleType.CAR));
        assertEquals(1, garage.getOccupiedSpaces(VehicleType.CAR));
    }

    @Test
    void removeVehicleFindsByPlate() {
        ParkingGarage garage = new ParkingGarage();
        Car car = new Car("RM-1", "M", "B", 2020, "Red");
        garage.parkVehicle(car);
        captureStdout(() -> garage.removeVehicle("RM-1"));
        assertEquals(50, garage.getAvailableSpaces(VehicleType.CAR));
    }

    @Test
    void removeVehicleNotFoundPrintsMessage() {
        ParkingGarage garage = new ParkingGarage();
        String out = captureStdout(() -> garage.removeVehicle("MISSING"));
        assertTrue(out.contains("not found"));
    }

    @Test
    void parkVehicleNoSpotsWhenTypeFull() {
        ParkingGarage garage = new ParkingGarage();
        for (int i = 0; i < 50; i++) {
            garage.parkVehicle(new Car("C-" + i, "M", "B", 2020, "Red"));
        }
        String out = captureStdout(() -> garage.parkVehicle(new Car("OVERFLOW", "M", "B", 2020, "Blue")));
        assertTrue(out.contains("No available spots"));
    }

    @Test
    void parkVehicle_DuplicateVehiclePrintsWarning() {
        ParkingGarage garage = new ParkingGarage();
        SUV suv = new SUV("SV-1", "X5", "BMW", 2022, "Gray");

        garage.parkVehicle(suv);

        String out = captureStdout(() -> garage.parkVehicle(suv));

        assertTrue(out.contains("already parked"));
    }

    @Test
    void parkVehicle_NoAvailableSpots() {
        ParkingGarage garage = new ParkingGarage();

        // Fill all SUV spots (20 spots: index 50–69)
        for (int i = 0; i < 20; i++) {
            garage.parkVehicle(new SUV("SV-" + i, "X5", "BMW", 2022, "Gray"));
        }

        String out = captureStdout(() ->
                garage.parkVehicle(new SUV("FULL-1", "X5", "BMW", 2022, "Gray"))
        );

        assertTrue(out.contains("No available spots"));
    }

    @Test
    void parkVehicleWithHours_CoversMethod() {
        ParkingGarage garage = new ParkingGarage();
        SUV suv = new SUV("SV-100", "X5", "BMW", 2022, "Gray");

        garage.parkVehicleWithHours(suv, 4);

        assertEquals(4, garage.getHoursParked("SV-100"));
    }

    @Test
    void removeVehicle_NotFoundPrintsMessage() {
        ParkingGarage garage = new ParkingGarage();

        String out = captureStdout(() ->
                garage.removeVehicle("DOES-NOT-EXIST")
        );

        assertTrue(out.contains("not found"));
    }


    @Test
    void displayGarageStatusPrintsOccupied() {
        ParkingGarage garage = new ParkingGarage();

        SUV suv = new SUV("SV-1", "X5", "BMW", 2022, "Gray");
        garage.parkVehicle(suv);

        User user = new User("Jordan", "j@example.com");
        user.registerVehicle(suv);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        String out = captureStdout(() -> garage.displayGarageStatus(users));

        assertTrue(out.contains("GARAGE STATUS"));
        assertTrue(out.contains("SV-1"));
    }

    @Test
    void suvAndMotorbikeSpots() {
        ParkingGarage garage = new ParkingGarage();
        garage.parkVehicle(new SUV("U-1", "Q5", "Audi", 2021, "Black"));
        garage.parkVehicle(new MotorBike("M-1", "R1", "Yamaha", 2020, "Yellow"));
        assertEquals(19, garage.getAvailableSpaces(VehicleType.SUV));
        assertEquals(9, garage.getAvailableSpaces(VehicleType.MOTORBIKE));
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
