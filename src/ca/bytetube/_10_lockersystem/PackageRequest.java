package ca.bytetube._10_lockersystem;

/**
 * Package Request class - represents a request for locker allocation
 */
public class PackageRequest {
    private String requestId;
    private Package pkg;
    private int priority;
    private long requestTime;
    
    public PackageRequest(String requestId, Package pkg, int priority) {
        this.requestId = requestId;
        this.pkg = pkg;
        this.priority = priority;
        this.requestTime = System.currentTimeMillis();
    }
    
    public String getRequestId() {
        return requestId;
    }
    
    public Package getPackage() {
        return pkg;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public long getRequestTime() {
        return requestTime;
    }
    
    @Override
    public String toString() {
        return String.format("PackageRequest[id=%s, package=%s, priority=%d, time=%d]", 
                           requestId, pkg.getId(), priority, requestTime);
    }
}
