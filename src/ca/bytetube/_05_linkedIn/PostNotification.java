package ca.bytetube._05_linkedIn;

public class PostNotification extends Notification {
    Account postCreator;
    Post post;
    String action;

    public PostNotification(Account triggerUser, Account postCreator, Post post, String action) {
        super(triggerUser);
        this.postCreator = postCreator;
        this.post = post;
        this.action = action;
    }

    @Override
    public void getNotification() {
        System.out.println(triggerUser.getUsername() + " has " + action + " your post: " + post.getPostId());
    }
}
