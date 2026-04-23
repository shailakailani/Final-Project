package api.model;

/**
 * Official.java
 * Parent class for all officials.
 * @author Kelvin Myat
 * @since April 16, 2026
 */
public class Official {
    private String name;
    private String party;
    private String email;
    private String office;
    private Long salary;
    private Long districtId;
    private String officialType;

    /**
     * Creates an Official object.
     * @param name the official's name
     * @param party the official's party
     * @param email the official's email
     * @param office the official's office
     * @param salary the official's salary
     * @param districtId the district id
     * @param officialType the type of official
     */
    public Official(String name, String party, String email, String office,
                    Long salary, Long districtId, String officialType) {
        this.name = name;
        this.party = party;
        this.email = email;
        this.office = office;
        this.salary = salary;
        this.districtId = districtId;
        this.officialType = officialType;
    }

    /**
     * Gets the official's name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the official's party.
     * @return the party
     */
    public String getParty() {
        return party;
    }

    /**
     * Gets the official's email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the official's office.
     * @return the office
     */
    public String getOffice() {
        return office;
    }

    /**
     * Gets the official's salary.
     * @return the salary
     */
    public Long getSalary() {
        return salary;
    }

    /**
     * Gets the district id.
     * @return the district id
     */
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * Gets the official type.
     * @return the official type
     */
    public String getOfficialType() {
        return officialType;
    }

    /**
     * Sets the official's name.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the official's party.
     * @param party the new party
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * Sets the official's email.
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the official's office.
     * @param office the new office
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * Sets the official's salary.
     * @param salary the new salary
     */
    public void setSalary(Long salary) {
        this.salary = salary;
    }

    /**
     * Sets the district id.
     * @param districtId the new district id
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    /**
     * Sets the official type.
     * @param officialType the new official type
     */
    public void setOfficialType(String officialType) {
        this.officialType = officialType;
    }

    /**
     * Returns the official information as a string.
     * @return the official information
     */
    @Override
    public String toString() {
        return "Official{" +
                "name='" + name + '\'' +
                ", party='" + party + '\'' +
                ", email='" + email + '\'' +
                ", office='" + office + '\'' +
                ", salary=" + salary +
                ", districtId=" + districtId +
                ", officialType='" + officialType + '\'' +
                '}';
    }
}