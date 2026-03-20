package VehicleSorting;

public class Truck extends Vehicle{
    public Truck(String licencePlate, String model, String brand, int year, String color, double timeSpent, double timeLeft) {

        super(licencePlate, model, brand, year, color, timeSpent, timeLeft, "Truck");
    }

    @Override
    public void displayVehicleInfo(){
        System.out.println("Vehicle type: " + getType() + "\n Truck Id: " + getLicensePlate() + "\n  Model: " + getModel() + "\n  Brand: " + getBrand()
                + "\nTruck  Year: " + getYear() + "\nTruck Color: " + getColor());
    }
    @Override
    public void displayTimeInfo(){
        System.out.println("Time spent: " + getTimeSpent()+ "\nTime left: " + getTimeLeft());
    }

}
