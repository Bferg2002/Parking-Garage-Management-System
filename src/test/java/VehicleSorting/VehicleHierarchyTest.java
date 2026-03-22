package VehicleSorting;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehicleHierarchyTest {

    @Test
    void carTypeAndFields() {
        Car car = new Car("CA-1", "Model S", "Tesla", 2023, "White");
        assertEquals(VehicleType.CAR, car.getType());
        assertEquals("CA-1", car.getLicensePlate());
        assertEquals("Model S", car.getModel());
        assertEquals("Tesla", car.getBrand());
        assertEquals(2023, car.getYear());
        assertEquals("White", car.getColor());

        car.setLicensePlate("CA-2");
        car.setColor("Black");
        assertEquals("CA-2", car.getLicensePlate());
        assertEquals("Black", car.getColor());
    }

    @Test
    void suvType() {
        SUV suv = new SUV("SV-9", "CX-5", "Mazda", 2022, "Red");
        assertEquals(VehicleType.SUV, suv.getType());
    }

    @Test
    void motorBikeType() {
        MotorBike bike = new MotorBike("MB-3", "Scout", "Indian", 2021, "Brown");
        assertEquals(VehicleType.MOTORBIKE, bike.getType());
    }

    @Test
    void displayVehicleInfoIncludesDetails() {
        Car car = new Car("D-1", "Accord", "Honda", 2019, "Silver");
        String out = captureStdout(car::displayVehicleInfo);
        assertTrue(out.contains("CAR"));
        assertTrue(out.contains("D-1"));
        assertTrue(out.contains("Accord"));
        assertTrue(out.contains("Honda"));
        assertTrue(out.contains("2019"));
        assertTrue(out.contains("Silver"));
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
