package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Column(name= "NAME", unique= false, nullable= false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Party party;

    @Column(name = "DISTRICT_ID", nullable = false)
    private Long districtId;

    @ElementCollection
    private List<Long> committeeIds;

    @Enumerated(EnumType.STRING)
    private OfficialType officialType;
}
