package api.model;

/**
 * Senator class that extends Official.
 * @author Kelvin Myat
 * @since April 19, 2026
 */
public class Senator extends Official {

    /**
     * Creates a Senator object.
     * @param name the senator's name
     * @param party the senator's party
     * @param email the senator's email
     * @param office the senator's office
     * @param salary the senator's salary
     * @param districtId the district id
     */
    public Senator(String name, String party, String email, String office,
                   Long salary, Long districtId) {
        super(name, party, email, office, salary, districtId, "Senator");
    }
}