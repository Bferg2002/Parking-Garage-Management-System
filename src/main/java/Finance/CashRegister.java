package Finance;

import VehicleSorting.Vehicle;
import VehicleSorting.VehicleType;

public class CashRegister {

    // Example hourly rates by vehicle type
    private static final double CAR_RATE = 5.0;
    private static final double SUV_RATE = 7.0;
    private static final double MOTORBIKE_RATE = 3.0;

    // Example fines for unregistered vehicles
    private static final double CAR_FINE = 50.0;
    private static final double SUV_FINE = 70.0;
    private static final double MOTORBIKE_FINE = 30.0;

    /**
     * Calculate parking fee based on vehicle type and hours parked
     * @param vehicleType type of vehicle
     * @param hours number of hours parked
     * @return fee amount
     */
    public static double calculateFee(VehicleType vehicleType, int hours) {
        return switch (vehicleType) {
            case CAR -> CAR_RATE * hours;
            case SUV -> SUV_RATE * hours;
            case MOTORBIKE -> MOTORBIKE_RATE * hours;
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        };
    }

    /**
     * Calculate fine for unregistered vehicle based on type
     * @param vehicleType type of vehicle
     * @return fine amount
     */
    public static double calculateFine(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> CAR_FINE;
            case SUV -> SUV_FINE;
            case MOTORBIKE -> MOTORBIKE_FINE;
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        };
    }

    /**
     * Optionally, print a detailed charge summary with 2 decimal places
     */
    public static void printChargeSummary(Vehicle vehicle, int hours, boolean registered) {
        if (registered) {
            double fee = calculateFee(vehicle.getType(), hours);
            System.out.printf("Vehicle %s parked for %d hours. Fee: $%.2f%n",
                    vehicle.getLicensePlate(), hours, fee);
        } else {
            double fine = calculateFine(vehicle.getType());
            System.out.printf("Vehicle %s is not registered. Fine: $%.2f%n",
                    vehicle.getLicensePlate(), fine);
        }
    }
}