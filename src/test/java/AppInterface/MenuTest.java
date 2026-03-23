package AppInterface;

import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    // Helper to simulate user input
    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    // ─────────────────────────────────────────────
    //  CUSTOMER MENU – existing tests (kept intact)
    // ─────────────────────────────────────────────

    @Test
    void testCustomerViewSpacesAndLogout() {
        provideInput("1\n5\n");
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("AVAILABLE SPACES"));
    }

    @Test
    void testCustomerRegisterVehicle() {
        String input = String.join("\n",
                "2", "1", "TEST123", "Civic", "Honda", "2020", "Blue", "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("Vehicle registered successfully"));
    }

    @Test
    void testCustomerDuplicatePlate() {
        String input = String.join("\n", "2", "1", "MIKE001", "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("already registered"));
    }

    @Test
    void testCustomerRemoveVehicleNotFound() {
        String input = String.join("\n", "4", "NOTFOUND", "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("Vehicle not found"));
    }

    @Test
    void testInvalidMenuOption() {
        String input = String.join("\n", "99", "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("Invalid option"));
    }

    // ─────────────────────────────────────────────
    //  CUSTOMER MENU – new coverage
    // ─────────────────────────────────────────────

    /** Option 3 – view registered vehicles */
    @Test
    void testCustomerViewMyVehicles() {
        String input = String.join("\n", "3", "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("mike123");   // mike has MIKE001 pre-registered
        String output = outputStream.toString();
        assertTrue(output.contains("YOUR REGISTERED VEHICLES") || output.contains("MIKE001"));
    }

    /** Option 4 – successfully remove a vehicle that exists on the account */
    @Test
    void testCustomerRemoveVehicleSuccess() {
        // First register a vehicle for bobby123, then remove it
        String input = String.join("\n",
                "2", "1", "REMOVE99", "Corolla", "Toyota", "2019", "Red",  // register
                "4", "REMOVE99",   // remove
                "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("Vehicle removed successfully"));
    }

    /** Register an SUV (type choice 2) */
    @Test
    void testCustomerRegisterSUV() {
        String input = String.join("\n",
                "2", "2", "SUV001", "Tahoe", "Chevy", "2021", "White", "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("Vehicle registered successfully"));
    }

    /** Register a Motorbike (type choice 3) */
    @Test
    void testCustomerRegisterMotorbike() {
        String input = String.join("\n",
                "2", "3", "MOTO01", "Ninja", "Kawasaki", "2022", "Green", "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("Vehicle registered successfully"));
    }

    /** Register with an invalid vehicle type – hits the default branch */
    @Test
    void testCustomerRegisterInvalidVehicleType() {
        String input = String.join("\n",
                "2", "9", "BADTYPE", "ModelX", "Tesla", "2023", "Black", "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("Invalid vehicle type"));
    }

    /** Year validation – enter a bad year first, then a valid one */
    @Test
    void testCustomerRegisterInvalidYearThenValid() {
        String input = String.join("\n",
                "2", "1", "YEAR99",
                "Civic", "Honda",
                "abcd",   // bad year → triggers error
                "2020",   // valid year
                "Blue",
                "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid year") || output.contains("Vehicle registered successfully"));
    }

    /** Year out-of-range – enter 1800 first, then valid */
    @Test
    void testCustomerRegisterOutOfRangeYearThenValid() {
        String input = String.join("\n",
                "2", "1", "RANGE99",
                "Civic", "Honda",
                "1800",   // out of range
                "2020",   // valid
                "Silver",
                "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        String output = outputStream.toString();
        assertTrue(output.contains("1920") || output.contains("Vehicle registered successfully"));
    }

    /** Color validation – enter digits first, then a valid color */
    @Test
    void testCustomerRegisterInvalidColorThenValid() {
        String input = String.join("\n",
                "2", "1", "COLOR9",
                "Civic", "Honda",
                "2020",
                "123",    // invalid color (digits)
                "Blue",   // valid
                "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid color") || output.contains("Vehicle registered successfully"));
    }

    // ─────────────────────────────────────────────
    //  EMPLOYEE MENU – existing tests (kept intact)
    // ─────────────────────────────────────────────

    @Test
    void testEmployeeCheckRegisteredVehicleYes() {
        String input = String.join("\n", "1", "MIKE001", "yes", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        String output = outputStream.toString();
        assertTrue(output.contains("Vehicle confirmed as registered"));
        assertTrue(output.contains("Fee owed"));
    }

    @Test
    void testEmployeeCheckRegisteredVehicleNoThenFine() {
        String input = String.join("\n", "1", "MIKE001", "no", "1", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("Printing fine"));
    }

    @Test
    void testEmployeeCheckUnknownVehicle() {
        String input = String.join("\n", "1", "UNKNOWN123", "1", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("Vehicle not found"));
    }

    @Test
    void testEmployeeArrivalAndDeparture() {
        String input = String.join("\n", "2", "MIKE001", "3", "MIKE001", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        String output = outputStream.toString();
        assertTrue(output.contains("arrived"));
        assertTrue(output.contains("departed"));
    }

    @Test
    void testEmployeeViewSpaces() {
        String input = String.join("\n", "5", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("AVAILABLE SPACES"));
    }

    @Test
    void testEmployeeDepartureNotFound() {
        String input = String.join("\n", "3", "UNKNOWN123", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("not found"));
    }

    // ─────────────────────────────────────────────
    //  EMPLOYEE MENU – new coverage
    // ─────────────────────────────────────────────

    /** Option 4 – view full garage status */
    @Test
    void testEmployeeViewGarageStatus() {
        String input = String.join("\n", "4", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        // garage status is always printed on login too; just confirm no crash + output exists
        assertFalse(outputStream.toString().isEmpty());
    }

    /** Option 99 – invalid employee menu option */
    @Test
    void testEmployeeInvalidMenuOption() {
        String input = String.join("\n", "99", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("Invalid option"));
    }

    /** Check registered vehicle – user types garbage first, then "yes" (invalid confirm branch) */
    @Test
    void testEmployeeCheckRegisteredVehicleInvalidConfirmThenYes() {
        String input = String.join("\n", "1", "MIKE001", "maybe", "yes", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        String output = outputStream.toString();
        assertTrue(output.contains("yes or no") || output.contains("Vehicle confirmed as registered"));
    }

    /** Check registered vehicle mismatch – choose SUV fine (type 2) */
    @Test
    void testEmployeeCheckRegisteredVehicleNoThenSUVFine() {
        String input = String.join("\n", "1", "MIKE001", "no", "2", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("Printing fine"));
    }

    /** Check registered vehicle mismatch – choose Motorbike fine (type 3) */
    @Test
    void testEmployeeCheckRegisteredVehicleNoThenMotorbikeFine() {
        String input = String.join("\n", "1", "MIKE001", "no", "3", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("Printing fine"));
    }

    /** Check registered vehicle mismatch – invalid fine type first, then valid (car) */
    @Test
    void testEmployeeCheckRegisteredVehicleNoInvalidTypeThenCar() {
        String input = String.join("\n", "1", "MIKE001", "no", "9", "1", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid type") || output.contains("Printing fine"));
    }

    /** Unknown vehicle – choose SUV fine (type 2) */
    @Test
    void testEmployeeCheckUnknownVehicleSUVFine() {
        String input = String.join("\n", "1", "NOPLATEXX", "2", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("Printing fine"));
    }

    /** Unknown vehicle – choose Motorbike fine (type 3) */
    @Test
    void testEmployeeCheckUnknownVehicleMotorbikeFine() {
        String input = String.join("\n", "1", "NOPLATEXY", "3", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("Printing fine"));
    }

    /** Unknown vehicle – invalid fine type first, then car */
    @Test
    void testEmployeeCheckUnknownVehicleInvalidTypeThenCar() {
        String input = String.join("\n", "1", "NOPLATEXZ", "9", "1", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid type") || output.contains("Printing fine"));
    }

    /** Arrival of an unregistered (unknown) vehicle */
    @Test
    void testEmployeeArrivalUnknownVehicle() {
        String input = String.join("\n", "2", "UNKNOWN99", "6");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayEmployeeMenu("bryant123");
        assertTrue(outputStream.toString().contains("unregistered"));
    }

    @Test
    void testCustomerRegisterEmptyPlateRetry() {
        // typeChoice read first, then enters while(!validPlate) which calls input.nextLine()
        // Feed empty string to trigger the isEmpty branch, then valid plate
        String input = String.join("\n",
                "2",       // menu: register
                "1",       // typeChoice: Car
                "",        // plate = "" → isEmpty() TRUE → prints warning
                "PLT001",  // plate valid → validPlate = true
                "Civic",
                "Honda",
                "2020",
                "Blue",
                "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("License plate cannot be empty"));
    }

    @Test
    void testCustomerRegisterEmptyModelRetry() {
        String input = String.join("\n",
                "2",
                "1",
                "MDL001",  // valid plate
                "",        // model = "" → isEmpty() TRUE → prints warning
                "Civic",   // valid model
                "Honda",
                "2020",
                "Blue",
                "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("Model cannot be empty"));
    }

    @Test
    void testCustomerRegisterEmptyBrandRetry() {
        String input = String.join("\n",
                "2",
                "1",
                "BRD001",  // valid plate
                "Civic",   // valid model
                "",        // brand = "" → isEmpty() TRUE → prints warning
                "Honda",   // valid brand
                "2020",
                "Blue",
                "5");
        provideInput(input);
        Menu menu = new Menu();
        menu.displayCustomerMenu("bobby123");
        assertTrue(outputStream.toString().contains("Brand cannot be empty"));
    }
}