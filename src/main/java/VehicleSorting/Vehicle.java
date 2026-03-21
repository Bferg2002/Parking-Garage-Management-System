package VehicleSorting;

public abstract class Vehicle {

    private String licensePlate;
    private String model;
    private String brand;
    private int year;
    private String color;

    // Constructor (no type anymore)
    public Vehicle(String licensePlate, String model, String brand, int year, String color) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.color = color;
    }

    // 🔥 Each subclass defines its own type
    public abstract VehicleType getType();

    // Getters
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Display method
    public void displayVehicleInfo() {
        System.out.println(
                "Vehicle Type: " + getType() +
                        "\nPlate: " + getLicensePlate() +
                        "\nModel: " + getModel() +
                        "\nBrand: " + getBrand() +
                        "\nYear: " + getYear() +
                        "\nColor: " + getColor()
        );
    }
}