package AppInterface;
import AppUserConstruction.Employee;
import AppUserConstruction.User;
import Finance.CashRegister;
import ParkingGarage.ParkingGarage;
import VehicleSorting.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private ParkingGarage parkingGarage;
    private HashMap<String, String> logInCredentials;
    private HashMap<String, User> userAccounts;
    private HashMap<String, Employee> employeeAccounts;

    public Menu() {
        parkingGarage = new ParkingGarage();
        logInCredentials = new HashMap<>();
        userAccounts = new HashMap<>();
        employeeAccounts = new HashMap<>();

        logInCredentials.put("bobby123", "Test1");
        logInCredentials.put("mike123", "Test2");
        logInCredentials.put("bryant123", "Test3");
        logInCredentials.put("fred123", "Test4");

        userAccounts.put("bobby123", new User("Bobby", "bm@gmail.com"));
        userAccounts.put("mike123", new User("Mike", "mm@gmail.com"));

        employeeAccounts.put("bryant123", new Employee("Bryant"));
        employeeAccounts.put("fred123", new Employee("Fred"));

        // Mike's SUV already parked for 4 hours
        SUV mikeSUV = new SUV("MIKE001", "Tahoe", "Chevy", 2022, "Black");
        userAccounts.get("mike123").registerVehicle(mikeSUV);
        parkingGarage.parkVehicleWithHours(mikeSUV, 4);

        // Demo: silently park a random unregistered vehicle
        String randomPlate = generateRandomPlate();
        parkingGarage.parkVehicle(new Car(randomPlate, "", "", 0, ""));
    }

    private String generateRandomPlate() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String nums = "0123456789";
        Random rand = new Random();
        StringBuilder plate = new StringBuilder();
        for (int i = 0; i < 3; i++) plate.append(chars.charAt(rand.nextInt(chars.length())));
        for (int i = 0; i < 4; i++) plate.append(nums.charAt(rand.nextInt(nums.length())));
        return plate.toString();
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy  hh:mm a"));
    }

    public void start() {
        System.out.println("=================================");
        System.out.println("        Welcome to PARK-IT       ");
        System.out.println("   Smart Parking, Made Simple    ");
        System.out.println("=================================");
        System.out.println("  " + getTimestamp());
        System.out.println();

        while (true) {
            System.out.println("---------------------------------");
            System.out.println("  Type 'exit' to quit the app   ");
            System.out.println("---------------------------------");
            System.out.print(" Username: ");
            String username = input.nextLine().trim();
            System.out.println();

            if (username.equalsIgnoreCase("exit")) {
                System.out.println("=================================");
                System.out.println("   Thank you for using PARK-IT!  ");
                System.out.println("        See you next time!        ");
                System.out.println("=================================");
                System.exit(0);
            }

            if (username.isEmpty()) {
                System.out.println("⚠ Username cannot be empty. Please try again.\n");
                continue;
            }

            System.out.print(" Password: ");
            String password = input.nextLine().trim();
            System.out.println();

            if (password.isEmpty()) {
                System.out.println("⚠ Password cannot be empty. Please try again.\n");
                continue;
            }

            if (logInCredentials.containsKey(username) &&
                    logInCredentials.get(username).equals(password)) {
                if (userAccounts.containsKey(username)) {
                    displayCustomerMenu(username);
                } else if (employeeAccounts.containsKey(username)) {
                    displayEmployeeMenu(username);
                }
            } else {
                System.out.println("✗ Incorrect log-in information. Try again.\n");
            }
        }
    }

    public void displayCustomerMenu(String username) {
        System.out.println("\n=================================");
        System.out.println("         CUSTOMER PORTAL         ");
        System.out.println("   Welcome back, " + username + "!");
        System.out.println("   " + getTimestamp());
        System.out.println("=================================\n");
        boolean inMenu = true;
        do {
            System.out.println("---------------------------------");
            System.out.println("           MAIN MENU             ");
            System.out.println("---------------------------------");
            System.out.println("  1 - View available spaces      ");
            System.out.println("  2 - Register vehicle           ");
            System.out.println("  3 - View my vehicles           ");
            System.out.println("  4 - Remove a vehicle           ");
            System.out.println("  5 - Logout                     ");
            System.out.println("---------------------------------");
            System.out.print("\n  Your choice: ");
            String choice = input.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    System.out.println("---------------------------------");
                    System.out.println("        AVAILABLE SPACES          ");
                    System.out.println("---------------------------------");
                    System.out.println("  Cars:        " + parkingGarage.getAvailableSpaces(VehicleType.CAR) + " spots");
                    System.out.println("  SUVs/Trucks: " + parkingGarage.getAvailableSpaces(VehicleType.SUV) + " spots");
                    System.out.println("  Motorbikes:  " + parkingGarage.getAvailableSpaces(VehicleType.MOTORBIKE) + " spots");
                    System.out.println("---------------------------------\n");
                    break;

                case "2":
                    System.out.println("---------------------------------");
                    System.out.println("        REGISTER VEHICLE          ");
                    System.out.println("---------------------------------");
                    System.out.println("  1 - Car");
                    System.out.println("  2 - SUV/Truck");
                    System.out.println("  3 - Motorbike");
                    System.out.print("\n  Vehicle type: ");
                    String typeChoice = input.nextLine();

                    System.out.print("  License Plate: ");
                    String plate = "";
                    boolean validPlate = false;
                    while (!validPlate) {
                        plate = input.nextLine().trim();
                        if (plate.isEmpty()) {
                            System.out.print("  ⚠ License plate cannot be empty. Please re-enter: ");
                        } else {
                            validPlate = true;
                        }
                    }

                    boolean duplicate = false;
                    for (User u : userAccounts.values()) {
                        for (Vehicle v : u.getVehicles()) {
                            if (v.getLicensePlate().equalsIgnoreCase(plate)) {
                                duplicate = true;
                                break;
                            }
                        }
                    }
                    if (duplicate) {
                        System.out.println("⚠ A vehicle with that license plate is already registered.\n");
                        break;
                    }

                    System.out.print("  Model: ");
                    String model = "";
                    boolean validModel = false;
                    while (!validModel) {
                        model = input.nextLine().trim();
                        if (model.isEmpty()) {
                            System.out.print("  ⚠ Model cannot be empty. Please re-enter: ");
                        } else {
                            validModel = true;
                        }
                    }

                    System.out.print("  Brand: ");
                    String brand = "";
                    boolean validBrand = false;
                    while (!validBrand) {
                        brand = input.nextLine().trim();
                        if (brand.isEmpty()) {
                            System.out.print("  ⚠ Brand cannot be empty. Please re-enter: ");
                        } else {
                            validBrand = true;
                        }
                    }

                    System.out.print("  Year: ");
                    int year = 0;
                    boolean validYear = false;
                    while (!validYear) {
                        try {
                            year = Integer.parseInt(input.nextLine());
                            if (year < 1920 || year > 2026 || String.valueOf(year).length() != 4) {
                                System.out.print("  ⚠ Year must be between 1920 and 2026. Please re-enter: ");
                            } else {
                                validYear = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.print("  ⚠ Invalid year. Please enter a valid year: ");
                        }
                    }

                    System.out.print("  Color: ");
                    String color = "";
                    boolean validColor = false;
                    while (!validColor) {
                        color = input.nextLine().trim();
                        if (color.matches("[a-zA-Z]+")) {
                            validColor = true;
                        } else {
                            System.out.print("  ⚠ Invalid color. Please enter letters only: ");
                        }
                    }

                    Vehicle newVehicle = null;
                    switch (typeChoice) {
                        case "1": newVehicle = new Car(plate, model, brand, year, color); break;
                        case "2": newVehicle = new SUV(plate, model, brand, year, color); break;
                        case "3": newVehicle = new MotorBike(plate, model, brand, year, color); break;
                        default: System.out.println("⚠ Invalid vehicle type."); break;
                    }

                    if (newVehicle != null) {
                        userAccounts.get(username).registerVehicle(newVehicle);
                        System.out.println("✓ Vehicle registered successfully!\n");
                    }
                    break;

                case "3":
                    System.out.println("---------------------------------");
                    System.out.println("     YOUR REGISTERED VEHICLES     ");
                    System.out.println("---------------------------------");
                    userAccounts.get(username).viewAllRegisteredVehicles();
                    System.out.println("---------------------------------\n");
                    // TODO: add viewAccountInfo() method - show name, email, userId
                    break;

                case "4":
                    System.out.println("---------------------------------");
                    System.out.println("         REMOVE VEHICLE           ");
                    System.out.println("---------------------------------");
                    System.out.print("  Enter license plate: ");
                    String removePlate = input.nextLine();
                    User currentUser = userAccounts.get(username);
                    Vehicle vehicleToRemove = null;
                    for (Vehicle v : currentUser.getVehicles()) {
                        if (v.getLicensePlate().equals(removePlate)) {
                            vehicleToRemove = v;
                            break;
                        }
                    }
                    if (vehicleToRemove != null) {
                        currentUser.removeVehicleFromAccount(vehicleToRemove);
                        System.out.println("✓ Vehicle removed successfully!\n");
                    } else {
                        System.out.println("⚠ Vehicle not found on your account.\n");
                    }
                    break;

                case "5":
                    System.out.println("Logging out... See you next time!\n");
                    inMenu = false;
                    break;

                default:
                    System.out.println("⚠ Invalid option. Please try again.\n");
                    break;
            }
        } while (inMenu);
    }

    public void displayEmployeeMenu(String username) {
        Employee currentEmployee = employeeAccounts.get(username);
        System.out.println("\n=================================");
        System.out.println("         EMPLOYEE PORTAL         ");
        System.out.println("   Welcome, " + currentEmployee.getName() + "! [EMPLOYEE]");
        System.out.println("   " + getTimestamp());
        System.out.println("=================================\n");

        parkingGarage.displayGarageStatus(new ArrayList<>(userAccounts.values()));

        boolean inMenu = true;
        do {
            System.out.println("---------------------------------");
            System.out.println("         EMPLOYEE MENU           ");
            System.out.println("---------------------------------");
            System.out.println("  1 - Check vehicle registration ");
            System.out.println("  2 - Log vehicle arrival        ");
            System.out.println("  3 - Log vehicle departure      ");
            System.out.println("  4 - View garage status         ");
            System.out.println("  5 - View available spaces      ");
            System.out.println("  6 - Logout                     ");
            System.out.println("---------------------------------");
            System.out.print("\n  Your choice: ");
            String choice = input.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    System.out.print("  Enter license plate to check: ");
                    String checkPlate = input.nextLine();
                    Vehicle foundVehicle = null;
                    for (User user : userAccounts.values()) {
                        for (Vehicle v : user.getVehicles()) {
                            if (v.getLicensePlate().equals(checkPlate)) {
                                foundVehicle = v;
                                break;
                            }
                        }
                    }

                    if (foundVehicle != null) {
                        // Show registered description and ask if it matches
                        System.out.println("\n  ✓ Plate found in system. Registered vehicle details:");
                        System.out.println("  ---------------------------------");
                        System.out.println("  Brand:  " + foundVehicle.getBrand());
                        System.out.println("  Model:  " + foundVehicle.getModel());
                        System.out.println("  Year:   " + foundVehicle.getYear());
                        System.out.println("  Color:  " + foundVehicle.getColor());
                        System.out.println("  Type:   " + foundVehicle.getType());
                        System.out.println("  ---------------------------------");

                        boolean validConfirm = false;
                        while (!validConfirm) {
                            System.out.print("  Does the vehicle in the lot match this description? (yes/no): ");
                            String confirm = input.nextLine().trim().toLowerCase();
                            if (confirm.equals("yes")) {
                                validConfirm = true;
                                int hours = parkingGarage.getHoursParked(foundVehicle.getLicensePlate());
                                double fee = CashRegister.calculateFee(foundVehicle.getType(), hours);
                                System.out.println("\n  ✓ Vehicle confirmed as registered.");
                                System.out.printf("  Hours parked: %d | Fee owed: $%.2f%n%n", hours, fee);
                            } else if (confirm.equals("no")) {
                                validConfirm = true;
                                System.out.println("\n  ⚠ Vehicle does not match. Treating as unregistered.");
                                System.out.println("  What type of vehicle is this?");
                                System.out.println("  1 - Car");
                                System.out.println("  2 - SUV/Truck");
                                System.out.println("  3 - Motorbike");
                                boolean validFineType = false;
                                while (!validFineType) {
                                    System.out.print("\n  Vehicle type: ");
                                    String unreqType = input.nextLine();
                                    switch (unreqType) {
                                        case "1":
                                            System.out.printf("⚠ Printing fine for $%.0f...%n%n",
                                                    CashRegister.calculateFine(VehicleType.CAR));
                                            validFineType = true;
                                            break;
                                        case "2":
                                            System.out.printf("⚠ Printing fine for $%.0f...%n%n",
                                                    CashRegister.calculateFine(VehicleType.SUV));
                                            validFineType = true;
                                            break;
                                        case "3":
                                            System.out.printf("⚠ Printing fine for $%.0f...%n%n",
                                                    CashRegister.calculateFine(VehicleType.MOTORBIKE));
                                            validFineType = true;
                                            break;
                                        default:
                                            System.out.println("  ⚠ Invalid type. Please enter 1, 2, or 3.");
                                            break;
                                    }
                                }
                            } else {
                                System.out.print("  ⚠ Please enter yes or no: ");
                            }
                        }
                    } else {
                        // Plate not found at all
                        System.out.println("  ⚠ Vehicle not found in system.");
                        System.out.println("  What type of vehicle is this?");
                        System.out.println("  1 - Car");
                        System.out.println("  2 - SUV/Truck");
                        System.out.println("  3 - Motorbike");
                        boolean validFineType = false;
                        while (!validFineType) {
                            System.out.print("\n  Vehicle type: ");
                            String unreqType = input.nextLine();
                            switch (unreqType) {
                                case "1":
                                    System.out.printf("⚠ Printing fine for $%.0f...%n%n",
                                            CashRegister.calculateFine(VehicleType.CAR));
                                    validFineType = true;
                                    break;
                                case "2":
                                    System.out.printf("⚠ Printing fine for $%.0f...%n%n",
                                            CashRegister.calculateFine(VehicleType.SUV));
                                    validFineType = true;
                                    break;
                                case "3":
                                    System.out.printf("⚠ Printing fine for $%.0f...%n%n",
                                            CashRegister.calculateFine(VehicleType.MOTORBIKE));
                                    validFineType = true;
                                    break;
                                default:
                                    System.out.println("  ⚠ Invalid type. Please enter 1, 2, or 3.");
                                    break;
                            }
                        }
                    }
                    break;

                case "2":
                    System.out.print("  Enter license plate of arriving vehicle: ");
                    String arrivalPlate = input.nextLine();
                    Vehicle arrivalVehicle = null;
                    for (User user : userAccounts.values()) {
                        for (Vehicle v : user.getVehicles()) {
                            if (v.getLicensePlate().equals(arrivalPlate)) {
                                arrivalVehicle = v;
                                break;
                            }
                        }
                    }
                    if (arrivalVehicle != null) {
                        parkingGarage.parkVehicle(arrivalVehicle);
                        System.out.println("✓ Vehicle logged as arrived.\n");
                    } else {
                        System.out.println("⚠ Vehicle not found in system. Flagging as unregistered.\n");
                    }
                    break;

                case "3":
                    System.out.print("  Enter license plate of departing vehicle: ");
                    String departurePlate = input.nextLine();
                    boolean removed = parkingGarage.removeVehicle(departurePlate);
                    if (removed) {
                        System.out.println("✓ Vehicle logged as departed.\n");
                    }
                    break;

                case "4":
                    parkingGarage.displayGarageStatus(new ArrayList<>(userAccounts.values()));
                    break;

                case "5":
                    System.out.println("---------------------------------");
                    System.out.println("        AVAILABLE SPACES          ");
                    System.out.println("---------------------------------");
                    System.out.println("  Cars:        " + parkingGarage.getAvailableSpaces(VehicleType.CAR) + " spots");
                    System.out.println("  SUVs:        " + parkingGarage.getAvailableSpaces(VehicleType.SUV) + " spots");
                    System.out.println("  Motorbikes:  " + parkingGarage.getAvailableSpaces(VehicleType.MOTORBIKE) + " spots");
                    System.out.println("---------------------------------\n");
                    break;

                case "6":
                    System.out.println("Logging out... Stay safe!\n");
                    inMenu = false;
                    break;

                default:
                    System.out.println("⚠ Invalid option. Please try again.\n");
                    break;
            }
        } while (inMenu);
    }
}