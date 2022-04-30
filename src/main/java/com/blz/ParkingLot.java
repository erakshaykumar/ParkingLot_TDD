package com.blz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private Vehicle vehicle;
    private List<ParkingLotObserver> observers;
    private Map<Integer,Vehicle> parkingMap;
    Attendant attendant;

    public ParkingLot() {
        this.observers = new ArrayList<>();
    }

    /**
     * @Purpose : To park the vehicle
     * @Param : vehicle
     */


    public void vehicleParking(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("Parking lot is full");
        this.vehicle = vehicle;
        if(this.vehicle != null){
            String message = "Parking lot is full";
            for(ParkingLotObserver observer:observers){
                observer.update(message);
            }
        }
    }
    /**
     * @Purpose : To unPark the vehicle
     * @Param : vehicle
     */
    public void vehicleUnparking(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicle == null)
            throw new ParkingLotException("lot is empty");
        if (this.vehicle .equals(vehicle)){
            this.vehicle = null;
            for(ParkingLotObserver observer:observers){
                observer.update("Parking lot has space");
            }
            return;
        }
        throw new ParkingLotException("Please ask correct vehicle");
    }
    /**
     * @Purpose : Method to check vehicle is park or not
     * @Param : vehicle
     * @Return : Returns boolean value true or false
     */
    public boolean isParked(Vehicle vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
    /**
     * @Purpose :  Method to check vehicle is unpark or not
     * @Param : vehicle
     * @Return : Returns boolean value true or false
     */
    public boolean isUnParked(Vehicle vehicle) {
        if (this.vehicle == null)
            return true;
        return false;
    }

    public void registerObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public int getVehicleLotNumber(Vehicle vehicle) {
        for (Map.Entry map : parkingMap.entrySet()){
            if(map.getValue()==vehicle) return (int) map.getKey();
        }
        return 0;
    }
}