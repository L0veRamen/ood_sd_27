package ca.bytetube._13_elevator;

class ServiceRequest extends Request {
    private int weight;

    public ServiceRequest(RequestOrigin origin, int currentFloor, int destinationFloor) {
        super(origin, currentFloor, destinationFloor);
        this.elevatorType = ElevatorType.SERVICE;
    }

    public int getWeight() {
        return weight;
    }
}
