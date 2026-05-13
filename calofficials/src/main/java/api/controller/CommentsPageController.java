/**
 * Description:
 * @author Shaila Lewis
 * @since 04.09.26
*/
package api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentsPageController {

    /**
     * Method connects specified url to an html page to view (comments.html)
     * @param model information/element to be added to the page
     * @return html file name to load
     */
    @GetMapping("/comments")
    public String commentsPage(Model model) {
        model.addAttribute("message", "See what people are saying");
        return "comments";
    }
}