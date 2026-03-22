package AppUserConstruction;

import VehicleSorting.Vehicle;
import java.util.ArrayList;

//Class for a person using the parking garage app
public class User {
    private String name;
    private int userId;     //the user's id
    private String email;       //the user's email
    private ArrayList<Vehicle> vehicles;    //the list of vehicles that the user has tied to their account

    public User(String name, String email) {
        this.name = name;
        this.vehicles = new ArrayList<>();
        this.email = email;
    }

    //Returns the user's name
    public String getName() {
        return name;
    }

    //Updates the user's name
    public void setName(String name) {
        this.name = name;
    }

    //Returns the user's email
    public String getEmail() {
        return email;
    }

    // Getter for vehicles
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
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

        for (Vehicle vehicle : vehicles){
            System.out.println("*************************");
            System.out.println("Brand: " + vehicle.getBrand() + "Model: " + vehicle.getModel() +
                    "Year: " + vehicle.getYear() + "Color: " + vehicle.getColor());
        }

    }
}//ends class
