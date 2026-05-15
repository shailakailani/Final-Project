<<<<<<< HEAD
package api.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

=======
/**
 * Description: 
 * @author Shaila Lewis
 * @since 04.23.26
 */

package api.controller;
import api.dto.OfficialDTO;
import api.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.ArrayList;

@Controller
public class CountyPageController {
    private final SearchService searchService;

    public CountyPageController (SearchService searchService){
        this.searchService = searchService;
    }

    /**
     * Displays county page when we go to pagellink/county
     * @param county search term from user
     * @param model
     * @return county html file name
     */
    @GetMapping("/county")
    public String countyPage(@RequestParam(value = "search_bar", required = false) String county, Model model) {
        ArrayList<OfficialDTO> results = new ArrayList<OfficialDTO>(searchService.findOfficialsByCounty(county));
        if(results!=null) {
            model.addAttribute("officials", results);
            model.addAttribute("search_bar", county);
        }
            model.addAttribute("message","Search" + (county != null && !county.isBlank()? "ed: " + county : " Something"));
        return "county"; 
>>>>>>> ebabe6e4fee33caf1f25ce4d4f93d2e9b1a0d353
    }
}