package AppUserConstruction;

import Finance.CashRegister;
import VehicleSorting.Vehicle;
import java.util.ArrayList;

public class Employee extends Person {

    // Constructor
    public Employee(String name) {
        super(name);
    }

    /**
     * Helper method: checks if a vehicle is registered to any user
     * @param parkedVehicle Vehicle to check
     * @param allUsers List of all users
     * @return true if vehicle is registered, false otherwise
     */
    public boolean isRegistered(Vehicle parkedVehicle, ArrayList<User> allUsers) {
        for (User user : allUsers) {
            for (Vehicle vehicle : user.getVehicles()) {
                if (vehicle.getLicensePlate().equals(parkedVehicle.getLicensePlate())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Main method: checks registration and applies fine if needed
     * @param parkedVehicle Vehicle being checked
     * @param allUsers List of all users
     */
    public void checkVehicleRegistration(Vehicle parkedVehicle, ArrayList<User> allUsers) {
        boolean registered = isRegistered(parkedVehicle, allUsers);

        // Use Finance helper to print fee/fine neatly
        CashRegister.printChargeSummary(parkedVehicle, 0, registered); // hours=0 because this is a fine check
    }
}
