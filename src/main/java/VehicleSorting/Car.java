package VehicleSorting;

public class Car extends Vehicle{

    public Car(String licencePlate, String model, String brand, int year, String color, double timeSpent, double timeLeft) {

        super(licencePlate, model, brand, year, color, timeSpent, timeLeft);
    }
   @Override
           public void displayVehicleInfo(){
        System.out.println("Car Id: " + getLicensePlate() + "\n Car Model: " + getModel() + "\n Car Brand: " + getBrand()
       + "\nCar Year: " + getYear() + "\nCar Color: " + getColor());
    }

    @Override
    public void displayTimeInfo(){

    }

}
