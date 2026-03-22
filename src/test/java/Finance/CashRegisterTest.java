package Finance;

import VehicleSorting.Car;
import VehicleSorting.MotorBike;
import VehicleSorting.SUV;
import VehicleSorting.Vehicle;
import VehicleSorting.VehicleType;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CashRegisterTest {

    @Test
    void calculateFeePerType() {
        assertEquals(25.0, CashRegister.calculateFee(VehicleType.CAR, 5));
        assertEquals(35.0, CashRegister.calculateFee(VehicleType.SUV, 5));
        assertEquals(15.0, CashRegister.calculateFee(VehicleType.MOTORBIKE, 5));
    }

    @Test
    void calculateFinePerType() {
        assertEquals(50.0, CashRegister.calculateFine(VehicleType.CAR));
        assertEquals(70.0, CashRegister.calculateFine(VehicleType.SUV));
        assertEquals(30.0, CashRegister.calculateFine(VehicleType.MOTORBIKE));
    }

    @Test
    void printChargeSummaryRegistered() {
        Vehicle car = new Car("C-REG", "M3", "BMW", 2022, "Black");
        String out = captureStdout(() -> CashRegister.printChargeSummary(car, 2, true));
        assertTrue(out.contains("C-REG"));
        assertTrue(out.contains("2"));
        assertTrue(out.contains("10.00"));
    }

    @Test
    void printChargeSummaryUnregistered() {
        Vehicle bike = new MotorBike("M-UN", "Ninja", "Kawasaki", 2021, "Green");
        String out = captureStdout(() -> CashRegister.printChargeSummary(bike, 0, false));
        assertTrue(out.contains("M-UN"));
        assertTrue(out.contains("30.00"));
    }

    @Test
    void printChargeSummarySuvFee() {
        Vehicle suv = new SUV("S-1", "Highlander", "Toyota", 2020, "Silver");
        String out = captureStdout(() -> CashRegister.printChargeSummary(suv, 3, true));
        assertTrue(out.contains("21.00"));
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
