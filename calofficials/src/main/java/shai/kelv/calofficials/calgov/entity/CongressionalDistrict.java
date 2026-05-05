package shai.kelv.calofficials.calgov.entity;

import jakarta.persistence.Entity;

@Entity
public class CongressionalDistrict extends District {
    private MapType mapType =  MapType.CONGRESS;
}
