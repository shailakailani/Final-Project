package api.model;

/**
 * CongressMember class that extends Official.
 * April 19, 2026
 */
public class CongressMember extends Official {

    /**
     * Creates a CongressMember object.
     * @param name the congress member's name
     * @param party the congress member's party
     * @param email the congress member's email
     * @param office the congress member's office
     * @param salary the congress member's salary
     * @param districtId the district id
     */
    public CongressMember(String name, String party, String email, String office,
                          double salary, int districtId) {
        super(name, party, email, office, salary, districtId, "Congress Member");
    }
}