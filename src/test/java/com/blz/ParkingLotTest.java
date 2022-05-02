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
 * UC11- As a parking lot Owner I want a parking attendant to direct large cars to the lot which has
 * the highest number of free space So that it is easier to manoeuvre large cars
 * UC12 - Police department wants location of all parked white cars
 * UC13 - Police department wants location and plate number of all parked blue toyota cars
 * @File : Parking Lot TDD Problem
 * @Author : Akshay Kumar & Shardul Kumbhar
 */
package com.blz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

public class ParkingLotTest {
    ParkingLot parkingLot = null;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot();
    }

    /**
     * UC1- Given vehicle when parked should return true
     */
    @Test
    public void givenVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("car", 1);
        try {
            parkingLot.vehicleParking(vehicle, DriverType.NORMAL,CarType.SMALL);
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
            parkingLot.vehicleParking(vehicle, DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL,CarType.SMALL);
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
            parkingLot.vehicleParking(vehicle,DriverType.NORMAL,CarType.SMALL);
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
        Vehicle vehicle = new Vehicle("car1", 1);
        try {
            parkingLot.vehicleUnparking(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Please ask correct vehicle", e.getMessage());
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
            parkingLot.vehicleParking(vehicle,DriverType.NORMAL,CarType.SMALL);
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
            parkingLot.vehicleParking(vehicle1,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(vehicle2,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car3", 3),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car4", 4),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car5", 5),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car6", 6),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car7", 7),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car8", 8),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car9", 9),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car10", 10),DriverType.NORMAL,CarType.SMALL);
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
            parkingLot.vehicleParking(vehicle1,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(vehicle2,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car3", 3),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car4", 4),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car5", 5),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car6", 6),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car7", 7),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car8", 8),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car9", 9),DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(new Vehicle("car10", 10),DriverType.NORMAL,CarType.SMALL);
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
            parkingLot.vehicleParking(vehicle,DriverType.NORMAL,CarType.SMALL);
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
            parkingLot.vehicleParking(vehicle1,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(vehicle2,DriverType.NORMAL,CarType.SMALL);
            int vehicleLotNumber = parkingLot.getVehicleLotNumber(vehicle2);
            Assert.assertEquals(7, vehicleLotNumber);
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
            parkingLot.vehicleParking(vehicle1,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(vehicle2,DriverType.NORMAL,CarType.SMALL);
            int key = parkingLot.getVehicleLocation(vehicle2);
            Assert.assertEquals(7, key);
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
            parkingLot.vehicleParking(vehicle1,DriverType.NORMAL,CarType.SMALL);
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
            parkingLot.vehicleParking(vehicle1,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(vehicle2,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(vehicle3,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleUnparking(vehicle1);
            parkingLot.vehicleUnparking(vehicle3);
            parkingLot.vehicleParking(vehicle4,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(vehicle1,DriverType.NORMAL,CarType.SMALL);
            Assert.assertEquals(8,parkingLot.getVehicleLocation(vehicle1));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * @UC10- As handicap driver I want the parking attendant to park my car to a lot
     * which has the nearest free space So that I don’t have to go far for unparking my car
     */
    @Test
    public void givenAVehicle_WhenDriverTypeMentioned_ShouldParkAccordingly() {
        Vehicle vehicle1 = new Vehicle("car1", 1);
        Vehicle vehicle2 = new Vehicle("car2", 2);
        Vehicle vehicle3 = new Vehicle("car3", 3);

        try {
            parkingLot.vehicleParking(vehicle1,DriverType.NORMAL,CarType.SMALL);
            parkingLot.vehicleParking(vehicle2,DriverType.HANDICAP,CarType.SMALL);
            parkingLot.vehicleParking(vehicle3,DriverType.HANDICAP,CarType.SMALL);
            int key1 = parkingLot.getVehicleLocation(vehicle1);//6
            int key2 = parkingLot.getVehicleLocation(vehicle2);//1
            int key3 = parkingLot.getVehicleLocation(vehicle3);//1
            Assert.assertEquals(6,key1);
            Assert.assertEquals(1,key2);
            Assert.assertEquals(2,key3);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * @UC11- As a parking lot Owner I want a parking attendant to direct large cars to the lot which has
     * the highest number of free space So that it is easier to manoeuvre large cars
     */
    @Test
    public void givenTwoParkingMaps_WhenLargeVehicleComes_ShouldParkAtLotHavingLargeSpace() {
        Vehicle vehicle1 = new Vehicle("car1", 1);
        Vehicle vehicle2 = new Vehicle("car2", 2);
        Vehicle vehicle3 = new Vehicle("car3", 3);
        try {
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);//6
            parkingLot.vehicleParking(vehicle2, DriverType.HANDICAP, CarType.SMALL);//1
            parkingLot.vehicleParking(vehicle3, DriverType.HANDICAP, CarType.LARGE);//1
            int key1 = parkingLot.getVehicleLocation(vehicle1);//6
            int key2 = parkingLot.getVehicleLocation(vehicle2);//1
            int key3 = parkingLot.getVehicleLocation(vehicle3);//1
            Assert.assertEquals(6, key1);
            Assert.assertEquals(1, key2);
            Assert.assertEquals(1, key3);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     *@UC12 - Police department wants location of all parked white cars
     */
    @Test
    public void givenAParkingLot_WhenWhiteCarsFound_ShouldInformPoliceDepartment() {
        Vehicle vehicle1 = new Vehicle("car1", 1, "white");
        Vehicle vehicle2 = new Vehicle("car2", 2,"white");
        Vehicle vehicle3 = new Vehicle("car3", 3,"white");
        Vehicle vehicle4 = new Vehicle("car4", 4,"blue");
        try {
            List<Vehicle> expectedList = new ArrayList<>(Arrays.asList(vehicle2, vehicle3, vehicle1));
            List<Integer> expectedLotNumberList = new ArrayList<>(Arrays.asList(1,2,6));
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);//6
            parkingLot.vehicleParking(vehicle2, DriverType.HANDICAP, CarType.SMALL);//1
            parkingLot.vehicleParking(vehicle3, DriverType.HANDICAP, CarType.SMALL);//2
            parkingLot.vehicleParking(vehicle4, DriverType.HANDICAP, CarType.SMALL);//3
            List<Vehicle> actualList = parkingLot.getVehicleByColor("white");
            Assert.assertEquals(expectedList,actualList);
            List<Integer> actualLotNumberList = parkingLot.getVehicleLotNumberByColor("white");
            Assert.assertEquals(expectedLotNumberList,actualLotNumberList);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     *@UC13 - Police department wants location and plate number of all parked blue toyota cars
     */
    @Test
    public void givenAParkingLot_WhenBlueToyotaFound_ShouldReturnLocationAndPlateNumber() {
        Vehicle vehicle1 = new Vehicle("car1", 1, "white", "MH-22-GE338");
        Vehicle vehicle2 = new Vehicle("toyota", 2,"blue", "MH-16-HS347");
        Vehicle vehicle3 = new Vehicle("car3", 3,"white", "MH-04-ND466");
        Vehicle vehicle4 = new Vehicle("toyota", 4,"blue", "MH-02-DK9900");
        try {
            parkingLot.vehicleParking(vehicle1, DriverType.NORMAL, CarType.SMALL);//6
            parkingLot.vehicleParking(vehicle2, DriverType.NORMAL, CarType.SMALL);//7
            parkingLot.vehicleParking(vehicle3, DriverType.HANDICAP, CarType.SMALL);//1
            parkingLot.vehicleParking(vehicle4, DriverType.HANDICAP, CarType.SMALL);//2
            List<Integer> lotNumberList = parkingLot.getVehicleLotNumberByColorAndModelName("blue", "toyota");
            Assert.assertEquals(Arrays.asList(2,7),lotNumberList);
            List<String> vehicleNumberPlate = parkingLot.getVehicleNumberPlate(lotNumberList);
            Assert.assertEquals(Arrays.asList("MH-02-DK9900","MH-16-HS347"),vehicleNumberPlate);
            int key = parkingLot.getVehicleLocation(vehicle4);
            String vehicleNumberPlateBylotNumber = parkingLot.getVehicleNumberPlateBylotNumber(key);
            Assert.assertEquals("MH-02-DK9900",vehicleNumberPlateBylotNumber);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}

