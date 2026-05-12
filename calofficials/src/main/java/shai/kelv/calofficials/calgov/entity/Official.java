package shai.kelv.calofficials.calgov.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

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
    @CollectionTable(name = "official_committee_ids",
        joinColumns = {
            @JoinColumn(name = "official_id", referencedColumnName = "district_id"),
            @JoinColumn(name = "official_type", referencedColumnName = "official_type")
        }
    )
    @Column(name="committee_id")
    private List<Long> committeeIds;

    @Enumerated(EnumType.STRING)
    @Column(name="official_type", nullable = false, insertable = false, updatable = false)
    private OfficialType officialType;

    public String toString(){
        StringBuilder official = new StringBuilder("Salary: ");
        official.append(this.salary + "\n District ID: " + this.districtId);
        official.append("\n Name: " + this.name);
        official.append("\n Party: " + this.party);
        official.append("\n Committees: " + this.committeeIds);

        return official.toString();
    }
    
 /**
     * Gets the official id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the official id.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the official name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the official name.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the official party.
     * @return the party
     */
    public Party getParty() {
        return party;
    }

    /**
     * Gets the official salary.
     * @return the salary
     */
    public Long getSalary() {
        return this.salary;
    }

    /**
     * Sets the official party.
     * @param party the new party
     */
    public void setParty(Party party) {
        this.party = party;
    }

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
     * Gets the committee ids.
     * @return the committee ids
     */
    public List<Long> getCommitteeIds() {
        return committeeIds;
    }

    /**
     * Sets the committee ids.
     * @param committeeIds the new committee ids
     */
    public void setCommitteeIds(List<Long> committeeIds) {
        this.committeeIds = committeeIds;
    }

    /**
     * Gets the official type.
     * @return the official type
     */
    public OfficialType getOfficialType() {
        return officialType;
    }

    /**
     * Sets the official type.
     * @param officialType the new official type
     */
    public void setOfficialType(OfficialType officialType) {
        this.officialType = officialType;
    }
}