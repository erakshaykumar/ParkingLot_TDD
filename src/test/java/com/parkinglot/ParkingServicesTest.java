/**
 * @Purpose : As a parking lot owner I want driver to be able to park their car
 * So that they can catch their flight.
 * @File : Parking Lot TDD Problem
 * @Author : Akshay Kumar & Shardul Kumbhar
 */
package com.parkinglot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ParkingServicesTest {
    /*
    UC1 : Vehicle When Park Return True
     */
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle car = new Vehicle("Swift");
        ParkingLot parkingLot = new ParkingLot();
        boolean isParked = parkingLot.park(car);
        Assertions.assertTrue(isParked);
    }

    /*
     UC2 : Vehicle when Unpark Return True
     */
    @Test
    public void givenVehicle_WhenUnparked_ShouldReturnTrue() {
        Vehicle car = new Vehicle("Swift");
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(car);
        boolean isUnParked = parkingLot.unPark(car);
        Assertions.assertTrue(isUnParked);
    }
    /*
     UC3 : Check For Parking Lot Is Full
     */
    @Test
    public void givenArrayOfVehicles_WhenFull_ShouldReturnTrue() {
        Vehicle[] vehicles = {new Vehicle("car1"),
                new Vehicle("car2")};
        boolean isFull = ParkingLot.checkParkingLot(vehicles);
        Assertions.assertTrue(isFull);
    }

}
