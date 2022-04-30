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
 * @File : Parking Lot TDD Problem
 * @Author : Akshay Kumar & Shardul Kumbhar
 */
package com.blz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class ParkingLotTest {
    ParkingLot parkingLot = null;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot();
    }

    /**
     * UC1- Given vehicle when parked should return true
     */
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("car", 1);
        try {
            parkingLot.vehicleParking(vehicle);
            boolean isParked = parkingLot.isParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    /**
     * UC1- given a vehicle when already parked should throw exception
     */
    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldThrowException() {
        Vehicle vehicle = new Vehicle("car1", 1);
        Vehicle vehicle1 = new Vehicle("car2", 2);
        try {
            parkingLot.vehicleParking(vehicle);
            parkingLot.vehicleParking(vehicle1);
            boolean isParked = parkingLot.isParked(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking lot is full", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * UC2- Given a vehicle when unparked should return true
     */
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("car", 1);
        try {
            parkingLot.vehicleParking(vehicle);
            parkingLot.vehicleUnparking(vehicle);
            boolean isUnParked = parkingLot.isUnParked(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    /**
     * UC2- given a unparked vehicle when try to unpark should return throw exception
     */
    @Test
    public void givenUnParkedVehicle_WhenTryToUnPark_ShouldReturnThrowException() {
        Vehicle vehicle = new Vehicle("car", 1);
        try {
            parkingLot.vehicleUnparking(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("lot is empty", e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * UC2- Given a vehicle when try to unpark different vehicle should throw exception
     */
    @Test
    public void givenVehicle_WhenTryToUnParkDifferentVehicle_ShouldThrowException() {
        Vehicle vehicle = new Vehicle("car1", 1);
        Vehicle vehicle1 = new Vehicle("car2", 2);
        try {
            parkingLot.vehicleParking(vehicle);
            parkingLot.vehicleUnparking(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Please ask correct vehicle", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * UC3 Given a vehicle when parking lot is full should give message to owner
     */

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToOwner() {
        Vehicle vehicle1 = new Vehicle("car1", 1);
        Vehicle vehicle2 = new Vehicle("car2", 2);
        try {
            Owner owner = new Owner();
            parkingLot.registerObserver(owner);
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
            parkingLot.vehicleParking(new Vehicle("car3", 3));
            parkingLot.vehicleParking(new Vehicle("car4", 4));
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot is full", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC4- Given a vehicle when parking lot is full should give message to security personal
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToSecurityPersonal() {
        Vehicle vehicle1 = new Vehicle("car1", 1);
        Vehicle vehicle2 = new Vehicle("car2", 2);
        try {
            SecurityPersonal securityPersonal = new SecurityPersonal();
            parkingLot.registerObserver(securityPersonal);
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
            parkingLot.vehicleParking(new Vehicle("car3", 3));
            parkingLot.vehicleParking(new Vehicle("car4", 4));
            String status = securityPersonal.getStatus();
            Assert.assertEquals("Parking lot is full", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC5- Given a vehicle when parking lot has space again should give message to owner
     */
    @Test
    public void givenAVehicle_WhenParkingLotHasSpaceAgain_ShouldGiveMessageToOwner() {
        Vehicle vehicle = new Vehicle("car1", 1);
        try {
            Owner owner = new Owner();
            parkingLot.registerObserver(owner);
            parkingLot.vehicleParking(vehicle);
            parkingLot.vehicleUnparking(vehicle);
            String status = owner.getStatus();
            Assert.assertEquals("Parking lot has space", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC6- given attendant when owner gives the slot to park the vehicle should park
     */
    @Test
    public void givenAttendant_WhenOwnerGivesTheSlotToParkTheVehicle_ShouldPark() {
        try {
            Owner owner = new Owner();
            parkingLot.registerObserver(owner);
            Vehicle vehicle1 = new Vehicle("car1", 1);
            Vehicle vehicle2 = new Vehicle("car2", 2);
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
            int vehicleLotNumber = parkingLot.getVehicleLotNumber(vehicle2);
            Assert.assertEquals(2, vehicleLotNumber);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC7- given vehicle when find vehicle should return key
     */
    @Test
    public void givenVehicle_WhenVehicleFind_ShouldReturnKey() {
        try {
            Vehicle vehicle1 = new Vehicle("car1", 1);
            Vehicle vehicle2 = new Vehicle("car2", 2);
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
            int key = parkingLot.getVehicleLocation(vehicle2);
            Assert.assertEquals(2, key);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC8- given vehicle when find vehicle should return key
     */
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTimeOfParking() {
        try {
            Vehicle vehicle1 = new Vehicle("car", 1);
            parkingLot.vehicleParking(vehicle1);
            LocalDateTime localDateTime = LocalDateTime.now();
            Assert.assertEquals(localDateTime, parkingLot.getParkedTime());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * @UC9- Parking lot owner wants parking attendant to direct cars in evenly manner for Evenly Distribution
     */
    @Test
    public void givenAVehicle_WhenParked_ShouldParkEvenly() {
        Vehicle vehicle1 = new Vehicle("car1", 1);
        Vehicle vehicle2 = new Vehicle("car2", 2);
        Vehicle vehicle3 = new Vehicle("car3", 3);
        Vehicle vehicle4 = new Vehicle("car4", 4);
        try {
            parkingLot.vehicleParking(vehicle1);
            parkingLot.vehicleParking(vehicle2);
            parkingLot.vehicleParking(vehicle3);
            parkingLot.vehicleUnparking(vehicle1);
            parkingLot.vehicleUnparking(vehicle3);
            parkingLot.vehicleParking(vehicle4);
            parkingLot.vehicleParking(vehicle1);
            Assert.assertEquals(3,parkingLot.getVehicleLocation(vehicle1));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

}

