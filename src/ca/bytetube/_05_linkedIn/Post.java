package ca.bytetube._05_linkedIn;

public class Post {
    int postId;
    String info;

    public Post(int postId, String info) {
        this.postId = postId;
        this.info = info;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
