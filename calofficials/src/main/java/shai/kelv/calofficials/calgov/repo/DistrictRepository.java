/** 
 * Description: Creating querying functions for district table
 * @author Shaila Lewis
 * @since 05.07.2026
 */

package shai.kelv.calofficials.calgov.repo;
import shai.kelv.calofficials.calgov.entity.District;
import shai.kelv.calofficials.calgov.entity.MapType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District> findByCountiesContainingIgnoringCase(String counties);
    District findByDistrictIdAndMapType(Long districtId, MapType mapType);

    @Query(value = "SELECT c.counties FROM district_counties c " +
               "JOIN districts d ON c.district_id = d.district_id " +
               "WHERE d.district_id = :districtId AND d.map_type = :mapType", 
       nativeQuery = true)
    List<String> findCountiesByDistrictIdAndMapType(Long districtId, MapType mapType);
}
