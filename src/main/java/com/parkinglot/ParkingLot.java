/**@Purpose : As a parking lot owner I want driver to be able to park their car
 * So that they can catch their flight.
 * @File : Parking Lot TDD Problem
 * @Author : Akshay Kumar & Shardul Kumbhar
 */
package com.parkinglot;

public class ParkingLot {

    Vehicle vehicle;
    /*
    UC1 : Method For Park
     */
    public boolean park(Vehicle vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }
}
