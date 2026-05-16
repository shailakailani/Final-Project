/**
 * Description: Manages requests for index.html
 * @author Shaila Lewis
 * @since 05.09.36
 */

package api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomePageController {
     /**
     * Method connects specified url to an html page to view (index.html)
     * @return homepage file name
     */
    @GetMapping("/")
    public String homePage(){
        return "index";
    }
}
