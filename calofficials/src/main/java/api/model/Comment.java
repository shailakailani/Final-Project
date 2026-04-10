/**
 * Description:
 * @author Shaila Lewis
 * @since 04.09.26
 */

package api.model;
import java.time.Instant;
import java.util.Date;
import java.util.ArrayList;

public class Comment {
    private String username;
    private int id;
    private Instant date;
    private String message;
    private ArrayList<Comment> replies;

    public Comment(String username, String message, int id){
        this.username = username.replaceAll(" ", "").toLowerCase();
        this.message = message;
        this.id = id;
        this.date = Instant.now();
    }

    public Comment(String message, int id){
        this.username = "anonymous";
        this.id = id;
        this.message = message;
    }

    public ArrayList<Comment> getReplies(){
        return this.replies;
    }

    public String getUsername(){
        return this.username;
    }

    public String getMessage(){
        return this.message;
    }

    public Date getTime(){
        return Date.from(this.date);
    }

    public int getId(){
        return this.id;
    }

    public void addReply(Comment reply){
        this.replies.add(reply);
    }

    public String toString(){
        StringBuilder str = new StringBuilder("Username: " + this.username);
        str.append("\nMessage: " + this.message);
        str.append("\nDate: " + getTime());
        str.append("Replies: " + this.replies.size());
        str.append("Comment ID: " + this.id);

        return str.toString();
    }
}
