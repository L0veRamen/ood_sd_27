package ca.bytetube._10_lockersystem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class AdvancedLockerSystem extends LockerSystem {

    //thread-safe concurrent control
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    private Map<String, LockerReservation> reservations;

    private PriorityQueue<PackageRequest> priorityQueue;

    private long defaultExpirationTime = 24 * 60 * 60 * 1000; // 24 hours


    private static final AtomicLong reservationCounter = new AtomicLong(0);
    private static final AtomicLong requestCounter = new AtomicLong(0);

    public AdvancedLockerSystem() {
        super();
        this.reservations = new ConcurrentHashMap<>();
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingInt(PackageRequest::getPriority).reversed());
    }

    /**
     * Reserve a locker
     */
    public String reserveLocker(Package pkg, int priority, long reservationDurationMs) {
        lock.writeLock().lock();
        try {
            Locker availableLocker = findAvailableLocker(pkg);
            if (availableLocker == null) {
                String requestId = "REQ_" + System.currentTimeMillis() + "_" +
                        requestCounter.incrementAndGet();
                PackageRequest request = new PackageRequest(requestId, pkg, priority);
                priorityQueue.offer(request);

                System.out.println("No available locker, added to waiting queue, request ID: " + requestId);
                return requestId;
            }

            String reservationId = "RES_" + System.currentTimeMillis() + "_" +
                    reservationCounter.incrementAndGet();
            LockerReservation reservation = new LockerReservation(
                    reservationId,
                    availableLocker.getId(),
                    pkg,
                    System.currentTimeMillis() + reservationDurationMs
            );
            reservations.put(reservationId, reservation);

            removeFromAvailableLockers(availableLocker);
            System.out.println("Reservation successful! Reservation ID: " + reservationId +
                    ", Locker: " + availableLocker.getId() +
                    ", Please confirm within " + (reservationDurationMs / 1000 / 60) + " minutes");

            return reservationId;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Confirm reservation and assign locker
     */
    public boolean confirmReservation(String reservationId) {
        lock.writeLock().lock();
        try {
            LockerReservation reservation = reservations.get(reservationId);

            if (reservation == null) {
                System.out.println("Reservation does not exist: " + reservationId);
                return false;
            }

            if (reservation.isExpired()) {
                System.out.println("Reservation has expired: " + reservationId);
                cancelReservation(reservationId);
                return false;
            }

            Locker locker = allLockers.get(reservation.getLockerId());
            if (locker != null && locker.storePackage(reservation.getPackage())) {
                occupiedLockers.put(locker.getId(), locker);
                reservations.remove(reservationId);

                System.out.println("Reservation confirmed successfully! Locker " + locker.getId() + " has been assigned");
                return true;
            }

            return false;

        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Cancel reservation
     */
    public boolean cancelReservation(String reservationId) {
        lock.writeLock().lock();
        try {
            LockerReservation reservation = reservations.get(reservationId);

            if (reservation == null) {
                System.out.println("Reservation does not exist: " + reservationId);
                return false;
            }

            Locker locker = allLockers.get(reservation.getLockerId());
            if (locker != null) {
                addToAvailableLockers(locker);
            }

            reservations.remove(reservationId);
            System.out.println("Reservation cancelled: " + reservationId);
            return true;

        } finally {
            lock.writeLock().unlock();
        }
    }

    private void processWaitingQueue() {
        if (priorityQueue.isEmpty()) {
            return;
        }
        //a queue to hold that can not be processed yet
        List<PackageRequest> unprocessedRequests = new ArrayList<>();
        boolean anyAssigned = false;
        int processedCount = 0;

        while (!priorityQueue.isEmpty()) {
            PackageRequest request = priorityQueue.poll();
            Locker availableLocker = findAvailableLocker(request.getPackage());

            if (availableLocker != null) {

                if (availableLocker.storePackage(request.getPackage())) {
                    occupiedLockers.put(availableLocker.getId(), availableLocker);

                    System.out.println("Auto-assigned locker " + availableLocker.getId() +
                            " to waiting request " + request.getRequestId() +
                            " (priority: " + request.getPriority() + ")");

                    notifyUser(request.getRequestId(), availableLocker.getId());
                    anyAssigned = true;
                    processedCount++;
                } else {
                    unprocessedRequests.add(request);
                }
            } else {
                unprocessedRequests.add(request);
            }
        }
        //provide a fair allocation to prevent starvation
        for (PackageRequest request : unprocessedRequests) {
            priorityQueue.offer(request);
        }

        if (anyAssigned) {
            System.out.println("Waiting queue processing completed - Successfully assigned " + processedCount +
                    " lockers, " + unprocessedRequests.size() + " requests still waiting");
        }
    }

    /**
     * Clean up expired reservations and lockers
     */
    public void cleanupExpiredItems() {
        lock.writeLock().lock();
        try {
            long currentTime = System.currentTimeMillis();

            List<String> expiredReservations = new ArrayList<>();
            for (Map.Entry<String, LockerReservation> entry : reservations.entrySet()) {
                if (entry.getValue().isExpired()) {
                    expiredReservations.add(entry.getKey());
                }
            }

            for (String reservationId : expiredReservations) {
                cancelReservation(reservationId);
            }

            List<String> expiredLockers = new ArrayList<>();
            for (Map.Entry<String, Locker> entry : occupiedLockers.entrySet()) {
                Locker locker = entry.getValue();
                if (currentTime - locker.getAssignedTime() > defaultExpirationTime) {
                    expiredLockers.add(locker.getId());
                }
            }

            for (String lockerId : expiredLockers) {
                System.out.println("Locker " + lockerId + " usage timeout, auto-released");
                releaseLocker(lockerId);
            }

            System.out.println("Cleanup completed - Expired reservations: " + expiredReservations.size() +
                    ", Expired lockers: " + expiredLockers.size());

        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Get advanced features status information
     */
    public void printStatus() {
        lock.readLock().lock();
        try {
            System.out.println("=== Advanced Features Status ===");
            System.out.println("Active reservations: " + reservations.size());
            System.out.println("Waiting queue length: " + priorityQueue.size());

            if (!reservations.isEmpty()) {
                System.out.println("\nActive reservations:");
                for (LockerReservation reservation : reservations.values()) {
                    System.out.println("  " + reservation);
                }
            }

            System.out.println("==================");
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Notify user
     */
    private void notifyUser(String requestId, String lockerId) {
        System.out.println("Notification: Request " + requestId + " locker " + lockerId + " is ready");
    }

    /**
     * Override release locker method, automatically process waiting queue
     */
    @Override
    public Package releaseLocker(String lockerId) {
        Package pkg = super.releaseLocker(lockerId);

        if (pkg != null) {
            // After locker is released, automatically process waiting queue
            System.out.println("Locker " + lockerId + " released, automatically processing waiting queue...");
            processWaitingQueue();
        }

        return pkg;
    }

    @Override
    public Locker findAvailableLocker(Package pkg) {
        int packageSize = pkg.getSize();
        Integer suitableSize = availableLockersBySize.ceilingKey(packageSize);

        if (suitableSize == null) {
            return null;
        }

        if (suitableSize > packageSize * 2) {
            return null;
        }

        List<Locker> lockersOfSize = availableLockersBySize.get(suitableSize);
        if (lockersOfSize == null || lockersOfSize.isEmpty()) {
            return null;
        }

        return lockersOfSize.get(0);
    }


    public void setDefaultExpirationTime(long expirationTimeMs) {
        this.defaultExpirationTime = expirationTimeMs;
    }
}
