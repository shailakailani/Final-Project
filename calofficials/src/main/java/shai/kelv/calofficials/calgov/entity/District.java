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
    
 /**
     * Gets the district id.
     * @return the district id
     */
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * Sets the district id.
     * @param districtId the new district id
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    /**
     * Gets the counties in the district.
     * @return the counties
     */
    public List<String> getCounties() {
        return counties;
    }

    /**
     * Sets the counties in the district.
     * @param counties the new counties
     */
    public void setCounties(List<String> counties) {
        this.counties = counties;
    }

    /**
     * Gets the map type.
     * @return the map type
     */
    public MapType getMapType() {
        return mapType;
    }

    /**
     * Sets the map type.
     * @param mapType the new map type
     */
    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }
}