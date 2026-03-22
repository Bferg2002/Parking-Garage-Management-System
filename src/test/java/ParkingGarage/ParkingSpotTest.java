package ParkingGarage;

import VehicleSorting.Car;
import VehicleSorting.MotorBike;
import VehicleSorting.SUV;
import VehicleSorting.VehicleType;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingSpotTest {

    @Test
    void carSpotAcceptsCarAndRejectsWrongType() {
        ParkingSpot spot = new ParkingSpot(VehicleType.CAR);
        assertTrue(spot.isEmpty());
        assertEquals(VehicleType.CAR, spot.getType());

        Car car = new Car("A-1", "M", "B", 2020, "Red");
        assertTrue(spot.canFit(car));

        String parked = captureStdout(() -> spot.park(car));
        assertTrue(parked.contains("A-1"));
        assertFalse(spot.isEmpty());
        assertEquals(car, spot.getVehicle());

        SUV suv = new SUV("S-1", "X", "Y", 2021, "Blue");
        assertFalse(spot.canFit(suv));
        String reject = captureStdout(() -> spot.park(suv));
        assertTrue(reject.contains("Cannot park"));
    }

    @Test
    void removeVehicleClearsSpotAndHandlesEmpty() {
        ParkingSpot spot = new ParkingSpot(VehicleType.MOTORBIKE);
        captureStdout(() -> spot.removeVehicle());

        MotorBike bike = new MotorBike("B-1", "M", "H", 2019, "Black");
        spot.park(bike);
        String removed = captureStdout(() -> spot.removeVehicle());
        assertTrue(removed.contains("removed"));
        assertTrue(spot.isEmpty());
        assertNull(spot.getVehicle());

        captureStdout(() -> spot.removeVehicle());
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
