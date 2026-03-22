package AppInterface;
import AppUserConstruction.Employee;
import AppUserConstruction.User;
import ParkingGarage.ParkingGarage;
import VehicleSorting.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Menu {
private Scanner input = new Scanner(System.in);
private ParkingGarage parkingGarage;
private HashMap<String, String> logInCredentials;
private HashMap<String, User> userAccounts;
private HashMap<String, Employee> employeeAccounts;

public Menu(){
parkingGarage = new ParkingGarage();
logInCredentials = new HashMap<>();
userAccounts = new HashMap<>();
employeeAccounts = new HashMap<>();

//Credentials for all demo accounts
logInCredentials.put("bobby123", "Test1");
    logInCredentials.put("mike123", "Test2");
    logInCredentials.put("bryant123", "Test3");
    logInCredentials.put("fred123", "Test4");

userAccounts.put("bobby123", new User("Bobby","bm@gmail.com"));
    userAccounts.put("mike123", new User("Mike","mm@gmail.com"));

    employeeAccounts.put("bryant123", new Employee("Bryant"));
    employeeAccounts.put("fred123", new Employee("Fred"));


}


    public void start(){
        boolean correctLogInCheck = true;
    LocalDateTime now = LocalDateTime.now();
        System.out.println(now +
                "\nWelcome to the PARK-IT app!");

        System.out.print("Please input your username and password below: " +
                "\n Username: ");
        String username = input.nextLine();
        System.out.println();

        System.out.print("Password: ");
        String password = input.nextLine();

        System.out.println();

      while(correctLogInCheck){
          if (logInCredentials.containsKey(username)&&logInCredentials.get(username).equals(password)){

              correctLogInCheck = false;
          }

          else {
              System.out.println("Incorrect log-in information. Try again.");
              continue;
          }
          if(userAccounts.containsKey(username)){
 displayCustomerMenu(username);

          } else if (employeeAccounts.containsKey(username)) {
  displayEmployeeMenu(username);
          }

      }

}
    public void displayCustomerMenu(String username){
        System.out.println("Welcome " + username + ".");
        boolean inMenu = true;
        do {
          System.out.println("what would you like to do today?");
          String choice;
            System.out.println();
            System.out.println("1- View available spaces \n 2- Register vehicle \n 3- View my vehicles " +
                    "\n 4- Remove a vehicle \n 5- Logout");
            System.out.println();
          System.out.print("\nYour choice: ");
          System.out.println();
          choice = input.nextLine();
    switch(choice){
        case "1":
            System.out.println("Spots available for cars: "+parkingGarage.getAvailableSpaces(VehicleType.CAR));

            System.out.println("Spots available for SUVs/Trucks: "+parkingGarage.getAvailableSpaces(VehicleType.SUV));

            System.out.println("Spots available for motorbikes: "+parkingGarage.getAvailableSpaces(VehicleType.MOTORBIKE));

            break;

            case "2":

            System.out.println("What type of vehicle are you registering?");
            System.out.println("1- Car\n2- SUV/Truck\n3- Motorbike");
            String typeChoice = input.nextLine();

            System.out.print("License Plate: ");
            String plate = input.nextLine();
            System.out.print("Model: ");
            String model = input.nextLine();
            System.out.print("Brand: ");
            String brand = input.nextLine();
            System.out.print("Year: ");
            int year = Integer.parseInt(input.nextLine());
            System.out.print("Color: ");
            String color = input.nextLine();

            Vehicle newVehicle = null;
            switch(typeChoice) {
                case "1": newVehicle = new Car(plate, model, brand, year, color); break;
                case "2": newVehicle = new SUV(plate, model, brand, year, color); break;
                case "3": newVehicle = new MotorBike(plate, model, brand, year, color); break;
                default: System.out.println("Invalid vehicle type."); break;
            }

            if(newVehicle != null) {
                userAccounts.get(username).registerVehicle(newVehicle);
                System.out.println("Vehicle registered successfully!");
            }
            break;

        case "3":
userAccounts.get(username).viewAllRegisteredVehicles();
           // TODO: Go back and add a method to see all account information after 5 pm presentation
            break;

        case "4":
            System.out.println("");
            break;

            case "5":
inMenu = false;
            break;

        default:
            System.out.println("Invalid option. Please try again.");
    break;
    }

      }

    while(inMenu);


}


    public void displayEmployeeMenu(String username){


    }





}
