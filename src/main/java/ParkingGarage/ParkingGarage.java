package ParkingGarage;

import VehicleSorting.Vehicle;
import VehicleSorting.VehicleType;

public class ParkingGarage {

    private final ParkingSpot[] spots;

    public ParkingGarage() {
        spots = new ParkingSpot[80];
        // 50 Car spots
        for (int i = 0; i < 50; i++) spots[i] = new ParkingSpot(VehicleType.CAR);
        // 20 SUV spots
        for (int i = 50; i < 70; i++) spots[i] = new ParkingSpot(VehicleType.SUV);
        // 10 Motorcycle spots
        for (int i = 70; i < 80; i++) spots[i] = new ParkingSpot(VehicleType.MOTORBIKE);
    }

    // Park a vehicle
    public void parkVehicle(Vehicle vehicle) {
        for (int i = 0; i < spots.length; i++) {
            ParkingSpot spot = spots[i];
            if (spot.canFit(vehicle)) {
                spot.park(vehicle);
                System.out.println(vehicle.getLicensePlate() + " parked at spot #" + (i + 1));
                return;
            }
        }
        System.out.println("No available spots for " + vehicle.getType());
    }

    // Remove a vehicle by license plate
    public void removeVehicle(String licensePlate) {
        for (ParkingSpot spot : spots) {
            if (!spot.isEmpty() && spot.getVehicle().getLicensePlate().equals(licensePlate)) {
                spot.removeVehicle();
                return;
            }
        }
        System.out.println("Vehicle " + licensePlate + " not found.");
    }

    // Get available spaces of a type
    public int getAvailableSpaces(VehicleType type) {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (spot.getType() == type && spot.isEmpty()) count++;
        }
        return count;
    }

    // Get occupied spaces of a type
    public int getOccupiedSpaces(VehicleType type) {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (!spot.isEmpty() && spot.getType() == type) count++;
        }
        return count;
    }

    // Display all vehicles in garage
    public void displayGarageStatus() {
        System.out.println("=== Garage Status ===");
        for (ParkingSpot spot : spots) {
            if (!spot.isEmpty()) {
                spot.getVehicle().displayVehicleInfo();
                System.out.println("--------------------");
            }
        }
    }
}