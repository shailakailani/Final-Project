/**
 * Description:
 * @author Shaila Lewis
 * @since 04.10.26
*/
package api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
     /**
     * Method connects specified url to an html page to view (index.html)
     * @return homepage file name
     */
    @GetMapping("/")
    public String homePage(){
        return "index";
    }
}
