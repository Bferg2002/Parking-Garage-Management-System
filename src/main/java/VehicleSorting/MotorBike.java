package VehicleSorting;

public class MotorBike extends Vehicle {
    public MotorBike(String licencePlate, String model, String brand, int year, String color, boolean isRegistered) {

        super(licencePlate, model, brand, year, color, isRegistered);
    }

    @Override
    public void displayVehicleInfo(){
        System.out.println("Motorcycle Id: " + getLicensePlate() + "\n Motorcycle Model: " + getModel() + "\n Motorcycle Brand: " + getBrand()
                + "\nMotorcycle Year: " + getYear() + "\nMotorcycle Color: " + getColor());

        if(isRegistered()){
            System.out.println("Your " + getYear() + " " + getBrand() + " " + getModel() + " is registered to the vehicle library");
        }
        else {
            System.out.println("This " + getYear() + " " + getBrand() + " " + getModel() + " is not registered to the vehicle library");
        }

    }

}
