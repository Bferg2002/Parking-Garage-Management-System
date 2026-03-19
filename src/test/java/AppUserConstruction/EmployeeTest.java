package AppUserConstruction;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import VehicleSorting.Vehicle;

class EmployeeTest {

    @Test
    void testConstructorAndGetters() {
        Employee emp = new Employee(101, "Michael");

        assertEquals("Michael", emp.getName());
        assertEquals(101, emp.getEmployeeId());
    }

    @Test
    void testSetters() {
        Employee emp = new Employee(101, "Michael");

        emp.setEmployeeId(202);
        emp.setRole("Manager");

        assertEquals(202, emp.getEmployeeId());
        assertEquals("Manager", emp.getRole());
    }

    @Test
    void testCalculateExpense() {
        Employee emp = new Employee(101, "Michael");

        double expense = emp.calculateExpense(null);

        assertEquals(100.0, expense);
    }

    @Test
    void testCheckRegistration() {
        Employee emp = new Employee(101, "Michael");

        boolean result = emp.checkRegistration(null);

        assertTrue(result);
    }

    @Test
    void testTrackEntryAndExit() {
        Employee emp = new Employee(101, "Michael");

        emp.trackEntry(null);
        emp.trackExit(null);

        // No assertion needed (just coverage)
    }

    @Test
    void testDisplayRole() {
        Employee emp = new Employee(101, "Michael");
        emp.setRole("Developer");

        emp.displayRole();

        // Covers abstract method implementation
    }
}
