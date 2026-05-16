/**
 * Represents a comment posted by a user on the comments page.
 * A comment can be a main comment or a reply if it has a parent comment id.
 * @author Shaila Lewis, Kelvin Myat
 * @since 05.15.26
 */
package shai.kelv.calofficials.comments.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id", nullable = true, unique = false)
    private Long parentId;

    @Column(name = "username", nullable = true, unique = false)
    private String username;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date")
    private LocalDateTime date;

    /**
     * Default constructor required by JPA.
     */
    protected Comment() {
    }

    /**
     * Creates a new comment with a parent id, username and message.
     * @param parentId the id of the parent comment, or null if it is a main comment
     * @param username the username of the person posting the comment
     * @param message the comment message
     */
    public Comment(Long parentId, String username, String message) {
        this.parentId = parentId;
        this.username = username;
        this.message = message;
        this.date = LocalDateTime.now();
    }

    /**
     * Gets the comment id.
     * @return the comment id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the comment id.
     * @param id the new comment id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the parent comment id.
     * @return the parent comment id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets the parent comment id.
     * @param parentId the new parent comment id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the comment message.
     * @return the comment message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the comment message.
     * @param message the new comment message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the date and time the comment was created.
     * @return the comment date and time
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date and time of the comment.
     * @param date the new comment date and time
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}