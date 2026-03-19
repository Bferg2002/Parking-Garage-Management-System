package AppUserConstruction;

public abstract class Person {

        private String name; // one variable

        // Constructor
        public Person(String name) {
            this.name = name;
        }

        // Getter
        public String getName() {
            return name;
        }

        // Abstract method (optional, but typical for abstract classes)
        public abstract void displayRole();
    }








