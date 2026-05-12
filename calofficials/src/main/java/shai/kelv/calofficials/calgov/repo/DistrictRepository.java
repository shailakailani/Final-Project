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
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    @Query(value = "SELECT     district_id,     group_concat(counties, ' ') AS full_county_name FROM district_counties GROUP BY district_id HAVING full_county_name LIKE :county;",
        nativeQuery = true
    )
    List<District> findByCountiesContainingIgnoringCase(String county);

    @Query(value = "SELECT * FROM districts WHERE districts.map_type = :mapType AND districts.district_id = :districtId;", 
       nativeQuery = true)
    District findByDistrictIdAndMapType(Long districtId, MapType mapType);

    @Query(value = "SELECT counties FROM district_counties WHERE district_counties.map_type = :mapType AND district_counties.district_id = :districtId;", nativeQuery = true)
    List<String> findCountiesByDistrictIdAndMapType(@Param("districtId") Long districtId, @Param("mapType") String mapType);
}
