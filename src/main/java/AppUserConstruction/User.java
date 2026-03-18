package AppUserConstruction;

import VehicleSorting.Vehicle;
import java.util.ArrayList;

//Class for a person using the parking garage app
public class User {
    private int userId;     //the user's id
    private String email;       //the user's email
    private ArrayList<Vehicle> vehicles;    //the list of vehicles that the user has tied to their account

    //Constructor that creates a new user object
    public User(int userId, String email, ArrayList<Vehicle> vehicles) {
        this.userId = userId;
        this.email = email;
        vehicles = new ArrayList<>();
    }

    //Returns the user's id
    public int getUserId() {
        return userId;
    }

    //Updates the user's id
    public void setUserId(int userId) {
        this.userId = userId;
    }

    //Returns the user's email
    public String getEmail() {
        return email;
    }

    //Updates the user's email
    public void setEmail(String email) {
        this.email = email;
    }

    //Adds a vehicle to the user's account
    public void registerVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    //Removes a vehicle from the user's account
    public void removeVehicleFromAccount(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

    //Updates the vehicle color and license plate information
    public void updateVehicleInfo(Vehicle vehicle, String licensePlate, String color){
      //  vehicle.setColor(color);
        //  vehicle.setLicensePlate(licensePlate);
    }

    //Displays all the vehicles that the user has registered
    public void viewAllRegisteredVehicles() {
        /*
        for (Vehicle vehicle : vehicles){
            System.out.println("*************************");
            System.out.println("Brand: " + vehicle.getBrand() + "Model: " + vehicle.getModel() +
                    "Year: " + vehicle.getYear() + "Color: " + vehicle.getColor());
        }
         */
    }

    //Displays the amount of spaces that are currently available in the garage
    public void viewAvailibleSpaces(int availableSpaces){
        System.out.println("There are " + availableSpaces + " parking spaces left in the garage at this current time.");
    }

}//ends class
