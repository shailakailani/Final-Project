/**
 * Description:  
 * @author Shaila Lewis, Kelvin Myat
 * @since 05.15.26
*/
package api.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.service.CommentService;
import shai.kelv.calofficials.comments.entity.Comment;


@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;
    /**
     * Constructor for CommentController which will handle data and not render visuals.
     * @param commentService 
     */
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    /**
     * Method finds comment by id
     * @param id unique id of Comment 
     * @return comment which has unique id value
     */
    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id){
        return commentService.getCommentById(id);
    }

    /**
     * Method gets all comments available
     * @return list of comments that aren't under a thread of comments
     */
    @GetMapping
    public List<Comment> getComments(){
        return commentService.getComments();
    }

    
}
