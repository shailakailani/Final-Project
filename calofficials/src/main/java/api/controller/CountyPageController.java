/**
 * Description: 
 * @author Shaila Lewis
 * @since 04.23.26
 */

package api.controller;

import shai.kelv.calofficials.calgov.entity.MapType;
import shai.kelv.calofficials.calgov.entity.Official;
import shai.kelv.calofficials.calgov.entity.OfficialType;
import shai.kelv.calofficials.calgov.repo.CommitteeRepository;
import shai.kelv.calofficials.calgov.repo.DistrictRepository;
import shai.kelv.calofficials.calgov.repo.OfficialRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CountyPageController {
    @Autowired
    private OfficialRepository officialRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CommitteeRepository committeeRepository;

    /**
     * Displays county page when we go to pagellink/county
     * @param county search term from user
     * @param model
     * @return county html file name
     */
    @GetMapping("/county")
    public String countyPage(@RequestParam(value = "search_bar", required = false) String county, Model model) {
        //will find officials and send them to html page if there is a search term in the searchbar and it's an appropiate one.
        if (county != null && !county.isEmpty()) {
            ArrayList<Official> results = new ArrayList<Official>();
            ArrayList<String> counties = new ArrayList<String>();
            ArrayList<Object[]> districts = new ArrayList<Object[]>();
            ArrayList<String> committees = new ArrayList<String>();

            try{
                //don't add empty district arrays
                if(StringUtils.isAlpha(county) && !districtRepository.findAllByCountiesContainingIgnoringCase(county).isEmpty()){
                    districts.addAll(districtRepository.findAllByCountiesContainingIgnoringCase(county));

                    //don't attempt to use Official methods on null objects
                    for(Object[] dist: districts){
                        if( dist == null){
                            break;
                        }

                        switch(dist[1]){
                            case MapType.SENATE:
                                dist[1]= OfficialType.SENATOR;
                            case MapType.CONGRESS:
                                dist[1]= OfficialType.CONGRESSMAN;
                            default:
                                dist[1]=OfficialType.ASSEMBLY_MEMBER;        
                        }

                        results.addAll(officialRepository.findAllByDistrictIdAndMapType(dist[0],  dist[1]));
                        List<Official> temp = results.stream().distinct().collect(Collectors.toList());
                        results = new ArrayList<Official>(temp);
                      
                    }
                }
            }   catch (Exception e){
                System.out.print("Something went wrong while retrieving officials: " + e);
            }

            try {
                 //find counties and committees if we get a list of officials
                for(Official official : results){
                    String committee = "";
                    String officerCounties = String.join(", ", districtRepository.findCountiesByDistrictIdAndMapType(official.getDistrictId(), official.getOfficialType().getMapType().toString()));
                    
                    //formatting
                    officerCounties = officerCounties.replace("[", " ");
                    officerCounties = officerCounties.replace("]", " ");
                    officerCounties = officerCounties.replace(", ", " ");
                    officerCounties = officerCounties.replace(",,", ",");
                    
                    //retrieve committees
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
                model.addAttribute("searchbar", county);
                
            } catch (Exception e){
                System.out.print("Something went wrong while retrieving committees and counties: " + e);
            }
        }
            model.addAttribute("message",county != null && !county.isEmpty()? "You searched for: " + county:"What's your county?");
        return "county"; 
    }
    
}
