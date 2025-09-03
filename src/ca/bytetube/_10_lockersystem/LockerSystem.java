package ca.bytetube._10_lockersystem;

import java.util.*;

/**
 * Locker System - Core class for efficiently finding suitable lockers
 * Core Design:
 * 1. Use TreeMap to store available lockers sorted by size
 * 2. Use ceilingKey method to quickly find the smallest suitable locker
 * 3. Time complexity: O(log n)
 */
public class LockerSystem {
    // All lockers in the system
    protected Map<String, Locker> allLockers;
    
    // Currently occupied lockers
    protected Map<String, Locker> occupiedLockers;
    
    // Available lockers organized by size for quick lookup
    protected TreeMap<Integer, List<Locker>> availableLockersBySize;

    public LockerSystem() {
        this.allLockers = new HashMap<>();
        this.occupiedLockers = new HashMap<>();
        this.availableLockersBySize = new TreeMap<>();
    }

    /**
     * Add a new locker to the system
     */
    public void addLocker(Locker locker) {
        allLockers.put(locker.getId(), locker);
        addToAvailableLockers(locker);
    }

    /**
     * Find an available locker for a package
     */
    public Locker findAvailableLocker(Package pkg) {
        Integer suitableSize = availableLockersBySize.ceilingKey(pkg.getSize());
        
        if (suitableSize == null) {
            return null; // No suitable locker available
        }
        
        List<Locker> lockersOfSize = availableLockersBySize.get(suitableSize);
        if (lockersOfSize == null || lockersOfSize.isEmpty()) {
            return null;
        }
        
        return lockersOfSize.get(0);
    }

    /**
     * Assign a locker to a package
     */
    public boolean assignLocker(Package pkg) {
        Locker availableLocker = findAvailableLocker(pkg);
        
        if (availableLocker == null) {
            System.out.println("No available locker found for package: " + pkg.getId());
            return false;
        }
        
        if (availableLocker.storePackage(pkg)) {
            occupiedLockers.put(availableLocker.getId(), availableLocker);
            removeFromAvailableLockers(availableLocker);
            
            System.out.println("Package " + pkg.getId() + " assigned to locker " + availableLocker.getId());
            return true;
        }
        
        return false;
    }

    /**
     * Release locker
     */
    public Package releaseLocker(String lockerId) {
        Locker locker = occupiedLockers.get(lockerId);
        
        if (locker == null) {
            System.out.println("Locker " + lockerId + " is not occupied");
            return null;
        }
        
        Package pkg = locker.retrievePackage();
        
        // Remove from occupied list
        occupiedLockers.remove(lockerId);
        // Re-add to available list
        addToAvailableLockers(locker);
        
        System.out.println("Released locker " + lockerId + ", retrieved package: " + pkg);
        return pkg;
    }

    /**
     * Add locker to available list
     */
    protected void addToAvailableLockers(Locker locker) {
        int size = locker.getSize();
        availableLockersBySize.computeIfAbsent(size, unused -> new ArrayList<>()).add(locker);
    }

    /**
     * Remove locker from available list
     */
    protected void removeFromAvailableLockers(Locker locker) {
        int size = locker.getSize();
        List<Locker> lockersOfSize = availableLockersBySize.get(size);
        
        if (lockersOfSize != null) {
            lockersOfSize.remove(locker);
            // If all lockers of this size are used up, remove the key
            if (lockersOfSize.isEmpty()) {
                availableLockersBySize.remove(size);
            }
        }
    }

    /**
     * Get system status statistics
     */
    public void printSystemStatus() {
        System.out.println("\n=== Locker System Status ===");
        System.out.println("Total lockers: " + allLockers.size());
        System.out.println("Occupied lockers: " + occupiedLockers.size());
        
        int totalAvailable = availableLockersBySize.values()
                                                  .stream()
                                                  .mapToInt(List::size)
                                                  .sum();
        System.out.println("Available lockers: " + totalAvailable);
        
        System.out.println("\nAvailable lockers by size distribution:");
        for (Map.Entry<Integer, List<Locker>> entry : availableLockersBySize.entrySet()) {
            System.out.println("  Size " + entry.getKey() + ": " + entry.getValue().size() + " units");
        }
        
        if (!occupiedLockers.isEmpty()) {
            System.out.println("\nOccupied lockers:");
            for (Locker locker : occupiedLockers.values()) {
                System.out.println("  " + locker);
            }
        }
        System.out.println("===========================\n");
    }

    /**
     * Find all available lockers within a specified size range
     */
    public List<Locker> findAvailableLockersByRange(int minSize, int maxSize) {
        List<Locker> result = new ArrayList<>();
        
        for (Map.Entry<Integer, List<Locker>> entry : availableLockersBySize.subMap(minSize, true, maxSize, true).entrySet()) {
            result.addAll(entry.getValue());
        }
        
        return result;
    }

    /**
     * Get recommended locker size distribution
     */
    public Map<Integer, Integer> getRecommendedLockerDistribution() {
        Map<Integer, Integer> distribution = new HashMap<>();
        distribution.put(10, 20);  // Small lockers - 20 units
        distribution.put(20, 15);  // Medium lockers - 15 units  
        distribution.put(30, 10);  // Large lockers - 10 units
        distribution.put(50, 5);   // Extra large lockers - 5 units
        
        return distribution;
    }
}
