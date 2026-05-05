package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ADDRESSES")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (name = "OFFICIAL_ID")
    private Long officialId;

    @Enumerated
    private OfficialType officialType;
    
    @Column(name= "ADDRESS", nullable = false)
    private String address;

}
