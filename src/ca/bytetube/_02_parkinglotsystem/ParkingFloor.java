package ca.bytetube._02_parkinglotsystem;

import java.util.HashMap;
import java.util.Map;

/**
 * ParkingFloor represents a single floor in the parking lot system, where multiple parking spots exists.
 * It's responsible for managing the allocation and release of parking spots
 */
public class ParkingFloor {
    //0 = available, 1= occupied
    private int[] spots;
    private Map<Vehicle, int[]> vehicleMap;


    public ParkingFloor(int spotsCount) {
        spots = new int[spotsCount];
        vehicleMap = new HashMap<>();
    }

    //find a consecutive sequence of available parking spots on this floor and assigns them to the vehicle
    public boolean parkVehicle(Vehicle vehicle) {
        int size = vehicle.getSpotSize();
        int l = 0;
        int r = 0;
        while (r < spots.length) {
            if (spots[r] != 0) {
                l = r + 1;
            }
            if (r - l + 1 == size) {
                for (int k = l; k <= r; k++) {
                    spots[k] = 1;
                }
                vehicleMap.put(vehicle, new int[]{l, r});
                return true;
            }
            r++;
        }
        return false;
    }


    public int[] getVehicleSpots(Vehicle vehicle) {
        return vehicleMap.get(vehicle);

    }

    //release the parking spots occupied by the vehicle
    public boolean removeVehicle(Vehicle vehicle) {
        int[] startEnd = vehicleMap.get(vehicle);
        int start = startEnd[0];
        int end = startEnd[1];
        for (int k = start; k <= end; k++) {
            spots[k] = 0;
        }

        return vehicleMap.remove(vehicle) == null;
    }
}
