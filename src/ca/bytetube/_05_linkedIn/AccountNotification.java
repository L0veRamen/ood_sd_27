package ca.bytetube._05_linkedIn;

public class AccountNotification extends Notification {
    Account targetUser;
    String action;

    public AccountNotification(Account triggerUser, Account targetUser, String action) {
        super(triggerUser);
        this.targetUser = targetUser;
        this.action = action;
    }

    @Override
    public void getNotification() {
        System.out.println(triggerUser.getUsername() + " has " + action + " you");
    }
}
