/**
 * Controller for displaying the comments page and adding new comments.
 * @author Shaila Lewis, Kelvin Myat
 * @since 05.15.26
 */
package api.controller;

import java.util.ArrayList;
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

    /**
     * Constructor for CommentsPageController.
     * @param commentService service used to manage comments
     */
    public CommentsPageController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Displays the comments page and loads all main comments.
     * @param model stores data for the comments page
     * @return the comments HTML page
     */
    @GetMapping("/comments")
    public String commentsPage(Model model) {
        try {
            List<Comment> comments = commentService.getComments();

            model.addAttribute("message", "See what people are saying");
            model.addAttribute("comments", comments);
        } catch (Exception e) {
            System.out.println("COMMENT LOAD ERROR TYPE: " + e.getClass().getName());
            System.out.println("COMMENT LOAD ERROR MESSAGE: " + e.getMessage());

            model.addAttribute("message", "See what people are saying");
            model.addAttribute("comments", new ArrayList<Comment>());
            model.addAttribute("errorMessage", "Comments could not be loaded.");
        }

        return "comments";
    }

    /**
     * Adds a new comment from the comments form.
     * @param username username entered by the user
     * @param message comment message entered by the user
     * @param model stores data for the comments page if an error happens
     * @return redirects to the comments page after saving
     */
    @PostMapping("/comments")
    public String addComment(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "message", required = true) String message,
            Model model) {

        try {
            if (username == null || username.trim().isEmpty()) {
                username = "anonymous";
            }

            if (message == null || message.trim().isEmpty()) {
                return "redirect:/comments";
            }

            commentService.saveComment(username, message, null);

            return "redirect:/comments";

        } catch (Exception e) {
            System.out.println("COMMENT POST ERROR TYPE: " + e.getClass().getName());
            System.out.println("COMMENT POST ERROR MESSAGE: " + e.getMessage());

            model.addAttribute("message", "See what people are saying");
            model.addAttribute("comments", new ArrayList<Comment>());
            model.addAttribute("errorMessage", "Comment could not be saved.");

            return "comments";
        }
    }
}