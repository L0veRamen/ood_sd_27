package ca.bytetube._10_lockersystem;

/**
 * Locker Reservation class - represents a reservation for a locker
 */
public class LockerReservation {
    private String reservationId;
    private String lockerId;
    private Package pkg;
    private long expirationTime;
    
    public LockerReservation(String reservationId, String lockerId, Package pkg, long expirationTime) {
        this.reservationId = reservationId;
        this.lockerId = lockerId;
        this.pkg = pkg;
        this.expirationTime = expirationTime;
    }
    
    public String getReservationId() {
        return reservationId;
    }
    
    public String getLockerId() {
        return lockerId;
    }
    
    public Package getPackage() {
        return pkg;
    }
    
    public long getExpirationTime() {
        return expirationTime;
    }
    
    public boolean isExpired() {
        return System.currentTimeMillis() > expirationTime;
    }
    
    @Override
    public String toString() {
        return String.format("LockerReservation[id=%s, locker=%s, package=%s, expires=%d]", 
                           reservationId, lockerId, pkg.getId(), expirationTime);
    }
}
