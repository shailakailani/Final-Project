package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (name = "official_id")
    private Long officialId;

    @Enumerated
    @Column(name = "official_type")
    private OfficialType officialType;
    
    @Column(name= "address", nullable = false)
    private String address;

}
