package shai.kelv.calofficials.calgov.entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("CONGRESSMAN")
public class CongressMember extends Official{
}
