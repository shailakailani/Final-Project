package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="OFFICIALS")
public class Senator extends Official {
    private OfficialType officialType = OfficialType.SENATOR;
}
