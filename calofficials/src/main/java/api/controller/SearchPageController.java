/**
 * Description: 
 * @author Shaila Lewis
 * @since 05.09.36
 */

package api.controller;
import shai.kelv.calofficials.calgov.entity.Official;
import shai.kelv.calofficials.calgov.repo.CommitteeRepository;
import shai.kelv.calofficials.calgov.repo.DistrictRepository;
import shai.kelv.calofficials.calgov.repo.OfficialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.ArrayList;

@Controller
public class SearchPageController {
    @Autowired
    private OfficialRepository officialRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CommitteeRepository committeeRepository;

    /**
     * Method connects specified url to an html page to view (search.html), and if user searches returns officials to view.
     * @param model information/element to be added to the page
     * @return html file name to load
    */
    @GetMapping("/search")
    public String search(@RequestParam(value = "searchbar", required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            ArrayList<Official> results = new ArrayList<Official>();
            ArrayList<String> counties = new ArrayList<String>();
            ArrayList<String> committees = new ArrayList<String>();

            try{
                if(NumberUtils.isDigits(name) && !officialRepository.findAllByDistrictId(Math.abs(Long.parseLong(name))).isEmpty()){
                    results.addAll(officialRepository.findAllByDistrictId(Math.abs(Long.parseLong(name))));
                } else if(StringUtils.isAlpha(name) && !officialRepository.findByNameContainingIgnoreCase(name).isEmpty()){
                    results.addAll(officialRepository.findByNameContainingIgnoreCase(name));
                }
            }catch (Exception e){
                System.out.print("SOMETHING WENT WRONG: " + e);
            }

            //find counties and committees if we get a list of officials
            for(Official official : results){
                String committee = "";
                String officerCounties = String.join(", ", districtRepository.findCountiesByDistrictIdAndMapType(official.getDistrictId(), official.getOfficialType().getMapType().toString()));
                
                //formatting
                officerCounties = officerCounties.replace("[", " ");
                officerCounties = officerCounties.replace("]", " ");
                officerCounties = officerCounties.replace(", ", " ");
                officerCounties = officerCounties.replace(",,", ",");
                
                for(Long id : official.getCommitteeIds()){
                    committee += committeeRepository.findCommitteeById(id) + ", ";
                }
                
                committees.add(committee.isBlank()? "None" : committee);
                counties.add(officerCounties);  
            }

            //show results
            model.addAttribute("officials", results);
            model.addAttribute("counties", counties);
            model.addAttribute("committees", committees);
            model.addAttribute("searchbar", name);
            model.addAttribute("message", name);
        }
            model.addAttribute("message","Search Something");
        return "search"; 
    }
}
