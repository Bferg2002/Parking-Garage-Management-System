package VehicleSorting;

public class SUV extends Vehicle {

    public SUV(String licensePlate, String model, String brand, int year, String color) {
        super(licensePlate, model, brand, year, color);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.SUV;
    }

}