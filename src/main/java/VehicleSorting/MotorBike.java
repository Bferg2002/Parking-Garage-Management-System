package VehicleSorting;

public class MotorBike extends Vehicle {
    public MotorBike(String licencePlate, String model, String brand, int year, String color, double timeSpent, double timeLeft) {

        super(licencePlate, model, brand, year, color, timeSpent, timeLeft);
    }

    @Override
    public void displayVehicleInfo(){
        System.out.println("Motorcycle Id: " + getLicensePlate() + "\n Motorcycle Model: " + getModel() + "\n Motorcycle Brand: " + getBrand()
                + "\nMotorcycle Year: " + getYear() + "\nMotorcycle Color: " + getColor());
    }
@Override
    public void displayTimeInfo(){
    System.out.println("Time spent: " + getTimeSpent()+ "\nTime left: " + getTimeLeft());
}

}
