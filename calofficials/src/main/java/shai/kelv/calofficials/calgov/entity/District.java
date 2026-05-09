/** 
 * Description: Defining columns for district table as well as getters/setters
 * @author Shaila Lewis, Kelvin Myat
 * @since 05.09.2026
 */

package shai.kelv.calofficials.calgov.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="districts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name = "map_type")
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
    @Column(name="map_type", nullable = false, insertable = false, updatable = false)
    private MapType mapType;

    public Long getId(){
        return this.id;
    }

    public Long getDistrictId(){
        return this.districtId;
    }

    public List<String> getCounties(){
        return this.counties;
    }

    public MapType getMapType(){
        return this.mapType;
    }
}
