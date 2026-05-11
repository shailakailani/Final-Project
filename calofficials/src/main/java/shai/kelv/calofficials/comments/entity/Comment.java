/**
 * @author Shaila Lewis
 * @since 04.30.26
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
@Table(name="COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="PARENT_ID", nullable=true, unique=false)
    private Long parentId;
    @Column(name="USERNAME", nullable=true, unique=false)
    private String username;
    @Column(name="MESSAGE", nullable=false)
    private String message;
    @Column(name="DATE")
    private LocalDateTime date;

    public Comment(Long parentId, String username, String message){
        this.parentId = parentId;
        this.username = username;
        this.message = message;
        this.date = LocalDateTime.now();
    }

  /**
     * Gets the comment id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the comment id.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the parent comment id.
     * @return the parent id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets the parent comment id.
     * @param parentId the new parent id
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
     * Gets the message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the date.
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date.
     * @param date the new date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}