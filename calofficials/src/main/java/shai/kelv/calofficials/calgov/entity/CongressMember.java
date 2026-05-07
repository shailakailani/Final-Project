package shai.kelv.calofficials.calgov.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="officials")
public class CongressMember extends Official{
    private OfficialType officialType = OfficialType.CONGRESSMAN;
}
