package ca.bytetube._05_linkedIn;

import java.util.Scanner;

public class LinkedInSystem {
    AccountRegister accountRegister;
    PostRegister postRegister;

    NotificationRegister notificationRegister;

    public LinkedInSystem(AccountRegister accountRegister, PostRegister postRegister, NotificationRegister notificationRegister) {
        this.accountRegister = accountRegister;
        this.postRegister = postRegister;
        this.notificationRegister = notificationRegister;
    }

    public Account createUser(String userName) {
        Account user = accountRegister.createAccount(userName);
        notificationRegister.newAccountNotification(user);
        return user;
    }

    public Post createPost(Account user, String content) {
        return postRegister.addPost(content, user);
    }

    public void createComment(Post post, String content, Account commenter) {
        postRegister.addComments(post, content, commenter);
        Account postCreator = postRegister.findCreator(post);
        notificationRegister.createPostNotification(postCreator, commenter, post, "comment");
    }

    public void createLike(Post post, Account liker) {
        postRegister.addLikes(post, liker);
        Account postCreator = postRegister.findCreator(post);
        notificationRegister.createPostNotification(postCreator, liker, post, "like");
    }

    public void createFollow(Account followedAccount, Account followerAccount) {
        accountRegister.addRelationship(followedAccount, followerAccount, Status.FOLLOWED, Status.FOLLOWING);
        notificationRegister.createAccountNotification(followedAccount, followerAccount, "followed");
    }

    public void createConnect(Account connectedAccount, Account connectorAccount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(connectorAccount.getUsername() + " want to connect with " + connectedAccount.getUsername() + ". Does he/she accept?");
        int input = scanner.nextInt();
        scanner.close();
        if (input == 0) {
            System.out.println(connectedAccount.getUsername() + " doesn't want to connect with " + connectorAccount.getUsername());
            return;
        }

        accountRegister.addRelationship(connectedAccount, connectorAccount, Status.CONNECTED, Status.CONNECTED);
        notificationRegister.createAccountNotification(connectedAccount, connectorAccount, "connected");
        notificationRegister.createAccountNotification(connectorAccount, connectedAccount, "connected");
    }

    public void send(Account sender, Account recipient, Post post) {
        notificationRegister.createPostNotification(recipient, sender, post, "send");
    }

    public void getNotification(Account account) {
        notificationRegister.getNotification(account);
    }
}
