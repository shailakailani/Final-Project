/**
 * 
 * @author Shaila Lewis
 * @since 05.06.2026
 */
package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.*;

@Entity
@Table(name="committee")
public class Committee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique = true)
    private String name;

    @Column(name="desc", nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="official_type", nullable = false)
    private OfficialType officialType;

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Long getId(){
        return this.id;
    }

    public OfficialType getOfficialType(){
        return this.officialType;
    }
}
