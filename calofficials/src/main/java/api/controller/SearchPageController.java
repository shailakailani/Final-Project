/**
 * Description: Manages requests for search.html
 * @author Shaila Lewis
 * @since 05.09.36
 */

package api.controller;
import api.dto.OfficialDTO;
import api.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

@Controller
public class SearchPageController {
    private final SearchService searchService;

    /**
     * Constructor for SearchPageController
     * @param searchService service that handles querying logic
     */
    public SearchPageController (SearchService searchService){
        this.searchService = searchService;
    }

    /**
     * Method connects search url to view search html page, and if user searches it adds officials to view.
     * @param model information/element to be added to the page
     * @return html file name to load
    */
    @GetMapping("/search")
    public String search(@RequestParam(value = "searchbar", required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            ArrayList<OfficialDTO> results = new ArrayList<OfficialDTO>(searchService.findOfficials(name));
            model.addAttribute("officials", results);
            model.addAttribute("searchbar", name);
            model.addAttribute("message","Searched: " + name);
        }
            model.addAttribute("message","Search" + (name != null && !name.isBlank()?  "ed: " + name : " Something"));
        return "search"; 
    }
}
