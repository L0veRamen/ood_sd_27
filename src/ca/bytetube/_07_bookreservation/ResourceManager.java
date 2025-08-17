package ca.bytetube._07_bookreservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceManager {
    private Map<String, Resource> resources;

    public ResourceManager() {
        this.resources = new HashMap<>();
    }

    public void addResource(Resource resource) {
        resources.put(resource.getId(), resource);
    }

    public Resource getResource(String resourceId) {

        return resources.get(resourceId);
    }

    public void releaseResource(String resourceId, String name) {
        Resource resource = resources.get(resourceId);
        if (resource == null) return;

        synchronized (resource) {

            resource.setAvailableQuantity(resource.getAvailableQuantity() + 1);

            String nextUser = resource.getWaitingList().peek();
            if (nextUser != null && resource.getAvailableQuantity() > 0) {
                if (reserve(resourceId, nextUser)) {
                    resource.getWaitingList().poll();
                }
            }


        }
    }


    public List<Resource> search(String name) {
        return resources.values().stream().filter(r -> r.getName().contains(name)).collect(Collectors.toList());
    }


    public boolean reserve(String resourceId, String name) {
        Resource resource = resources.get(resourceId);
        if (resource == null) return false;

        synchronized (resource) {
            if (resource.getAvailableQuantity() > 0) {
                resource.setAvailableQuantity(resource.getAvailableQuantity() - 1);
                return true;
            } else {
                resource.getWaitingList().offer(name);
                return false;
            }
        }


    }


}
