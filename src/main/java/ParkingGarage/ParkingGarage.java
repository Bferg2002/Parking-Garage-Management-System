package ParkingGarage;

import AppUserConstruction.User;
import Finance.CashRegister;
import VehicleSorting.Vehicle;
import VehicleSorting.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingGarage {
    private final ParkingSpot[] spots;
    private HashMap<String, Integer> hoursParked;

    public ParkingGarage() {
        spots = new ParkingSpot[80];
        for (int i = 0; i < 50; i++) spots[i] = new ParkingSpot(VehicleType.CAR);
        for (int i = 50; i < 70; i++) spots[i] = new ParkingSpot(VehicleType.SUV);
        for (int i = 70; i < 80; i++) spots[i] = new ParkingSpot(VehicleType.MOTORBIKE);
        hoursParked = new HashMap<>();
    }

    public void parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (!spot.isEmpty() && spot.getVehicle().getLicensePlate().equals(vehicle.getLicensePlate())) {
                System.out.println("⚠ Vehicle " + vehicle.getLicensePlate() + " is already parked in the garage.\n");
                return;
            }
        }
        for (int i = 0; i < spots.length; i++) {
            ParkingSpot spot = spots[i];
            if (spot.canFit(vehicle)) {
                spot.park(vehicle);
                if (!hoursParked.containsKey(vehicle.getLicensePlate())) {
                    hoursParked.put(vehicle.getLicensePlate(), 0);
                }
                return;
            }
        }
        System.out.println("No available spots for " + vehicle.getType());
    }

    public void parkVehicleWithHours(Vehicle vehicle, int hours) {
        parkVehicle(vehicle);
        hoursParked.put(vehicle.getLicensePlate(), hours);
    }

    public boolean removeVehicle(String licensePlate) {
        for (ParkingSpot spot : spots) {
            if (!spot.isEmpty() && spot.getVehicle().getLicensePlate().equals(licensePlate)) {
                spot.removeVehicle();
                hoursParked.remove(licensePlate);
                return true;
            }
        }
        System.out.println("⚠ Vehicle " + licensePlate + " not found in garage.\n");
        return false;
    }

    public int getAvailableSpaces(VehicleType type) {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (spot.getType() == type && spot.isEmpty()) count++;
        }
        return count;
    }

    public int getOccupiedSpaces(VehicleType type) {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (!spot.isEmpty() && spot.getType() == type) count++;
        }
        return count;
    }

    public int getHoursParked(String licensePlate) {
        return hoursParked.getOrDefault(licensePlate, 0);
    }

    public void displayGarageStatus(ArrayList<User> allUsers) {
        System.out.println("=================================");
        System.out.println("       CURRENT GARAGE STATUS     ");
        System.out.println("=================================");

        int unregisteredCount = 0;
        for (ParkingSpot spot : spots) {
            if (!spot.isEmpty()) {
                Vehicle v = spot.getVehicle();
                if (!isRegistered(v.getLicensePlate(), allUsers)) {
                    unregisteredCount++;
                }
            }
        }

        if (unregisteredCount > 0) {
            System.out.println("⚠ WARNING: " + unregisteredCount + " unregistered vehicle(s) detected!");
            System.out.println("  Fine per unregistered vehicle:");
            System.out.println("  Car:       $" + (int) CashRegister.calculateFine(VehicleType.CAR));
            System.out.println("  SUV:       $" + (int) CashRegister.calculateFine(VehicleType.SUV));
            System.out.println("  Motorbike: $" + (int) CashRegister.calculateFine(VehicleType.MOTORBIKE));
            System.out.println();
        }

        System.out.println("---------------------------------");
        System.out.println("       REGISTERED VEHICLES       ");
        System.out.println("---------------------------------");
        boolean anyRegistered = false;
        for (ParkingSpot spot : spots) {
            if (!spot.isEmpty()) {
                Vehicle v = spot.getVehicle();
                if (isRegistered(v.getLicensePlate(), allUsers)) {
                    anyRegistered = true;
                    int hours = hoursParked.getOrDefault(v.getLicensePlate(), 0);
                    double fee = CashRegister.calculateFee(v.getType(), hours);
                    System.out.println("  Plate:  " + v.getLicensePlate());
                    System.out.println("  Brand:  " + v.getBrand() + " " + v.getModel());
                    System.out.println("  Type:   " + v.getType());
                    System.out.println("  Color:  " + v.getColor());
                    System.out.println("  Hours:  " + hours + " hrs");
                    System.out.printf("  Fee:    $%.2f%n", fee);
                    System.out.println("---------------------------------");
                }
            }
        }

        if (!anyRegistered) {
            System.out.println("  No registered vehicles currently parked.");
            System.out.println("---------------------------------");
        }
        System.out.println();
    }

    private boolean isRegistered(String licensePlate, ArrayList<User> allUsers) {
        for (User user : allUsers) {
            for (Vehicle v : user.getVehicles()) {
                if (v.getLicensePlate().equals(licensePlate)) {
                    return true;
                }
            }
        }
        return false;
    }
}