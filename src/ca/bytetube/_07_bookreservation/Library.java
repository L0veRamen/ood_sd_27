package ca.bytetube._07_bookreservation;

import java.util.List;
import java.util.Map;

public class Library {
    ResourceManager manager;

    public Library() {
        this.manager = new ResourceManager();
    }

    public void addResource(Resource resource) {
        manager.addResource(resource);
    }

    public void releaseResource(String resourceId, String name) {
        manager.releaseResource(resourceId, name);
    }


    public Resource getResource(String resourceId) {

        return manager.getResource(resourceId);
    }

    public List<Resource> search(String name) {
        return manager.search(name);
    }


    public boolean reserve(String resourceId, String name) {
        return manager.reserve(resourceId, name);
    }


}
