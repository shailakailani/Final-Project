/**
 * Description
 * @author Shaila Lewis
 * @since 04.23.26
 */
package api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class CountyPageController {
    @GetMapping("/county")
    public String countyPage(Model model) {
        model.addAttribute("message", "what county are you from?");
        return "/county";
    }
    
}
