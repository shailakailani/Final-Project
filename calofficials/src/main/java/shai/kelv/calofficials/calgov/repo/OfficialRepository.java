/** 
 * Description: Creating querying functions for officials table
 * @author Shaila Lewis
 * @since 05.07.2026
 */


package shai.kelv.calofficials.calgov.repo;
import shai.kelv.calofficials.calgov.entity.MapType;
import shai.kelv.calofficials.calgov.entity.Official;
import shai.kelv.calofficials.calgov.entity.OfficialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OfficialRepository extends JpaRepository<Official, Long> {
    @Query(value = """
        SELECT DISTINCT districts.district_id, districts.map_type FROM districts
        JOIN district_counties
          ON districts.district_id = district_counties.district_id 
          AND districts.map_type = district_counties.map_type 
        WHERE district_counties.counties LIKE %:county%
        """, nativeQuery = true)
    List<Official> findAllByDistrictId(@Param("districtId") Long districtId);

    List<Official> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT DISTINCT official FROM Official official LEFT JOIN FETCH official.committeeIds WHERE official.districtId = :districtId AND official.officialType = :officialType") 
    List<Official> findAllByDistrictIdAndOfficialType(@Param("districtId") Long districtId, @Param("officialType") String officialType);

    @Query("SELECT DISTINCT official FROM Official official LEFT JOIN FETCH official.committeeIds WHERE official.districtId = :districtId AND official.officialType = :officialType") 
    List<Official> findAllByDistrictIdAndMapType(@Param("districtId") Object districtId, @Param("officialType") Object officialType);

    @Query("SELECT DISTINCT official FROM Official official LEFT JOIN FETCH official.committeeIds WHERE official.districtId = :districtId AND official.officialType = :officialType") 
    List<Official> findAllByCounty(@Param("districtId") Long districtId, @Param("officialType") OfficialType officialType);
}