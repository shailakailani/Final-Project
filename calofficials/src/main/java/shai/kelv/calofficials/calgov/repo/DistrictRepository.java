/**
 * Description: Creating querying functions for district table
 * @author Shaila Lewis
 * @since 05.07.2026
 */
package shai.kelv.calofficials.calgov.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shai.kelv.calofficials.calgov.entity.District;

public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query(value = """
        SELECT DISTINCT d.district_id, d.map_type
        FROM districts d
        JOIN district_counties dc
          ON d.district_id = dc.district_id
         AND d.map_type = dc.map_type
        WHERE lower(dc.counties) LIKE '%' || lower(:county) || '%'
        """, nativeQuery = true)
    List<Object[]> findAllByCountiesContainingIgnoringCase(@Param("county") String county);

    @Query(value = """
        SELECT counties
        FROM district_counties
        WHERE district_counties.map_type = :mapType
          AND district_counties.district_id = :districtId
        """, nativeQuery = true)
    List<String> findCountiesByDistrictIdAndMapType(@Param("districtId") Long districtId,
                                                    @Param("mapType") String mapType);
}