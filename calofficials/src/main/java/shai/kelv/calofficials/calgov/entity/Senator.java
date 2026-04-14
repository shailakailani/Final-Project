package shai.kelv.calofficials.calgov.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

@Entity
public class Senator extends Official {
    @ElementCollection
    private List<Long> committeeIds;
}
