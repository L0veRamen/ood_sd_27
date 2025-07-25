package ca.bytetube._02_parkinglotsystem;

/*ParkingGarage represents the entire parking lot system and consists of multiple parking floors
    Its purpose is to manage multiple floors and attempt to find parking spots across different floors
*/
public class ParkingGarage {
    private ParkingFloor[] parkingFloors;

    public ParkingGarage(int floorCount, int spotsFloor) {
        parkingFloors = new ParkingFloor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            parkingFloors[i] = new ParkingFloor(spotsFloor);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : parkingFloors) {
            if (floor.parkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : parkingFloors) {
            if (floor.getVehicleSpots(vehicle) != null) {
                floor.removeVehicle(vehicle);
                return true;
            }
        }
        return false;
    }
}
