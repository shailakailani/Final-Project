package shai.kelv.calofficials.calgov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import shai.kelv.calofficials.calgov.entity.District;

public interface DistrictRepository extends JpaRepository<District, Long> {
    District findByCountiesContaining(String county);
    
}