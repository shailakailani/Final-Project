package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="officials")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name = "official_type")
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
    @Column(name="official_type", nullable = false, insertable = false, updatable = false)
    private OfficialType officialType;

    public String getName(){
        return this.name;
    }

    public Long getDistrictId(){
        return this.districtId;
    }

    public Long getId(){
        return this.id;
    }

    public OfficialType getOfficialType(){
        return this.officialType;
    }
    public Party getParty(){
        return this.party;
    }

    public Long getSalary(){
        return this.salary;
    }

    public List<Long> getCommitteeIds(){
        return this.committeeIds;
    }

    public String toString(){
        StringBuilder official = new StringBuilder("Salary: ");
        official.append(this.salary + "\n District ID: " + this.districtId);
        official.append("\n Name: " + this.name);
        official.append("\n Party: " + this.party);
        official.append("\n Committees: " + this.committeeIds);

        return official.toString();
    }
}
