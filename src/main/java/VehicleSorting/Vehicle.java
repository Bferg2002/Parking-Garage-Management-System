package VehicleSorting;

public abstract class Vehicle {
private String licensePlate;
private String model;
private String brand;
private int year;
private String color;
private boolean isRegistered;

    public Vehicle(String licensePlate, String model, String brand, int year,  String color, boolean isRegistered) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.isRegistered = isRegistered;
        this.color = color;
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

    public boolean isRegistered() {
        return isRegistered;
    }
    public void setLicensePlate(String licensePlate){
        this.licensePlate = licensePlate;
    }

    public void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract void displayVehicleInfo();
}
