package ParkingGarage;

import VehicleSorting.Vehicle;
import VehicleSorting.VehicleType;

public class ParkingSpot {

    private Vehicle vehicle;       // null if empty
    private final VehicleType type;

    public ParkingSpot(VehicleType type) {
        this.type = type;
    }

    public boolean isEmpty() {
        return vehicle == null;
    }

    public boolean canFit(Vehicle v) {
        return v.getType() == type && isEmpty();
    }

    public void park(Vehicle v) {
        if (canFit(v)) {
            this.vehicle = v;
            System.out.println("Vehicle " + v.getLicensePlate() + " parked.");
        } else {
            System.out.println("Cannot park " + v.getLicensePlate() + " in this spot.");
        }
    }

    public void removeVehicle() {
        if (vehicle != null) {
            System.out.println("Vehicle " + vehicle.getLicensePlate() + " removed from spot.");
            vehicle = null;
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public VehicleType getType() {
        return type;
    }
}