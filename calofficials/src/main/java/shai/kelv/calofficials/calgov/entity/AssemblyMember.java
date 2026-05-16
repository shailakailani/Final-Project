/**
 * Description: Assembly Member class to be turned into part of a column
 * @author Shaila Lewis, Kelvin Myat
 * @since 05.09.2026
 */
package shai.kelv.calofficials.calgov.entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ASSEMBLY_MEMBER")
public class AssemblyMember extends Official{
}
