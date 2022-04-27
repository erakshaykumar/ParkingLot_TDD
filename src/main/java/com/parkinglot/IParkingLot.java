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

public interface IParkingLot {
    public void park(Vehicle vehicle) throws ParkingLotException;

    public void unPark(Vehicle vehicle) throws ParkingLotException;

    public void notifyObservers(String message);
}
