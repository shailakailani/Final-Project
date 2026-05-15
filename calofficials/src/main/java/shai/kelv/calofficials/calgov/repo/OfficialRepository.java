 /** 
 * Description: Creating querying functions for officials table
 * @author Shaila Lewis
 * @since 05.07.2026
 */
<<<<<<< HEAD
package shai.kelv.calofficials.calgov.repo;

import java.util.List;

=======

package shai.kelv.calofficials.calgov.repo;
import shai.kelv.calofficials.calgov.entity.Official;
>>>>>>> ebabe6e4fee33caf1f25ce4d4f93d2e9b1a0d353
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shai.kelv.calofficials.calgov.entity.Official;
import shai.kelv.calofficials.calgov.entity.OfficialType;

public interface OfficialRepository extends JpaRepository<Official, Long> {

    @Query("SELECT DISTINCT official FROM Official official LEFT JOIN FETCH official.committeeIds WHERE official.districtId = :districtId")
    List<Official> findAllByDistrictId(@Param("districtId") Long districtId);

    List<Official> findByNameContainingIgnoreCase(String name);

    @Query("SELECT DISTINCT official FROM Official official LEFT JOIN FETCH official.committeeIds WHERE official.districtId = :districtId AND official.officialType = :officialType") 
    List<Official> findAllByDistrictIdAndOfficialType(@Param("districtId") Object districtId, @Param("officialType") Object officialType);
}