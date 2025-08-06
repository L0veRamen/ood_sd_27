package ca.bytetube._05_linkedIn;

import java.util.HashMap;
import java.util.Map;

public class AccountRegister {
    Map<Account, Map<Account, Status>> relationships = new HashMap<>();


    public Account createAccount(String name) {
        Account newUser = new Account(relationships.size(), name);
        Map<Account, Status> relation = new HashMap<>();
        relationships.put(newUser, relation);
        return newUser;
    }


    public void addRelationship(Account target, Account actor, Status targetStatus, Status actorStatus) {
        Map<Account, Status> actorRelationships = relationships.get(actor);
        Map<Account, Status> targetRelationships = relationships.get(target);
        targetRelationships.put(actor, targetStatus);
        actorRelationships.put(target, actorStatus);
        relationships.put(target, targetRelationships);
        relationships.put(actor, actorRelationships);

    }
}
