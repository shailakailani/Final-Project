/**
 * Description:
 * @author Shaila Lewis
 * @since 04.09.26
*/
package api.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @RequestMapping("../resources/static/")
    public String index(){
        return "index.html";
    }
}
