package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.Entity;

@Entity
public class SenateDistrict extends District{
    private MapType mapType = MapType.SENATE;
}
