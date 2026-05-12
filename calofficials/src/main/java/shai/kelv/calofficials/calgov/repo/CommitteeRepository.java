/**
 * @author Shaila Lewis
 * @since 05.07.2026
 */

package shai.kelv.calofficials.calgov.repo;
import shai.kelv.calofficials.calgov.entity.Committee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommitteeRepository extends JpaRepository<Committee, Long> {

    @Query(value="SELECT \"name\" FROM committee WHERE id=:id;", nativeQuery=true)
    public String findCommitteeById(@Param("id") Long id);


    @Query(value="SELECT \"name\" FROM committee WHERE id=:id AND official_type=:officialType;", nativeQuery=true)
    public String findByIdAndOfficialType(Long id, String officialType); 

  
}

