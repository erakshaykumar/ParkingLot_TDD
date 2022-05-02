/**
 * @Purpose : UC1-As a parking lot owner I want driver to be able to park their car
 * So that they can catch their flight.
 * UC2-As a driver I want to unpark my car So that I can go home
 * UC3-As a Parking Lot owner I want to know when the lot is full So that I can put out the full sign
 * UC4-As a airport security personal I want to know when the lot is full So that I can
 * redirect my security staff
 * UC5-As a parking lot Owner I want to know when the parking lot has space again So that I
 * can take in the full sign
 * UC6-As a parking lot Owner I want a parking attendant to park cars so that I can make decisions
 * on where to park the cars
 * UC7-As a driver I want to find my car so that can go home
 * UC8-As a parking lot owner I want to know when a car was parked on my lot
 * so that I charge the lot users
 * UC9-As a parking lot Owner I want a parking attendant to evenly direct cars to the lots
 * So that the lots have an evenly distribution
 * UC10-As handicap driver I want the parking attendant to park my car to a lot which has the
 * nearest free space So that I don’t have to go far for unparking my car
 * @File : Parking Lot TDD Problem
 * @Author : Akshay Kumar & Shardul Kumbhar
 */
package com.blz;

import java.util.Map;

public class Owner implements ParkingLotObserver {
    private static String status;
    private int key = 0;

    /**
    Updating message to owner
     */
    public void update(String message) {
        this.status = message;
    }

    /**
     * @return updated message
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * @param parkingMap
     * @return returns key to attendant
     */
    public int getLotNumberToPark(Map<Integer, Vehicle> parkingMap, DriverType driverType) {
        if(driverType==DriverType.HANDICAP)
            for(int key=1;key<=parkingMap.size();key++){
                if(parkingMap.get(key)==null)
                    return key;
            }

        if(driverType==DriverType.NORMAL) {
            int key = 6;
            for ( key = 6; key <= parkingMap.size(); key++) {
                if (parkingMap.get(key)==null)
                    return key;
            }

            for ( key = 1; key <= parkingMap.size(); key++) {
                if (parkingMap.get(key)==null)
                    return key;
            }
        }
        return this.key;
    }
}