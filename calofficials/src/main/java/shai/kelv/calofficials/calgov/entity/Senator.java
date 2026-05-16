/**
 * Description: Senator class to be  part of a column
 * @author Shaila Lewis
 * @since 05.15.2026
 */
package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SENATOR")
public class Senator extends Official {
}
