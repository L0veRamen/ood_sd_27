package ca.bytetube._07_bookreservation;

import java.util.List;

public class Library {
    ResourceManager manager;

    public Library() {
        this.manager = new ResourceManager();
    }

    public void addResource(Resource resource) {
        manager.addResource(resource);
    }

    public void addUser(User user) {
        manager.addUser(user);
    }

    public User getUser(String userId) {
        return manager.getUser(userId);
    }

//    public void releaseResource(String resourceId, String name) {
//        manager.releaseResource(resourceId, name);
//    }


    public Resource getResource(String resourceId) {

        return manager.getResource(resourceId);
    }

    public List<Resource> search(String name) {
        return manager.search(name);
    }


    //    public boolean reserve(String resourceId, String name) {
//        return manager.reserve(resourceId, name);
//    }

    public boolean reserve(String resourceId, String userId, int days) {
        return manager.reserve(resourceId, userId, days);
    }

    public boolean releaseAndPay(String resourceId, String userId) {
        return manager.releaseAndPay(resourceId, userId);
    }



}
