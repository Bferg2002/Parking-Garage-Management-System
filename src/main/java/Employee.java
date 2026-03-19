import VehicleSorting.Vehicle;

// Employee class extending Person
public class Employee extends Person {
    // Employee class extending Person
        private int employeeId;
        private String role;

        // Constructor
        public Employee(int employeeId, String name) {
            super(name); // call parent constructor
            this.employeeId = employeeId;
        }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // Methods from your original design
        public void trackEntry(Vehicle vehicle) {
            System.out.println("Tracking entry for vehicle: " + vehicle);
        }

        public void trackExit(Vehicle vehicle) {
            System.out.println("Tracking exit for vehicle: " + vehicle);
        }

        public double calculateExpense(Vehicle vehicle) {
            // Example logic
            return 100.0;
        }

        public boolean checkRegistration(Vehicle vehicle) {
            // Example logic
            return true;
        }

        @Override
        public void displayRole() {
            System.out.println("Name: " + super.getName());
            System.out.println("Role: " + getRole());
            System.out.println("EmployeeId: " + getEmployeeId());


        }
    }

