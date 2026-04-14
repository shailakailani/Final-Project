/**
 * Description:
 * @author Shaila Lewis
 * @since 04.09.26
*/

package api.controller;
import org.springframework.web.bind.annotation.RestController;
import service.CommentService;



@RestController
public class CommentController {
    private CommentService commentService;

    /* 
    @GetMapping
    public Comment getComment(@RequestParam int id){
        return commentService.getComment(id);
    }*/
    
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }
    
    
    
}
