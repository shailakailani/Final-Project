/**
 * Description: Senate District class to be part of a column
 * @author Shaila Lewis
 * @since 05.09.2026
 */

package shai.kelv.calofficials.calgov.entity;
import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("SENATE")
public class SenateDistrict extends District{
}
