package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="officials")
public class AssemblyMember extends Official{
    private OfficialType officialType = OfficialType.ASSEMBLY_MEMBER;
    
}
