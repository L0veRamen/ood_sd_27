package ca.bytetube._02_parkinglotsystem;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * ParkingSystem represents the overall operational management system
 * it relies on ParkingGarage to manage parking operations and also introduce a billing system
 */
public class ParkingSystem {
    private ParkingGarage parkingGarage;
    private Map<Integer, Integer> timeParked;
    private int hourRate;

    public ParkingSystem(ParkingGarage parkingGarage, int hourRate) {
        this.parkingGarage = parkingGarage;
        this.hourRate = hourRate;
        timeParked = new HashMap<>();
    }

    public boolean parkVehicle(Driver driver) {
        int currHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        boolean isParked = parkingGarage.parkVehicle(driver.getVehicle());
        if (isParked) {
            timeParked.put(driver.getId(), currHour);
        }

        return isParked;
    }

    //calculates parking fees,charges driver,and release the parking lot in parkingGarage
    public boolean removeVehicle(Driver driver) {
        if (!timeParked.containsKey(driver.getId())) {
            return false;
        }
        int end = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int start = timeParked.get(driver.getId());
        int duration = (end - start);
        driver.charge(duration * hourRate);
        System.out.println("driver payment due is " + driver.getPaymentDue());
        timeParked.remove(driver.getId());
        return parkingGarage.removeVehicle(driver.getVehicle());
    }
}
