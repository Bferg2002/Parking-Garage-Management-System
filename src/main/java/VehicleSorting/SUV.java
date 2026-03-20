package VehicleSorting;

public class SUV extends Vehicle{

    public SUV(String licencePlate, String model, String brand, int year, String color, double timeSpent, double timeLeft) {

        super(licencePlate, model, brand, year, color, timeSpent, timeLeft, "SUV");
    }

    @Override
    public void displayVehicleInfo(){
        System.out.println("Vehicle type: " + getType() +"\nSUV Id: " + getLicensePlate() + "\n SUV Model: " + getModel() + "\n SUV Brand: " + getBrand()
                + "\nSUV Year: " + getYear() + "\nSUV Color: " + getColor());
    }
    @Override
    public void displayTimeInfo(){
        System.out.println("Time spent: " + getTimeSpent()+ "\nTime left: " + getTimeLeft());
    }


}
