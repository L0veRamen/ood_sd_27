package ca.bytetube._05_linkedIn;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NotificationRegister {
    Map<Account, List<Notification>> notifications = new HashMap<>();

    public void newAccountNotification(Account user) {
        List<Notification> list = new LinkedList<>();
        notifications.put(user, list);
    }

    public void createPostNotification(Account postCreator, Account actor, Post post, String action) {
        List<Notification> creatorNotification = notifications.get(postCreator);
        creatorNotification.add(new PostNotification(actor, postCreator, post, action));
        notifications.put(postCreator, creatorNotification);
    }

    public void createAccountNotification(Account target, Account actor, String action) {
        List<Notification> notificationList = notifications.get(target);
        notificationList.add(new AccountNotification(actor, target, action));
        notifications.put(target, notificationList);
    }


    public void getNotification(Account account) {
        List<Notification> notificationList = notifications.get(account);
        for (Notification notification : notificationList) {
            notification.getNotification();
        }
    }


}
