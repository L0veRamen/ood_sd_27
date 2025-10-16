package ca.bytetube._19_amazongo;

import java.util.HashMap;
import java.util.Map;

public class TrackingRegister {
    Map<String, Session> sessions = new HashMap<>();

    public Session start(Customer customer) {
        Session s = new Session("S-" + System.nanoTime(), customer);
        sessions.put(s.getId(), s);
        return s;
    }

    public void pickup(String sessionId, Product product, int qty) {
        Session s = sessions.get(sessionId);
        if (s != null) s.getBasket().add(product, qty);
    }

    public void putback(String sessionId, Product product, int qty) {
        Session s = sessions.get(sessionId);
        if (s != null) s.getBasket().add(product, -qty);
    }
}
