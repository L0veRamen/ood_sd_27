package ca.bytetube._05_linkedIn;

public abstract class Notification {
    Account triggerUser;

    public Notification(Account triggerUser) {
        this.triggerUser = triggerUser;
    }

    public abstract void getNotification();
}
