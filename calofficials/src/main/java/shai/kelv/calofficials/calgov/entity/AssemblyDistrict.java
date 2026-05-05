package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.Entity;

@Entity
public class AssemblyDistrict extends District{
    private MapType mapType = MapType.ASSEMBLY;
}
