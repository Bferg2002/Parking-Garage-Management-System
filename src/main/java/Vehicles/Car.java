package Vehicles;

public class Car extends Vehicle{

    public Car(String licencePlate, String model, String brand, int year, String color, boolean isRegistered) {

        super(licencePlate, model, brand, year, color, isRegistered);
    }
   @Override
           public void displayVehicleInfo(){
        System.out.println("Car Id: " + getLicensePlate() + "\n Car Model: " + getModel() + "\n Car Brand: " + getBrand()
       + "\nCar Year: " + getYear() + "\nCar Color: " + getColor());

       if(isRegistered()){
           System.out.println("Your " + getYear() + " " + getBrand() + " " + getModel() + " is registered to the vehicle library");
       }
       else {
           System.out.println("This " + getYear() + " " + getBrand() + " " + getModel() + " is not registered to the vehicle library");
       }

    }


}
