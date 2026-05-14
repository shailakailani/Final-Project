/**
 * Description:
 * @author Shaila Lewis
 * @since 04.09.26
*/
package api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import api.service.CommentService;
import shai.kelv.calofficials.comments.entity.Comment;

@Controller
public class CommentsPageController {

    private final CommentService commentService;

    public CommentsPageController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public String commentsPage(Model model) {
        List<Comment> comments = commentService.getComments();
        model.addAttribute("message", "See what people are saying");
        model.addAttribute("comments", comments);
        return "comments";
    }

    @PostMapping("/comments")
    public String addComment(
            @RequestParam(required = false) String username,
            @RequestParam String message) {

        if (username == null || username.trim().isEmpty()) {
            username = "anonymous";
        }

        commentService.saveComment(username, message, null);
        return "redirect:/comments";
    }
}