package shai.kelv.calofficials.calgov.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Official {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "NAME", unique= false, nullable= false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Party party;

    @Column(name = "DISTRICT_ID", nullable = false)
    private Long districtId;

    @ElementCollection
    private List<Long> committeeIds;

    @Enumerated(EnumType.STRING)
    private OfficialType officialType;
    
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