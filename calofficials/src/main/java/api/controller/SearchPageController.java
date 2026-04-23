/**
 * Description: 
 * @author Shaila Lewis
 * @since 04.23.36
 */

package api.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchPageController {
    /**
     * Method connects specified url to an html page to view (search.html)
     * @param model information/element to be added to the page
     * @return html file name to load
     */
    @GetMapping("/search")
    public String searchPage(Model model) {
        model.addAttribute("message","Are you trying to search something?");
        return "search";
    }
    
}
