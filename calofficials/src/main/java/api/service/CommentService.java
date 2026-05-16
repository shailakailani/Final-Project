/**
 * Service class for handling comment logic.
 * This class connects the comments controller to the comment repository.
 * @author Shaila Lewis, Kelvin Myat
 * @since 05.15.26
 */
package api.service;
import shai.kelv.calofficials.comments.entity.Comment;
import shai.kelv.calofficials.comments.repo.CommentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * Constructor for CommentService.
     * @param commentRepository repository used to access comment data
     */
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Finds one comment by its id.
     * @param id the unique id of the comment
     * @return the comment with the matching id, or null if no comment is found
     */
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    /**
     * Finds all comments that are replies to a specific parent comment.
     * @param id the id of the parent comment
     * @return list of comments under the parent comment
     */
    public List<Comment> getCommentsByParentId(Long id) {
        return commentRepository.findByParentId(id);
    }

    /**
     * Finds all main comments that are not replies.
     * @return list of top-level comments
     */
    public List<Comment> getComments() {
        return commentRepository.findByParentIdIsNull();
    }

    /**
     * Saves a new comment to the database.
     * @param username the username of the person posting the comment
     * @param message the comment message
     * @param parentId the id of the parent comment or null if it is a main comment
     * @return the saved comment
     */
    @Transactional(transactionManager= "commentsTransactionManager")
    public Comment saveComment(String username, String message, Long parentId){
        Comment comment = new Comment(parentId, username, message);
        return commentRepository.save(comment);
    }
}