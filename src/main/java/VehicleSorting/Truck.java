package VehicleSorting;

public class Truck extends Vehicle{
    public Truck(String licencePlate, String model, String brand, int year, String color, double timeSpent, double timeLeft) {

        super(licencePlate, model, brand, year, color, timeSpent, timeLeft);
    }

    @Override
    public void displayVehicleInfo(){
        System.out.println("Truck Id: " + getLicensePlate() + "\n Truck  Model: " + getModel() + "\n Truck  Brand: " + getBrand()
                + "\nTruck  Year: " + getYear() + "\nTruck Color: " + getColor());
    }
    @Override
    public void displayTimeInfo(){
        System.out.println("Time spent: " + getTimeSpent()+ "\nTime left: " + getTimeLeft());
    }

}
