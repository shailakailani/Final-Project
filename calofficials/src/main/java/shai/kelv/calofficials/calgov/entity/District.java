package shai.kelv.calofficials.calgov.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="DISTRICTS")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="DISTRICT_ID", nullable = false)
    private Long districtId;  

    @ElementCollection
    private List<String> counties;
    
    @Enumerated(EnumType.STRING)
    private MapType mapType;
}
