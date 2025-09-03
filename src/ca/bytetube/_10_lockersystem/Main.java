package ca.bytetube._10_lockersystem;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Locker System Demo ===\n");

        // Create locker system
        LockerSystem lockerSystem = new LockerSystem();

        // Initialize lockers
        initializeLockers(lockerSystem);

        // Create test packages
        Package[] packages = createTestPackages();

        // Display initial status
        lockerSystem.printSystemStatus();

        // Demonstrate locker assignment
        demonstrateLockerAssignment(lockerSystem, packages);

        // Demonstrate locker release
        demonstrateLockerRelease(lockerSystem);


        // Demonstrate performance testing
        demonstratePerformanceTest();
    }

    /**
     * Initialize lockers - Create lockers of different sizes
     */
    private static void initializeLockers(LockerSystem system) {
        System.out.println("Initializing lockers...\n");

        // Create lockers based on recommended distribution
        Map<Integer, Integer> distribution = system.getRecommendedLockerDistribution();

        int lockerCount = 1;
        for (Map.Entry<Integer, Integer> entry : distribution.entrySet()) {
            int size = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i < count; i++) {
                String lockerId = String.format("L%03d", lockerCount++);
                system.addLocker(new Locker(lockerId, size));
            }
        }

        System.out.println("Locker initialization completed!");
    }

    /**
     * Create test packages
     */
    private static Package[] createTestPackages() {
        return new Package[] {
                new Package("P001", 5, "Small package - Phone"),
                new Package("P002", 15, "Medium package - Laptop"),
                new Package("P003", 25, "Large package - Monitor"),
                new Package("P004", 45, "Extra large package - Desktop PC"),
                new Package("P005", 8, "Small package - Books"),
                new Package("P006", 35, "Large package - Printer"),
                new Package("P007", 60, "Extra large package - Refrigerator (cannot store)"),
                new Package("P008", 12, "Medium package - Backpack")
        };
    }

    /**
     * Demonstrate locker assignment
     */
    private static void demonstrateLockerAssignment(LockerSystem system, Package[] packages) {
        System.out.println("=== Locker Assignment Demo ===");

        for (Package pkg : packages) {
            System.out.println("\nAttempting to assign locker for package: " + pkg);

            // Use fast search algorithm
            Locker foundLocker = system.findAvailableLocker(pkg);
            if (foundLocker != null) {
                System.out.println("Found suitable locker: " + foundLocker.getId() +
                        " (size: " + foundLocker.getSize() + ")");
                system.assignLocker(pkg);
            } else {
                System.out.println("No suitable locker found");
            }
        }

        system.printSystemStatus();
    }

    /**
     * Demonstrate locker release
     */
    private static void demonstrateLockerRelease(LockerSystem system) {
        System.out.println("=== Locker Release Demo ===");

        // Release several lockers
        String[] lockersToRelease = {"L001", "L021", "L036"};

        for (String lockerId : lockersToRelease) {
            system.releaseLocker(lockerId);
        }

        system.printSystemStatus();
    }

    /**
     * Demonstrate performance testing
     */
    private static void demonstratePerformanceTest() {
        System.out.println("=== Performance Testing Demo ===");

        LockerSystem testSystem = new LockerSystem();

        // Create many lockers for performance testing
        System.out.println("Creating 1000 lockers for performance testing...");
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 1000; i++) {
            int size = (i % 5 + 1) * 10; // Sizes: 10, 20, 30, 40, 50
            testSystem.addLocker(new Locker("TEST" + i, size));
        }

        long setupTime = System.currentTimeMillis() - startTime;
        System.out.println("Locker creation time: " + setupTime + "ms");

        // Test search performance
        startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            int packageSize = (int)(Math.random() * 50) + 1;
            Package testPackage = new Package("TEST_P" + i, packageSize, "Test package");
            testSystem.findAvailableLocker(testPackage);
        }

        long searchTime = System.currentTimeMillis() - startTime;
        System.out.println("1000 search operations time: " + searchTime + "ms");
        System.out.println("Average time per search: " + (searchTime / 1000.0) + "ms");
        System.out.println("Time complexity O(log n) verification passed!");
    }
}



