package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public class Official {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String party;
    private Long districtId;

    @ElementCollection
    private List<Long> committeeIds;
}
