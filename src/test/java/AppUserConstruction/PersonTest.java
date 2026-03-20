package AppUserConstruction;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    // Dummy subclass for testing abstract class
    class TestPerson extends Person {
        public TestPerson(String name) {
            super(name);
        }

        @Override
        public void displayRole() {
            System.out.println("Test Role");
        }
    }

    @Test
    void testConstructorAndGetter() {
        TestPerson person = new TestPerson("Alice");

        assertEquals("Alice", person.getName());
    }

    @Test
    void testDisplayRole() {
        TestPerson person = new TestPerson("Alice");

        person.displayRole();

        // No assertion needed (just coverage)
    }
}