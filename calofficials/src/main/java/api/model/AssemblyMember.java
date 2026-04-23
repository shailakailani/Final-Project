package api.model;

/**
 * AssemblyMember class that extends Official.
 * @author Kelvin Myat
 * @since April 19, 2026
 */
public class AssemblyMember extends Official {

    /**
     * Creates an AssemblyMember object.
     * @param name the assembly member's name
     * @param party the assembly member's party
     * @param email the assembly member's email
     * @param office the assembly member's office
     * @param salary the assembly member's salary
     * @param districtId the district id
     */
    public AssemblyMember(String name, String party, String email, String office,
                          Long salary, Long districtId) {
        super(name, party, email, office, salary, districtId, "Assembly Member");
    }
}