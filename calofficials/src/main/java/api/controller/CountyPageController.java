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

import shai.kelv.calofficials.calgov.entity.MapType;
>>>>>>> ebabe6e4fee33caf1f25ce4d4f93d2e9b1a0d353
import shai.kelv.calofficials.calgov.entity.Official;
import shai.kelv.calofficials.calgov.entity.OfficialType;
import shai.kelv.calofficials.calgov.repo.CommitteeRepository;
import shai.kelv.calofficials.calgov.repo.DistrictRepository;
import shai.kelv.calofficials.calgov.repo.OfficialRepository;
<<<<<<< HEAD
=======
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
>>>>>>> ebabe6e4fee33caf1f25ce4d4f93d2e9b1a0d353

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
<<<<<<< HEAD
    public String countyPage(@RequestParam(value = "searchbar", required = false) String county, Model model) {
        ArrayList<Official> results = new ArrayList<>();
        ArrayList<String> counties = new ArrayList<>();
        ArrayList<String> committees = new ArrayList<>();
        ArrayList<Object[]> districts = new ArrayList<>();

        model.addAttribute("searchterm", county);
        model.addAttribute("message", "What's your county?");

        if (county != null && !county.isBlank() && StringUtils.isAlphaSpace(county)) {
            try {
                String cleanedCounty = county.trim();

                // First try the full county name
                List<Object[]> initialMatches = districtRepository.findAllByCountiesContainingIgnoringCase(cleanedCounty);

                // If full county name did not work, try searching word by word
                if (initialMatches.isEmpty() && cleanedCounty.contains(" ")) {
                    Set<String> seenDistricts = new LinkedHashSet<>();
                    String[] parts = cleanedCounty.split("\\s+");

                    for (String part : parts) {
                        List<Object[]> partialMatches = districtRepository.findAllByCountiesContainingIgnoringCase(part);

                        for (Object[] dist : partialMatches) {
                            if (dist == null) {
                                continue;
                            }

                            String key = dist[0].toString() + "|" + dist[1].toString();
                            if (!seenDistricts.contains(key)) {
                                seenDistricts.add(key);
                                districts.add(dist);
                            }
                        }
                    }
                } else {
                    districts.addAll(initialMatches);
                }

                for (Object[] dist : districts) {
                    if (dist == null) {
                        continue;
                    }

                    Long districtId = ((Number) dist[0]).longValue();
                    String mapType = dist[1].toString();
                    OfficialType officialType = null;

                    switch (mapType) {
                        case "SENATE":
                            officialType = OfficialType.SENATOR;
                            break;
                        case "CONGRESS":
                            officialType = OfficialType.CONGRESSMAN;
                            break;
                        case "ASSEMBLY":
                            officialType = OfficialType.ASSEMBLY_MEMBER;
                            break;
                    }

                    if (officialType != null) {
                        results.addAll(officialRepository.findAllByDistrictIdAndOfficialType(districtId, officialType));
                    }
                }

                List<Official> temp = results.stream().distinct().collect(Collectors.toList());
                results = new ArrayList<>(temp);

                // Build counties + committees, then filter for multi-word county input
                ArrayList<Official> filteredResults = new ArrayList<>();
                ArrayList<String> filteredCounties = new ArrayList<>();
                ArrayList<String> filteredCommittees = new ArrayList<>();

                for (Official official : results) {
                    String committee = "";
                    String mapType = "";

                    switch (official.getOfficialType()) {
                        case SENATOR:
                            mapType = "SENATE";
                            break;
                        case CONGRESSMAN:
                            mapType = "CONGRESS";
                            break;
                        case ASSEMBLY_MEMBER:
                            mapType = "ASSEMBLY";
                            break;
                    }

                    List<String> rawCounties = districtRepository.findCountiesByDistrictIdAndMapType(
                            official.getDistrictId(), mapType);

                    String officerCounties = String.join(" ", rawCounties);
                    officerCounties = officerCounties.replaceAll("\\s+,", ",");
                    officerCounties = officerCounties.replaceAll(",\\s*,", ",");
                    officerCounties = officerCounties.replaceAll("\\bSan\\s+Francisco\\b", "San Francisco");
                    officerCounties = officerCounties.replaceAll("\\bSan\\s+Mateo\\b", "San Mateo");
                    officerCounties = officerCounties.replaceAll("\\bSan\\s+Bernardino\\b", "San Bernardino");
                    officerCounties = officerCounties.replaceAll("\\bSan\\s+Joaquin\\b", "San Joaquin");
                    officerCounties = officerCounties.replaceAll("\\bContra\\s+Costa\\b", "Contra Costa");
                    officerCounties = officerCounties.replaceAll("\\bSanta\\s+Clara\\b", "Santa Clara");
                    officerCounties = officerCounties.replaceAll("\\bEl\\s+Dorado\\b", "El Dorado");
                    officerCounties = officerCounties.replaceAll("\\s{2,}", " ").trim();

                    for (Long id : official.getCommitteeIds()) {
                        String committeeName = committeeRepository.findCommitteeById(id);
                        if (committeeName != null) {
                            committee += committeeName + ", ";
                        }
                    }

                    if (committee.endsWith(", ")) {
                        committee = committee.substring(0, committee.length() - 2);
                    }

                    // For multi-word input, only keep results that contain all words
                    boolean matchesAllWords = true;
                    String[] words = cleanedCounty.toLowerCase().split("\\s+");
                    String countyText = officerCounties.toLowerCase();

                    for (String word : words) {
                        if (!countyText.contains(word)) {
                            matchesAllWords = false;
                            break;
                        }
                    }

                    if (matchesAllWords) {
                        filteredResults.add(official);
                        filteredCounties.add(officerCounties);
                        filteredCommittees.add(committee.isBlank() ? "None" : committee);
                    }
                }

                results = filteredResults;
                counties = filteredCounties;
                committees = filteredCommittees;

            } catch (Exception e) {
                System.out.println("SOMETHING WENT WRONG: " + e.getMessage());
                e.printStackTrace();
            }
        }

        model.addAttribute("officials", results);
        model.addAttribute("counties", counties);
        model.addAttribute("committees", committees);

        return "county";
=======
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
>>>>>>> ebabe6e4fee33caf1f25ce4d4f93d2e9b1a0d353
    }
}