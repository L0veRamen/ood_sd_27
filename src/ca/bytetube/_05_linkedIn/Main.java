package ca.bytetube._05_linkedIn;


public class Main {
    public static void main(String[] args) {
        AccountRegister accountRegister = new AccountRegister();
        PostRegister postRegister = new PostRegister();
        NotificationRegister notificationRegister = new NotificationRegister();

        LinkedInSystem linkedIn = new LinkedInSystem(accountRegister, postRegister, notificationRegister);
        Account alice = linkedIn.createUser("Alice");
        Account bob = linkedIn.createUser("Bob");
        Account claire = linkedIn.createUser("Claire");

        linkedIn.createFollow(alice, bob);
        linkedIn.createConnect(alice, claire);
        Post postAlice = linkedIn.createPost(alice, "today is a nice day!");
        Post postBob = linkedIn.createPost(bob, "cold!");
        linkedIn.createLike(postAlice, claire);
        linkedIn.createComment(postBob, "agree", claire);
        linkedIn.send(alice, claire, postBob);

        linkedIn.getNotification(alice);
        linkedIn.getNotification(bob);
        linkedIn.getNotification(claire);
    }
}
