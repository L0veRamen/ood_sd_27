package ca.bytetube._05_linkedIn;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PostRegister {
    Map<Post, Account> posts = new HashMap<>();
    Map<Post, List<Comment>> comments = new HashMap<>();
    Map<Post, List<Account>> likes = new HashMap<>();


    public Post addPost(String content, Account user) {
        Post newPost = new Post(posts.size(), content);
        posts.put(newPost, user);
        return newPost;

    }


    public void addComments(Post post, String content, Account user) {
        List<Comment> commentList = new LinkedList<>();
        if (comments.containsKey(post)) {
            commentList = comments.get(post);
        }
        commentList.add(new Comment(content, user));
        comments.put(post, commentList);

    }


    public void addLikes(Post post, Account user) {
        List<Account> accountList = new LinkedList<>();
        if (likes.containsKey(post)) {
            accountList = likes.get(post);
        }
        accountList.add(user);
        likes.put(post, accountList);

    }


    public Account findCreator(Post post) {
        Account account = null;
        for (Map.Entry<Post, Account> entry : posts.entrySet()) {
            if (entry.getKey().getPostId() == post.getPostId()) {
                account = entry.getValue();
                break;
            }
        }
        return account;
    }


}
