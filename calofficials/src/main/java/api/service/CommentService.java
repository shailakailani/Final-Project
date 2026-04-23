/**
 * Description: Logic for filtering through comments
 * @author Shaila Lewis
 * @date 04.09.26
 */

package api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import shai.kelv.calofficials.comments.entity.Comment;
import shai.kelv.calofficials.comments.repo.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * Constructor for CommentService
     * @param commentRepository
     */
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    /**
     * Method searches all comments in repository and returns the one with a unique id.
     * @param id unique identifier
     * @return Comment with matching id
     */
    public Comment getCommentById(Long id){
        return commentRepository.findById(id).orElse(null);
    }

    /**
     * Method returns all comments who are under the comment with inputed id
     * @param id the parent of children comments
     * @return all the nested comments under comment with id equal to parent id (first layer only)
     */
    public List<Comment> getCommentsByParentId(Long id){
        return commentRepository.findByParentId(id);
    }

    /**
     * Method returns all original comments that aren't part of a thread
     * @return list of comments 
     */
    public List<Comment> getComments(){
        return this.commentRepository.findByParentId(null);
    }
}
