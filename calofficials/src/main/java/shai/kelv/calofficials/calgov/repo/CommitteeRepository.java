/**
 * @author Shaila Lewis
 * @since 05.07.2026
 */

package shai.kelv.calofficials.calgov.repo;
import shai.kelv.calofficials.calgov.entity.Committee;
import org.springframework.data.jpa.repository.JpaRepository;
import shai.kelv.calofficials.calgov.entity.OfficialType;


public interface CommitteeRepository extends JpaRepository<Committee, Long> {

    public Committee findCommitteeById(Long id);
    public Committee findByIdAndOfficialType(Long id, OfficialType officialType); 
}

