package ca.bytetube._02_parkinglotsystem;

public class Main {
    public static void main(String[] args) {
        ParkingGarage garage = new ParkingGarage(2, 3);
        ParkingSystem system = new ParkingSystem(garage, 10);
        Driver driver1 = new Driver(1, new Car());
        Driver driver2 = new Driver(2, new Limos());
        Driver driver3 = new Driver(3, new SemiTrucks());

        System.out.println(system.parkVehicle(driver1));
        System.out.println(system.parkVehicle(driver2));
        System.out.println(system.parkVehicle(driver3));
        System.out.println(system.parkVehicle(driver3));

        System.out.println(system.removeVehicle(driver1));
        System.out.println(system.removeVehicle(driver2));
        System.out.println(system.parkVehicle(driver3));
        System.out.println(system.removeVehicle(driver3));
        System.out.println(system.removeVehicle(driver1));

    }
}
