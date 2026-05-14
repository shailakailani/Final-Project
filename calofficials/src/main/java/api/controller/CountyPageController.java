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

import shai.kelv.calofficials.calgov.entity.Official;
import shai.kelv.calofficials.calgov.entity.OfficialType;
import shai.kelv.calofficials.calgov.repo.CommitteeRepository;
import shai.kelv.calofficials.calgov.repo.DistrictRepository;
import shai.kelv.calofficials.calgov.repo.OfficialRepository;

@Controller
public class CountyPageController {

    @Autowired
    private OfficialRepository officialRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private CommitteeRepository committeeRepository;

    @GetMapping("/county")
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
    }
}