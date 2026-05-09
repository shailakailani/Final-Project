package shai.kelv.calofficials.calgov.entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ASSEMBLY_MEMBER")
public class AssemblyMember extends Official{
}
