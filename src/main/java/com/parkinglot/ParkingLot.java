/**
 * @Purpose : UC1-As a parking lot owner I want driver to be able to park their car
 * So that they can catch their flight.
 * UC2-As a driver I want to unpark my car So that I can go home
 * UC3-As a Parking Lot owner I want to know when the lot is full So that I can put out the full sign
 * UC4-As a airport security personal I want to know when the lot is full So that I can
 * redirect my security staff
 * @File : Parking Lot TDD Problem
 * @Author : Akshay Kumar & Shardul Kumbhar
 */
package com.parkinglot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ParkingLot implements IParkingLot {
    int parkingLotCapacity = 2;
    private final LinkedHashMap<String, Object> parkingMap = new LinkedHashMap<String, Object>();
    private List<ParkingObserver> observers = new ArrayList<>();

    //DEFAULT CONSTRUCTOR
    public ParkingLot() {
    }

    public void addObserver(ParkingObserver observer) {
        this.observers.add(observer);
    }

    /**
     * @Purpose : To Park Vehicle
     * @Param : vehicle obj
     * @Function :To check for parking status
     * @Return :boolean t/f
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
        if (this.parkingMap.size() <= parkingLotCapacity) {
            parkingMap.put(vehicle.getId(), vehicle);
        } else if (parkingMap.size() == parkingLotCapacity)
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_FULL, "Parking Lot is Full");
        if (parkingMap.size() == parkingLotCapacity)
            notifyObservers("Parking Full");
    }

    //METHOD FOR UNPARK VEHICLE
    /**
     * @Purpose : To UnPark Vehicle
     * @Param : Vehicle obj ,exception handling
     * @Function :handling runtime errors
     * @Return :t/f
     */
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle");
        if (parkingMap.containsKey(vehicle.getId())) {
            parkingMap.remove(vehicle.getId());
        }
    }

    @Override
    public void notifyObservers(String message) {
        for (ParkingObserver list : observers) {
            list.update(message);
        }
    }

    //METHOD FOR CHECK VEHICLE PARKED
    /**
     * @Purpose :To check for park status
     * @Param : vehicle obj
     * @Function :check for parking
     * @Return :t/f
     */
    public boolean isParked(Vehicle vehicle) {
        if (parkingMap.containsKey(vehicle.getId()))
            return true;
        return false;
    }


    //METHOD FOR CHECK VEHICLE UNPARKED
    /**
     * @Purpose : To unpark
     * @Param : veh obj
     * @Function :Unpark
     * @Return :t/f
     */
    public boolean isUnParked(Vehicle vehicle) {
        if (!parkingMap.containsKey(vehicle.getId()))
            return true;
        return false;
    }
}
