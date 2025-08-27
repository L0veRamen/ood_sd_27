package ca.bytetube._10_lockersystem;


public class Locker {
    private String id;
    private int size;
    private boolean isOccupied;
    private Package storedPackage;
    private long assignedTime;
    
    public Locker(String id, int size) {
        this.id = id;
        this.size = size;
        this.isOccupied = false;
        this.storedPackage = null;
        this.assignedTime = 0;
    }
    

    public boolean canStore(int packageSize) {
        return !isOccupied && size >= packageSize;
    }

    public boolean storePackage(Package pkg) {
        if (canStore(pkg.getSize())) {
            this.storedPackage = pkg;
            this.isOccupied = true;
            this.assignedTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
    

    public Package retrievePackage() {
        Package pkg = this.storedPackage;
        this.storedPackage = null;
        this.isOccupied = false;
        this.assignedTime = 0;
        return pkg;
    }
    

    public String getId() {
        return id;
    }
    
    public int getSize() {
        return size;
    }

    
    @Override
    public String toString() {
        return String.format("Locker[id=%s, size=%d, occupied=%s, package=%s]", 
                           id, size, isOccupied, storedPackage != null ? storedPackage.getId() : "null");
    }
}



