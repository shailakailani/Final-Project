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
@Table(name="districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="district_id", nullable = false)
    private Long districtId;  

    @ElementCollection
    @Column(name="counties")
    private List<String> counties;
    
    @Enumerated(EnumType.STRING)
    @Column(name="map_type")
    private MapType mapType;
}
