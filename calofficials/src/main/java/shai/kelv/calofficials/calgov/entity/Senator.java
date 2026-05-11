package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SENATOR")
public class Senator extends Official {
}
