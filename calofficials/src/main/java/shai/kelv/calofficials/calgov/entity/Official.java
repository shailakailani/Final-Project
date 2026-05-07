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
    @Column(name="id")
    private Long id;

    @Column(name= "name", unique= false, nullable= false)
    private String name;

    @Column(name= "salary", unique= false, nullable= true)
    private Long salary;

    @Enumerated(EnumType.STRING)
    @Column(name="party", nullable = true)
    private Party party;

    @Column(name = "district_id", nullable = false)
    private Long districtId;

    @ElementCollection
    @Column(name="committees")
    private List<Long> committeeIds;

    @Enumerated(EnumType.STRING)
    @Column(name="official_type", nullable = false)
    private OfficialType officialType;
}
