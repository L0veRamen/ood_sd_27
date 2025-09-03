package ca.bytetube._10_lockersystem;

public class AdvancedMain {
    public static void main(String[] args) {
        System.out.println("=== Advanced Locker System Demo ===\n");
        
        // Create advanced locker system
        AdvancedLockerSystem advancedSystem = new AdvancedLockerSystem();
        
        // Initialize advanced system
        initializeAdvancedSystem(advancedSystem);
        
        // Demonstrate reservation system
        demonstrateReservationSystem(advancedSystem);
        
        // Demonstrate priority queue
        demonstratePriorityQueue(advancedSystem);
        
        // Demonstrate expiration cleanup
        demonstrateExpirationCleanup(advancedSystem);
    }
    
    /**
     * Initialize advanced system
     */
    private static void initializeAdvancedSystem(AdvancedLockerSystem system) {
        System.out.println("Initializing advanced locker system...\n");
        
        // Create lockers of various sizes
        String[] sizes = {"10", "20", "30", "50"};
        int[] counts = {3, 3, 2, 1}; // Intentionally create fewer lockers to demonstrate queuing functionality
        
        int lockerCount = 1;
        for (int i = 0; i < sizes.length; i++) {
            int size = Integer.parseInt(sizes[i]);
            for (int j = 0; j < counts[i]; j++) {
                String lockerId = String.format("LOC%03d", lockerCount++);
                system.addLocker(new Locker(lockerId, size));
            }
        }
        
        // Set shorter expiration time for demonstration (5 seconds)
        system.setDefaultExpirationTime(5000);
        
        system.printStatus();
    }
    
    /**
     * Demonstrate reservation system
     */
    private static void demonstrateReservationSystem(AdvancedLockerSystem system) {
        System.out.println("=== Reservation System Demo ===");
        
        // Create test packages
        Package pkg1 = new Package("RESERVE_1", 15, "Package requiring reservation 1");
        Package pkg2 = new Package("RESERVE_2", 25, "Package requiring reservation 2");
        
        // Reserve lockers (5 minute reservation period)
        String reservation1 = system.reserveLocker(pkg1, 3, 5 * 60 * 1000);
        String reservation2 = system.reserveLocker(pkg2, 2, 3 * 60 * 1000);
        
        system.printStatus();
        
        // Confirm one reservation
        if (reservation1 != null) {
            System.out.println("\nConfirming reservation: " + reservation1);
            system.confirmReservation(reservation1);
        }
        
        // Cancel another reservation
        if (reservation2 != null) {
            System.out.println("\nCancelling reservation: " + reservation2);
            system.cancelReservation(reservation2);
        }
        
        system.printStatus();
    }
    
    /**
     * Demonstrate priority queue
     */
    private static void demonstratePriorityQueue(AdvancedLockerSystem system) {
        System.out.println("\n=== Priority Queue Demo ===");
        
        // First fill all small lockers to ensure they are occupied
        System.out.println("Filling small lockers...");
        for (int i = 1; i <= 3; i++) {  // Only fill 3 small lockers
            Package pkg = new Package("FILL_" + i, 8, "Filler package " + i);
            system.assignLocker(pkg);
            System.out.println("FILL_" + i + " assigned to locker LOC00" + i);
        }
        
        // Assign one medium locker for subsequent demo preparation
        Package fillMedium = new Package("FILL_MEDIUM", 15, "Medium filler package");
        system.assignLocker(fillMedium);
        System.out.println("FILL_MEDIUM assigned to locker LOC004");
        
        system.printStatus();
        
        // Now try to reserve small lockers - should enter waiting queue
        Package vipPkg = new Package("VIP_1", 8, "VIP package - high priority");
        Package normalPkg = new Package("NORMAL_1", 8, "Normal package");
        Package premiumPkg = new Package("PREMIUM_1", 8, "Premium member package");
        
        // Reservations with different priorities
        System.out.println("\nAttempting to reserve small lockers...");
        
        // First try direct assignment, should fail (small lockers are full)
        System.out.println("Attempting direct assignment of VIP package...");
        Locker foundLocker = system.findAvailableLocker(vipPkg);
        if (foundLocker == null) {
            System.out.println("No small lockers available, VIP package will enter waiting queue");
        }
        
        // Now make reservations, should enter waiting queue
        String vipRequest = system.reserveLocker(vipPkg, 10, 10 * 60 * 1000);
        String normalRequest = system.reserveLocker(normalPkg, 1, 10 * 60 * 1000);
        String premiumRequest = system.reserveLocker(premiumPkg, 5, 10 * 60 * 1000);
        
        System.out.println("VIP request ID: " + vipRequest);
        System.out.println("Normal request ID: " + normalRequest);
        System.out.println("Premium request ID: " + premiumRequest);
        
        System.out.println("All small lockers are full, 3 requests entered waiting queue");
        
        // Release one locker, observe which request gets priority processing
        System.out.println("\nReleasing one small locker...");
        system.releaseLocker("LOC001");
        
        system.printStatus();
    }
    
    /**
     * Demonstrate expiration cleanup functionality
     */
    private static void demonstrateExpirationCleanup(AdvancedLockerSystem system) {
        System.out.println("\n=== Expiration Cleanup Demo ===");
        
        // Create a short-term reservation
        Package shortTermPkg = new Package("SHORT_1", 20, "Short-term package");
        String shortReservation = system.reserveLocker(shortTermPkg, 3, 2000); // 2 second expiration
        
        System.out.println("Created reservation expiring in 2 seconds: " + shortReservation);
        
        // Wait 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\nExecuting cleanup after 3 seconds...");
        system.cleanupExpiredItems();
        
        system.printStatus();
        
        // Now try to confirm expired reservation
        if (shortReservation != null) {
            System.out.println("Attempting to confirm expired reservation...");
            boolean success = system.confirmReservation(shortReservation);
            System.out.println("Confirmation result: " + (success ? "Success" : "Failed (reservation expired)"));
        }
    }
}
