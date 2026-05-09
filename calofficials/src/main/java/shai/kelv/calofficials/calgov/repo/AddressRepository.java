/**
 * @author Shaila Lewis
 * @since 05.07.2026
 */

package shai.kelv.calofficials.calgov.repo;
import shai.kelv.calofficials.calgov.entity.Address;
import shai.kelv.calofficials.calgov.entity.OfficialType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
 Address findByOfficialIdAndOfficialType(Long officialId, OfficialType officialType);

}
