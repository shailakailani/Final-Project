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
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="parent_id", nullable=true, unique=false)
    private Long parentId;
    @Column(name="username", nullable=true, unique=false)
    private String username;
    @Column(name="message", nullable=false)
    private String message;
    @Column(name="date")
    private LocalDateTime date;

    public Comment(Long parentId, String username, String message){
        this.parentId = parentId;
        this.username = username;
        this.message = message;
        this.date = LocalDateTime.now();
    }
}
