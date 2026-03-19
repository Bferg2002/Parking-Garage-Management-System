package ParkingGarage;

import VehicleSorting.Vehicle;

public class ParkingGarage {

    //This is an array of parking spots that can hold any type of vehicle object.
    private final ParkingSpot<? extends Vehicle>[] spots;

    //we instantiate a parking garage with hardcoded 80 total spots
    //50 car spots, 20 SUV spots, and 10 motorcycle spots
    public ParkingGarage(){
        spots=new ParkingSpot[80];
        //50 car spots
        for (int i = 0; i < 50; i++) {
            spots[i] = new ParkingSpot<>("Car");
        }
        // 20 truck spots
        for (int i = 50; i < 70; i++) {
            spots[i] = new ParkingSpot<>("SUV");
        }
        // 10 motorcycle spots
        for (int i = 70; i < 80; i++) {
            spots[i] = new ParkingSpot<>("MotorBike");
        }
    }

    //returns number of available spaces based on vehicle type
    public int getAvailableParkingSpaces(String type) {
        int availableSpaces=0;
        for (ParkingSpot spot : spots) {
            if (spot.getType().equalsIgnoreCase(type) && spot.isEmpty()) {
                availableSpaces++;
            }
        }
        return availableSpaces;
    }
    public int getOccupiedParkingSpaces(Vehicle[] spots) {
        int occupiedSpaces=0;
        for (Vehicle spot : spots) {
            if (spot != null) {
                occupiedSpaces++;
            }
        }
        return occupiedSpaces;
    }
    //parks vehicle object in array based on type
    public void parkVehicle(Vehicle vehicle) {
        for (int i = 0; i < spots.length; i++) {
            ParkingSpot<? extends Vehicle> spot = spots[i];
            if (spot.canFit(vehicle) && spot.isEmpty()) {
                spot.park(vehicle);
                System.out.println("Vehicle " + vehicle.getLicensePlate() +
                        " parked at spot #" + (i + 1));
                return;
            }
        }
        System.out.println("No available spots for " + vehicle.getType());
    }
//removes vehicle based on license plate
    public void removeVehicle(String licensePlate){
        for (int i = 0; i < spots.length; i++) {
            ParkingSpot<? extends Vehicle> spot = spots[i];
            if (!spot.isEmpty() &&
                    spot.getVehicle().getLicensePlate().equals(licensePlate)) {
                spot.removeVehicle();
                System.out.println("Vehicle " + licensePlate +
                        " removed from spot #" + (i + 1));
                return;
            }
        }
        System.out.println("Vehicle not found.");
    }
    //iterates through master array, printing all items found
    public void printCurrentCarsInLot() {
        for (int i = 0; i < 50; i++) {
            if (spots[i] != null) {
                System.out.println("Cars parked in lot:" + spots[i]);
            }else{
                System.out.println("Car lot is empty");
            }
            for (i = 50; i < 70; i++) {
                if (spots[i] != null) {
                    System.out.println("SUVs parked in lot:" + spots[i]);
                } else {
                    System.out.println("Car lot is empty");
                }
                for (i = 70; i < 80; i++) {
                    if (spots[i] != null) {
                        System.out.println("Motorcycles parked in lot:" + spots[i]);
                    }
                }

            }
        }
    }


//    public int getOccupiedCarSpaces(Car[] car) {
//        for (int i = 0; i<car.length;i++){
//            if (car[i]==null) {
//                System.out.println("Lot is empty");
//            }else if(car[i]!=null)
//                carOccupiedSpaces++;
//            }
//        return carOccupiedSpaces;
//    }
//    public int getOccupiedMotoSpaces(MotorBike[] motorBikes) {
//        for (int i = 0; i<motorBikes.length;i++){
//            if (motorBikes[i]==null) {
//                System.out.println("The Motorcycle lot is empty.");
//            }else if(motorBikes[i]!=null)
//                motoOccupiedSpaces++;
//        }
//        return motoOccupiedSpaces;
//    }
//    public int getAvailableSUVSpaces(SUV[] suvs) {
//        for (int i = 0; i<suvs.length;i++){
//            if (suvs[i]==null) {
//                System.out.println("The Motorcycle lot is empty.");
//            }else if(suvs[i]!=null)
//                suvOccupiedSpaces++;
//        }
//        return suvOccupiedSpaces;
//    }
//public void parkVehicle(Vehicle[] spots, Vehicle vehicle) {
//    for (int i = 0; i < spots.length; i++) {
//        if (spots[i] == null) {
//            spots[i] = vehicle;
//            System.out.println("Vehicle with licence plate #" + vehicle.getLicensePlate() + " has been parked at spot #" + getParkingSpotNumber());
//            break;
//        }else System.out.println("Lot is full");
//    }
//}




}
