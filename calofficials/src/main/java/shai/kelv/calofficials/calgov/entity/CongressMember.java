/**
 * Description:Congress Member class to be part of a column
 * @author Shaila Lewis
 * @since 05.09.2026
 */

package shai.kelv.calofficials.calgov.entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONGRESSMAN")
public class CongressMember extends Official{
}
