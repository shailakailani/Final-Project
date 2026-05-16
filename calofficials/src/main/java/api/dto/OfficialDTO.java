/**
 * Description: DTO for displaying information easily in front end
 * @author Shaila Lewis
 * @since 05.15.2026
 */
package api.dto;

public record OfficialDTO(String name, String officialType, Long salary, Long districtId, String committees, String counties) {
}
