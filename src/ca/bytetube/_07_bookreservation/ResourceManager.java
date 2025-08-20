package ca.bytetube._07_bookreservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceManager {
    private final Map<String, Resource> resources;
    private Map<String, User> users;

    public ResourceManager() {
        this.resources = new HashMap<>();
        this.users = new HashMap<>();
    }

    public void addResource(Resource resource) {
        resources.put(resource.getId(), resource);
    }

    public Resource getResource(String resourceId) {

        return resources.get(resourceId);
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    //    public void releaseResource(String resourceId, String name) {
//        Resource resource = resources.get(resourceId);
//        if (resource == null) return;
//
//        synchronized (resource) {
//
//            resource.setAvailableQuantity(resource.getAvailableQuantity() + 1);
//
//            String nextUser = resource.getWaitingList().peek();
//            if (nextUser != null && resource.getAvailableQuantity() > 0) {
//                if (reserve(resourceId, nextUser)) {
//                    resource.getWaitingList().poll();
//                }
//            }
//
//
//        }
//    }

    public boolean releaseAndPay(String resourceId, String userId) {
        Resource resource = resources.get(resourceId);
        User user = users.get(userId);
        if (resource == null || user == null) return false;

        synchronized (resource) {
            // Check if user has active rental
            if (!resource.hasActiveRental(userId)) {
                System.out.printf("%s has no rental to return for %s%n",
                        user.getName(), resource.getName());
                return false;
            }

            // Calculate fee
            double fee = resource.calculateFee(userId);

            // User tries to pay the fee
            boolean paymentSuccess = user.pay(fee);

            if (paymentSuccess) {
                // Payment successful - now actually end the rental
                resource.endRentalAndCalculateFee(userId); // This removes the rental
                resource.setAvailableQuantity(resource.getAvailableQuantity() + 1);
                System.out.printf("%s successfully returned %s%n",
                        user.getName(), resource.getName());

                // Process waiting list
                String nextUserId = resource.getWaitingList().peek();

                if (nextUserId != null && resource.getAvailableQuantity() > 0) {
                    User nextUser = users.get(nextUserId);

                    if (nextUser != null && reserve(resourceId, nextUserId, 1)) { // Default 1 day for waiting list
                        resource.getWaitingList().poll();
                        System.out.printf("%s from waiting list now has %s%n",
                                nextUser.getName(), resource.getName());
                    }
                }
                return true;
            } else {
                // Payment failed - rental remains active
                System.out.printf("%s cannot return %s due to insufficient funds%n",
                        user.getName(), resource.getName());
                return false;
            }
        }
    }


    public List<Resource> search(String name) {
        return resources.values().stream().filter(r -> r.getName().contains(name)).collect(Collectors.toList());
    }


//    public boolean reserve(String resourceId, String name) {
//        Resource resource = resources.get(resourceId);
//        if (resource == null) return false;
//
//        synchronized (resource) {
//            if (resource.getAvailableQuantity() > 0) {
//                resource.setAvailableQuantity(resource.getAvailableQuantity() - 1);
//                return true;
//            } else {
//                resource.getWaitingList().offer(name);
//                return false;
//            }
//        }
//
//
//    }

    public boolean reserve(String resourceId, String userId, int days) {
        Resource resource = resources.get(resourceId);
        User user = users.get(userId);
        if (resource == null || user == null) return false;

        synchronized (resource) {
            if (resource.getAvailableQuantity() > 0) {
                resource.setAvailableQuantity(resource.getAvailableQuantity() - 1);
                resource.startRental(userId, days);
                System.out.printf("%s reserved %s for %d days%n",
                        user.getName(), resource.getName(), days);
                return true;
            } else {
                resource.getWaitingList().offer(userId);
                System.out.printf("%s added to waiting list for %s%n",
                        user.getName(), resource.getName());
                return false;
            }
        }
    }


}
