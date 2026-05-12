/** 
 * Description: Creating querying functions for district table
 * @author Shaila Lewis
 * @since 05.07.2026
 */

package shai.kelv.calofficials.calgov.repo;
import shai.kelv.calofficials.calgov.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
  @Query(value = """
        SELECT DISTINCT districts.district_id, districts.map_type FROM districts  
        JOIN district_counties 
          ON districts.district_id = district_counties.district_id 
          AND districts.map_type = district_counties.map_type
        WHERE district_counties.counties LIKE %:county%
        """, nativeQuery = true)
    List<Object[]> findAllByCountiesContainingIgnoringCase(@Param("county") String county);


    @Query(value = "SELECT counties FROM district_counties WHERE district_counties.map_type = :mapType AND district_counties.district_id = :districtId;", nativeQuery = true)
    List<String> findCountiesByDistrictIdAndMapType(@Param("districtId") Long districtId, @Param("mapType") String mapType);
}
