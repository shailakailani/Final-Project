/**
 * Description: Class for comments which are updated to the calgov database
 * @author Shaila Lewis
 * @since 04.09.26
 */

package api.model;
import java.time.Instant;
import java.util.Date;
import java.util.ArrayList;

public class Comment {
    private String username;
    private Long id;
    private Instant date;
    private String message;
    private ArrayList<Comment> replies;

    /**
     * This is the constructor for initializing a comment where user left a username to input.
     * @param username name/username of commentor.
     * @param message text inputed by commentor.
     * @param id identifying digit for specific comment.
     */
    public Comment(String username, String message, Long id){
        this.username = username.replaceAll(" ", "").toLowerCase();
        this.message = message;
        this.id = id;
        this.date = Instant.now();
    }

    /**
     * This is the constructor for initializing a comment without an inputed username.
     * @param message text inputed by commentor.
     * @param id identifying digit for specific comment.
     */
    public Comment(String message, Long id){
        this.username = "anonymous";
        this.id = id;
        this.message = message;
    }

    /**
     * This method returns list of all comments under this specific post
     * @return ArrayList<Comment>
     */
    public ArrayList<Comment> getReplies(){
        return this.replies;
    }

    /**
     * This method returns username for this post's commentor, unless there isn't one.
     * @return String
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * This method returns message in this comment.
     * @return String
     */
    public String getMessage(){
        return this.message;
    }

    /**
     * This method returns date this comment was posted
     * @return Date
     */
    public Date getTime(){
        return Date.from(this.date);
    }

    /**
     * This method will return the identifiable number for this specific message
     * @return int
     */
    public Long getId(){
        return this.id;
    }

    /**
     * This method adds a comment to the list of comments under this particular comment.
     * @param reply Comment to be added
     */
    public void addReply(Comment reply){
        this.replies.add(reply);
    }

    /**
     * This method prints the information associated with this particular object.
     */
    public String toString(){
        StringBuilder str = new StringBuilder("Username: " + this.username);
        str.append("\nMessage: " + this.message);
        str.append("\nDate: " + getTime());
        str.append("Replies: " + this.replies.size());
        str.append("Comment ID: " + this.id);

        return str.toString();
    }
}
