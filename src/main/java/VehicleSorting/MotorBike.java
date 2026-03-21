package VehicleSorting;

public class MotorBike extends Vehicle {

    public MotorBike(String licensePlate, String model, String brand, int year, String color) {
        super(licensePlate, model, brand, year, color);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.MOTORBIKE;
    }

}