/** 
 * Description: Creating querying functions for officials table
 * @author Shaila Lewis
 * @since 05.07.2026
 */


package shai.kelv.calofficials.calgov.repo;
import shai.kelv.calofficials.calgov.entity.Official;
import shai.kelv.calofficials.calgov.entity.OfficialType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OfficialRepository extends JpaRepository<Official, Long> {
    List<Official> findByDistrictId(Long districtId);
    List<Official> findByNameContainingIgnoreCase(String name);
    Official findByDistrictIdAndOfficialType(Long districtId, OfficialType officialType);
}