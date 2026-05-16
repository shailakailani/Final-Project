/**
 * Service class for handling searching for districts and officials logic.
 * @author Shaila Lewis, Kelvin Myat
 * @since 05.15.26
 */
package api.service;
import api.dto.OfficialDTO;
import shai.kelv.calofficials.calgov.entity.Official;
import shai.kelv.calofficials.calgov.entity.MapType;
import shai.kelv.calofficials.calgov.repo.CommitteeRepository;
import shai.kelv.calofficials.calgov.repo.DistrictRepository;
import shai.kelv.calofficials.calgov.repo.OfficialRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SearchService {
    @Autowired
    private OfficialRepository officialRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CommitteeRepository committeeRepository;

    /**
     * Splits strings based on the next capital letter
     * @param str
     * @return string of separated cammelcase string
     */
    private String splitString(String str){
        StringBuilder ans = new StringBuilder();
        ans.append(str.charAt(0));
        for(int i = 1; i < str.length(); i++){
            ans.append((Character.isUpperCase(str.charAt(i))? " ": "") + str.charAt(i));
        }
        return ans.toString();
    }

    /**
     * Finds committees based on a list of ids
     * @param ids to search with
     * @return String of all committees found
     */
    private String findCommittees(List<Long> ids){
        ArrayList<String> committeeNames = new ArrayList<String>(0);
        String result = "";

        for(Long id : ids){
            committeeNames.add(committeeRepository.findCommitteeById(id));
        }

        result = committeeNames.stream().filter(committee -> !Objects.isNull(committee) && !committee.isBlank()).collect(Collectors.joining(", "));

        return result.isBlank()? "None" : result;
    }

    /**
     * Finds counties based on a district id and map type
     * @param id to search with
     * @param mapType string to get particular district
     * @return String of all counties found
     */
    private String findCounties(Long id, String mapType){
        ArrayList<String> counties = new ArrayList<String>();
        counties.addAll(districtRepository.findCountiesByDistrictIdAndMapType(id, mapType));
        String result = counties.stream().filter(county -> !Objects.isNull(county) && !county.isBlank()).map(county -> splitString(county)).collect(Collectors.joining(", "));

        return result;
    }

    /**
     * Find all officials associated with a county
     * @param county nonempty string to query with
     * @return List of officials
     */
    public List<OfficialDTO> findOfficialsByCounty(String county){
        ArrayList<OfficialDTO> results = new ArrayList<OfficialDTO>();
        ArrayList<Official> officers = new ArrayList<Official>();
        
        try{
            if(!Objects.isNull(county) && !county.isBlank()){
                String term = county.replace(" ", "");
                System.out.println("TERM: " + term);
                List<Object[]> districts = districtRepository.findByCountiesContainingIgnoreCase(term);
                List<Object[]> validDistricts = districts.stream()
                .filter(Objects::nonNull)
                .toList();

                for(Object[] district: validDistricts){
                    System.out.println("District: " + district[0] + ", " + MapType.getOfficialType(district[1].toString()));
                    officers.addAll(officialRepository.findAllByDistrictIdAndOfficialType(district[0], MapType.getOfficialType(district[1].toString())));
                }

                for(Official off : officers){
                    String committees = findCommittees(off.getCommitteeIds());
                    String counties = findCounties(off.getDistrictId(), off.getMapType().toString());
                    results.add(convertToOfficialDTO(off, committees, counties));
                }
            }

        } catch (Exception e){
            System.out.print("SOMETHING WENT WRONG DURING SEARCH BY COUNTY: " + e);
        }

        return results;
    }

    /**
     * Find all officials associated with an id or name
     * @param county nonempty string to query with (can be a number or name)
     * @return List of officials
     */
    public List<OfficialDTO> findOfficials(String term){
        ArrayList<OfficialDTO> results = new ArrayList<OfficialDTO>();
        ArrayList<Official> officers = new ArrayList<Official>();
        
        try{
            if(NumberUtils.isDigits(term)){
                Long id = Math.abs(Long.parseLong(term));
                officers.addAll(officialRepository.findAllByDistrictId(id));
            } else if(StringUtils.isAlpha(term)){
                officers.addAll(officialRepository.findByNameContainingIgnoreCase(term));
            }

            officers.removeIf(officer-> Objects.isNull(officer));
            for(Official off : officers){
                String committees = findCommittees(off.getCommitteeIds());
                String counties = findCounties(off.getDistrictId(), off.getMapType().toString());
                results.add(convertToOfficialDTO(off, committees, counties));
            }

        } catch (Exception e){
            System.out.print("SOMETHING WENT WRONG DURING SEARCH BY NAME/ID: " + e);
        } 

        return results;
    }

    /**
     * Method merges committes, official, and their counties into a simpler object to display.
     * @param official the official to convert
     * @param commitees official is in
     * @param counties official serves
     * @return DTO to simplify usage in our front end
     */
    private OfficialDTO convertToOfficialDTO(Official official, String commitees, String counties){
        return new OfficialDTO(
            official.getName(),
            official.getOfficialTitle(),
            official.getSalary(),
            official.getDistrictId(),
            commitees,
            counties
        );
    }
}
