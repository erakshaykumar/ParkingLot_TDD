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


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingServicesTest {
    ParkingLot parkingLot = null;
    Owner owner = null;
    AirportSecurity airportSecurity = null;

    @Before
    public void set() {
        parkingLot = new ParkingLot();
        owner = new Owner();
        airportSecurity = new AirportSecurity();
    }

    /**
     * UC1-Vehicle When Parked Return True
     *
     * @param : Vehicle
     */
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        boolean isParked = parkingLot.isParked(vehicle);
        Assert.assertTrue(isParked);
    }

    /**
     * UC1-Vehicle When Already Parked Return True
     *
     * @param : Vehicle
     */
    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            Vehicle vehicle = new Vehicle("1", "Car");
            parkingLot.park(vehicle);
            Vehicle vehicle1 = new Vehicle("2", "Car");
            boolean isParked = parkingLot.isParked(vehicle1);
            Assert.assertFalse(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_FULL, e.type);
        }
    }

    /**
     * UC2-Vehicle When Unparked Return True
     *
     * @param : Vehicle
     */
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        boolean isUnParked = parkingLot.isUnParked(vehicle);
        Assert.assertTrue(isUnParked);
    }

    /**
     * UC2-Vehicle When Unparked Return False
     *
     * @param : Vehicle
     */
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnFalse() {
        try {
            Vehicle vehicle = new Vehicle("1", "Car");
            parkingLot.park(vehicle);
            parkingLot.unPark(null);
            boolean isUnParked = parkingLot.isUnParked(vehicle);
            Assert.assertFalse(isUnParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    /**
     * UC3-Vehicles to When Owner Return True
     *
     * @param: Vehicles
     */
    @Test
    public void givenVehicle_WhenOwner_ShouldReturnIsFull() throws ParkingLotException {
        parkingLot.addObserver(owner);
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        Vehicle vehicle1 = new Vehicle("2", "Car");
        parkingLot.park(vehicle1);
        Assert.assertEquals("Parking Full", owner.getStatus());
    }

    /**
     * UC4-Vehicles to When Owner And Security Return True
     *
     * @param: Vehicles
     */
    @Test
    public void givenVehicle_WhenOwnerAndAadSecurity_ShouldReturnIsFull() throws ParkingLotException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(airportSecurity);
        Vehicle vehicle = new Vehicle("1", "Car");
        parkingLot.park(vehicle);
        Vehicle vehicle1 = new Vehicle("2", "car");
        parkingLot.park(vehicle1);
        Assert.assertEquals("Parking Full", owner.getStatus());
        Assert.assertEquals("Parking Full", airportSecurity.getStatus());
    }

}
