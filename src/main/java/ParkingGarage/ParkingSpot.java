package ParkingGarage;

import VehicleSorting.Vehicle;

public class ParkingSpot <T extends Vehicle> {
    private T vehicle;
    private String type;

    public ParkingSpot(String type){
        this.type=type;
    }
    public boolean isEmpty(){
        return vehicle==null;
    }
    public boolean canFit(Vehicle v){
        return type.equalsIgnoreCase(v.getType());
    }
    public void park(Vehicle vehicle){
        this.vehicle=vehicle;
    }
    public void removeVehicle(){
        this.vehicle=null;
    }
    public T getVehicle(){
        return vehicle;
    }
    public String getType(){
        return type;
    }

}
