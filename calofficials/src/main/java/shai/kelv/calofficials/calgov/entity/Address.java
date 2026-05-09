/**
 * Description:
 * @author Shaila Lewis, Kelvin Myat
 * @since 05.09.2026
 */
package shai.kelv.calofficials.calgov.entity;
import jakarta.persistence.*;

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
