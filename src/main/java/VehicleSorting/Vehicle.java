package VehicleSorting;

public abstract class Vehicle {
private String licensePlate;
private String model;
private String brand;
private int year;
private String color;
private double timeSpent;
private double timeLeft;

    public Vehicle(String licensePlate, String model, String brand, int year,  String color, double timeSpent, double timeLeft) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.timeSpent = timeSpent;
        this.timeLeft = timeLeft;
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public String getLicensePlate(){
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

    public void setLicensePlate(String licensePlate){
        this.licensePlate = licensePlate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
    }

    public abstract void displayVehicleInfo();
    public abstract void displayTimeInfo();

}
