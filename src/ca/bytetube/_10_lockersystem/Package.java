package ca.bytetube._10_lockersystem;

public class Package {
    private String id;
    private int size;
    private String description;
    
    public Package(String id, int size, String description) {
        this.id = id;
        this.size = size;
        this.description = description;
    }
    
    public String getId() {
        return id;
    }
    
    public int getSize() {
        return size;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return String.format("Package[id=%s, size=%d, desc=%s]", id, size, description);
    }
}



