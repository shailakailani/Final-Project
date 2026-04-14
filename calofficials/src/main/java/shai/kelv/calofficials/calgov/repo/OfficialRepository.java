package shai.kelv.calofficials.calgov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import shai.kelv.calofficials.calgov.entity.Official;

public interface OfficialRepository extends JpaRepository<Official, Long> {
    List<Official> findByDistrictId(Long districtId);
}