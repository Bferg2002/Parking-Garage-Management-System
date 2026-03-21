package VehicleSorting;

public class Car extends Vehicle {

    public Car(String licensePlate, String model, String brand, int year, String color) {
        super(licensePlate, model, brand, year, color);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

}